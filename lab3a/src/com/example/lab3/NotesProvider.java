package com.example.lab3;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class NotesProvider extends ContentProvider {
	static final String URL = "content://com.example.notesprovider/notes";
	SQLiteDatabase db;
	ProviderDatabase dbHelper;
	static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.example.notesprovider", "notes", 1);
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues cv) {
		// TODO Auto-generated method stub
		db = dbHelper.getWritableDatabase();
		db.insert(ProviderDatabase.TABLE_NAME, null, cv);
		db.close();
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new ProviderDatabase(getContext(),
				ProviderDatabase.DATABASE_NAME + ".db", null, 1);
		return (db == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		Cursor cursor = null;
		db = dbHelper.getReadableDatabase();
		cursor = db.query(ProviderDatabase.TABLE_NAME, arg1, arg2, arg3, arg4,
				null, null);
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
}