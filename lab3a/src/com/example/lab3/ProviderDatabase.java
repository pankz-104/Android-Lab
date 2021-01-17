package com.example.lab3;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class ProviderDatabase extends SQLiteOpenHelper
{
public static String DATABASE_NAME="noteprovider";
public static String TABLE_NAME="notes";
public static String COLUMN_DATE="note_date";
public static String COLUMN_NOTE="content";
public ProviderDatabase(Context context, String name,
CursorFactory factory, int version) {
super(context, name, factory, version);
// TODO Auto-generated constructor stub
}
@Override
public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
db.execSQL("create table notes (note_date TEXT,content TEXT)");
}
@Override
public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
// TODO Auto-generated method stub
}
}