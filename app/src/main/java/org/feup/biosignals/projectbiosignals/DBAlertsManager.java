package org.feup.biosignals.projectbiosignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.feup.biosignals.projectbiosignals.ui.alerts.classAlertItem;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBAlertsManager {
    private static final String DATABASE_NAME="dbAlerts";
    public static final String DATE="date";
    public static final String TITLE="title";
    public static final String MESSAGE="message";
    private final SQLiteDatabase db;

    public DBAlertsManager(Context context) {
        db = (new DatabaseALertsHelper(context)).getWritableDatabase();
    }


    public void AddAlert(String title, String message){
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
        db.insert("table_alerts", null, alertMessage);
    }

    public ArrayList<classAlertItem> getAlertsByDate() {
        Log.i("db helper", "getAlert");
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] cols = {TITLE, MESSAGE};

        //Get current date
        Date currentTime = Calendar.getInstance().getTime();
        String[] current_date = {DateFormat.getDateInstance().format(currentTime)};
        Cursor cursor = db.query("table_alerts", cols, "date=?", current_date, null, null, null);

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
}
