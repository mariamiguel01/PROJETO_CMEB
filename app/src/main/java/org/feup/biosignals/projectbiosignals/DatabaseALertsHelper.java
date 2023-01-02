package org.feup.biosignals.projectbiosignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;

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
		db.execSQL("CREATE TABLE dbAlerts (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, message TEXT);");
	}

	public void AddAlert(String title, String message){
		// Gets the data repository in write mode
		SQLiteDatabase db = getWritableDatabase();
		//Get current date
		Date currentTime = Calendar.getInstance().getTime();
		String date = DateFormat.getDateInstance().format(currentTime);
		// Create a new map of values, where column names are the keys
		ContentValues alertMessage = new ContentValues();
		alertMessage.put(DATE, date);
		alertMessage.put(TITLE, title);
		alertMessage.put(MESSAGE, message);
		// Insert the new row, returning the primary key value of the new row
		//long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
		db.insert(DATABASE_NAME, null, alertMessage);
	}

	public ArrayList<classAlertItem> getAlertsByDate() {
		SQLiteDatabase db = getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] cols = {TITLE, MESSAGE};

		//Get current date
		Date currentTime = Calendar.getInstance().getTime();
		String[] current_date = {DateFormat.getDateInstance().format(currentTime)};
		Cursor cursor = db.query(DATABASE_NAME, cols, "date=?", current_date, null, null, null);

		ArrayList<classAlertItem> info = new ArrayList<>();
		if (cursor.getCount() != 0) {
			while (cursor.moveToNext()) {
				classAlertItem row = new classAlertItem(cursor.getString(0),cursor.getString(1));
				info.add(row);
			}
			cursor.close();
		} else {
			classAlertItem row = new classAlertItem("No alerts today","---");
			info.add(row);
			cursor.close();
		}
		return info;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		android.util.Log.w("Constants", "Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS dailyAlerts");
		onCreate(db);
	}
}