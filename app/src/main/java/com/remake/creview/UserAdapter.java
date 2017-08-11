package com.remake.creview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 06-08-2017.
 */

public class UserAdapter extends ArrayAdapter{
    Context context;
    int resource;
    ArrayList<Users> objects, templist;
    public UserAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Users> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

        templist = new ArrayList<>();
        templist.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        view  = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView custName = (TextView)view.findViewById(R.id.textViewName);
        TextView custPhone = (TextView)view.findViewById(R.id.textViewNumber);

        Users users = objects.get(position);
        custName.setText(users.getId()+" "+users.getName());
        custPhone.setText(users.getPhone());

        return view;
    }

    public void getFilter(String s) {
        objects.clear();
        if(s.length() == 0){
            objects.addAll(templist);
        }
        else{
            for(int i=0;i<templist.size();i++){
                if(templist.get(i).getName().toLowerCase().contains(s.toLowerCase())){
                    objects.add(templist.get(i));
                    Spannable color = new Spannable() {
                        @Override
                        public void setSpan(Object what, int start, int end, int flags) {

                         what = new ForegroundColorSpan(Color.BLUE);
                        }

                        @Override
                        public void removeSpan(Object what) {

                        }

                        @Override
                        public <T> T[] getSpans(int start, int end, Class<T> type) {
                            return null;
                        }

                        @Override
                        public int getSpanStart(Object tag) {
                            return 0;
                        }

                        @Override
                        public int getSpanEnd(Object tag) {
                            return 0;
                        }

                        @Override
                        public int getSpanFlags(Object tag) {
                            return 0;
                        }

                        @Override
                        public int nextSpanTransition(int start, int limit, Class type) {
                            return 0;
                        }

                        @Override
                        public int length() {
                            return 0;
                        }

                        @Override
                        public char charAt(int index) {
                            return 0;
                        }

                        @Override
                        public CharSequence subSequence(int start, int end) {
                            return null;
                        }
                    };

                }
            }
        }
        notifyDataSetChanged();
    }
}
