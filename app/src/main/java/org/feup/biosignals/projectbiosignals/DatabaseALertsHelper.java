package org.feup.biosignals.projectbiosignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;
import android.util.Log;

import org.feup.biosignals.projectbiosignals.ui.alerts.classAlertItem;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatabaseALertsHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="dbAlerts";
	public static final String DATE="date";
	public static final String TITLE="title";
	public static final String MESSAGE="message";

	public DatabaseALertsHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE table_alerts (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, message TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		android.util.Log.w("Constants", "Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS table_alerts");
		onCreate(db);
	}
}