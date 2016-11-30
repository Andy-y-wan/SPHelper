package com.pl.sphelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import static com.pl.sphelper.ConstantUtil.*;


public class GetAwayContentProvider extends ContentProvider{
    private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);

    @Override
    public boolean onCreate() {

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] path= uri.getPath().split(SEPARATOR);
        String type=path[1];
        String key=path[2];
//        Cursor cursor=new MatrixCursor(new String[]{DEFAULT_CURSOR_NAME});
//        cursor.
//        SPHelperImpl.get(key, type);
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        // 用这个来取数值
        String[] path= uri.getPath().split(SEPARATOR);
        String type=path[1];
        String key=path[2];
        if (type.equals(TYPE_CONTAIN)){
            return SPHelperImpl.contains(getContext(),key)+"";
        }
        return  ""+SPHelperImpl.get(getContext(),key,type);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String[] path= uri.getPath().split(SEPARATOR);
        String type=path[1];
        String key=path[2];
        Object obj= (Object) values.get(VALUE);
        if (obj!=null)
            SPHelperImpl.save(getContext(),key,obj);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String[] path= uri.getPath().split(SEPARATOR);
        String type=path[1];
        String key=path[2];
        if (SPHelperImpl.contains(getContext(),key)){
            SPHelperImpl.remove(getContext(),key);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
       insert(uri,values);
       return 0;
    }
}