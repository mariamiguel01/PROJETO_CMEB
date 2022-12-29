package org.feup.biosignals.projectbiosignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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


    // function to use in statistics (get the data by the date)
    public List<List<String>> getListByDate() {
        List<List<String>> info = new ArrayList<>();
        //Get current date
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);
        Cursor cursor = db.rawQuery("Select * from EulerAngles WHERE date='"+date+"'" , null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < cursor.getColumnCount(); i++)
                    row.add(cursor.getString(i));
                info.add(row);
            }
        }
        return info;
    }

    
}
