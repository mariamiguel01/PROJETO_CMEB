package org.feup.biosignals.projectbiosignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class DBManager {
    private static final String TABLE_EULER_ANGLES = "EulerAngles";
    private final SQLiteDatabase db;

    public DBManager(Context context) {
        db = (new DBHandler(context)).getWritableDatabase();
    }

    public void AddAngle(Double pitch, Double roll, Double yaw){
        ContentValues cv = new ContentValues();

        //Get current date
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);

        cv.put("pitch", pitch);
        cv.put("roll", roll);
        cv.put("yaw",yaw);
        cv.put("date", date);
        db.insertOrThrow("EulerAngles", null, cv);
    }





}
