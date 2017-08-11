package com.remake.creview;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    EditText editSearch;
    UserAdapter userAdapter;
    ArrayList<Users> userList;
    ContentResolver resolver;
    Users users;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        editSearch = (EditText)findViewById(R.id.textViewSearch);
        listView = (ListView)findViewById(R.id.list_item);
        resolver = getContentResolver();
        users = new Users();
        retrieve();
    }
    public void retrieve(){
        String projections[] = {Utils.COL_ID,Utils.COL_NAME,Utils.COL_EMAIL,Utils.COL_PHONE,Utils.COL_ADDRESS,Utils.COL_QUALITY,Utils.COL_SERVICE,Utils.COL_CLEANINESS,Utils.COL_RATING};
        Cursor cursor = resolver.query(Utils.USER_URI,projections,null,null,null);
        if(cursor!=null){
            userList = new ArrayList<>();
            int id =0;
            String n="",e="",p="",a="",q="",s="",c="";
            float r=0F;
            while(cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndex(Utils.COL_ID));
                n = cursor.getString(cursor.getColumnIndex(Utils.COL_NAME));
                e = cursor.getString(cursor.getColumnIndex(Utils.COL_EMAIL));
                p = cursor.getString(cursor.getColumnIndex(Utils.COL_PHONE));
                a = cursor.getString(cursor.getColumnIndex(Utils.COL_ADDRESS));
                q = cursor.getString(cursor.getColumnIndex(Utils.COL_QUALITY));
                s = cursor.getString(cursor.getColumnIndex(Utils.COL_SERVICE));
                c = cursor.getString(cursor.getColumnIndex(Utils.COL_CLEANINESS));
                r = cursor.getFloat(cursor.getColumnIndex(Utils.COL_RATING));

                users = new Users(id,n,e,p,a,q,s,c,r);
                userList.add(users);
            }
        }
        userAdapter = new UserAdapter(this,R.layout.card_view,userList);
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(this);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userAdapter.getFilter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        users = userList.get(position);
        showDialog();
        pos = position;
    }
    public void showDialog(){
        String[] options = {"View User","Delete User","Update User"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        showUser();
                        break;
                    case 1:
                        askForDeletion();
                        break;
                    case 2:
                        Intent intent = new Intent(AllUsersActivity.this,UpdateUserActivity.class);
                        intent.putExtra(Utils.KEY_CUSTOMER,users);
                        startActivityForResult(intent,101);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==102){
            users = (Users) data.getSerializableExtra("keyUpdate");

            userList.set(pos,users);
            userAdapter.notifyDataSetChanged();
            Toast.makeText(this,users.getName()+ " is Successfully Updated.",Toast.LENGTH_LONG).show();
        }
    }

    public void showUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(users.getName());
        builder.setMessage(users.toString());
        builder.setPositiveButton("Done",null);
        builder.create().show();
    }
    public void deleteUser(){
        String where = Utils.COL_ID+"="+users.getId();
        int i = resolver.delete(Utils.USER_URI,where,null);
        if(i>0){
            Toast.makeText(this,users.getName()+" is deleted successfully",Toast.LENGTH_LONG).show();
            userList.remove(pos);
            userAdapter.notifyDataSetChanged();
        }
    }
    public void askForDeletion(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(users.getName());
        builder.setMessage("Are your sure to delete the record ?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.backupmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.backup){
            FileWriter fw = null;
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();


            try {
                File file = new File(path+"/backupCustomers.txt");
                fw = new FileWriter(file,true);


                for(Users user : userList){
                    fw.write(user.getId()+" - "+user.getName()+"-"+user.getEmail()+"-"+user.getPhone()+"-"+user.getAddress()+user.getRatings()+"\n");
                }

                fw.close();
                Toast.makeText(this, "Successfully Backedup", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "exception"+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }


        }
        return super.onOptionsItemSelected(item);
    }
}
