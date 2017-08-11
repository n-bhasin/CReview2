package com.remake.creview;

import android.net.Uri;

/**
 * Created by pc on 06-08-2017.
 */

public class Utils {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "users.db";
    public static final String TAB_NAME = "users";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final String COL_QUALITY = "QUALITY";
    public static final String COL_SERVICE = "SERVICE";
    public static final String COL_CLEANINESS = "CLEANINESS";
    public static final String COL_RATING = "RATING";

    public static final String CREATE_TAB_QUERY = "create table users(_ID integer primary key autoincrement, NAME text, EMAIL text, PHONE text, ADDRESS text, QUALITY text, SERVICE text, CLEANINESS text, RATING text)";

    public static final Uri USER_URI = Uri.parse("content://com.remake.creview.mycontentprovider/"+TAB_NAME);

    public static final String KEY_CUSTOMER = "keyCustomer";
}
