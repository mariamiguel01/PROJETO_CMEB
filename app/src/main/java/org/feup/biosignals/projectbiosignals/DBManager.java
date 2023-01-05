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
        db = (new DBHandler(context)).getWritableDatabase(); }



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

    // function to use in statistics (get the pitch by the date)
    public List<Double> getPitchByDate() {
        List<Double> info = new ArrayList<>();
        //Get current date
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);

        Cursor cursor = db.rawQuery("Select * from EulerAngles WHERE date='"+date+"'" , null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                info.add(cursor.getDouble(1));
            }
        }
        return info;
    }

    // to calculate the daily pitch average
    public float calculateAverage(List <Double> dailyPitch) {
        float sum = 0;
        for (int i=0; i< dailyPitch.size(); i++) {
            sum += dailyPitch.get(i);
        }
        return sum / dailyPitch.size();
    }

    // to calculate the daily pitch average
    public float calculatePercentage(List <Double> dailyPitch) {
        float count = 0;
        for (int i=0; i< dailyPitch.size(); i++) {
            if(dailyPitch.get(i)>=10.0){
                count++;
            }
        }

        return count/dailyPitch.size();
    }

    // function to get the current month
    public String getCurrentMonth() {
        String month;
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);
        //String date = "04/01/2023";
        String[] splitDate = date.split("/");
        month = splitDate[1].trim();
        return month;
    };

    // function to get the current month
    public String getCurrentYear() {
        String year;
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);
        //String date = "04/01/2023";
        String[] splitDate = date.split("/");
        year = splitDate[2].trim();
        return year;
    };

    
    // function to get the last value in the database
    public String getPitchPB() {
       Cursor cursor = db.rawQuery("Select * from EulerAngles", null);
       cursor.moveToLast();
       String pitch = cursor.getString(1);
       return pitch;
    }

    // returns the number of data for that day that allows to count the total time in that day
    public int getDailySpent() {
        int count;
        Date currentTime = Calendar.getInstance().getTime();
        String date = DateFormat.getDateInstance().format(currentTime);
        Cursor cursor = db.rawQuery("Select * from EulerAngles WHERE date='"+date+"'", null);
        count = cursor.getCount();
        return count;
    }

    // function to get the last value in the database
    public Double getCalibration() {
        Cursor cursor = db.rawQuery("Select * from EulerAngles", null);
        cursor.moveToLast();
        Double pitch = cursor.getDouble(1);
        return pitch;
    }

    // function to use in statistics (get the pitch by the date)
    public List<Double> getPitchForHist(String month, String year, String day) {
        List<Double> info = new ArrayList<>();
        //Get current date with the variables introduced by the user
        String date = day + "/" + month + "/" + year;

        Cursor cursor = db.rawQuery("Select * from EulerAngles WHERE date='"+date+"'" , null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                info.add(cursor.getDouble(1));
            }
        }
        return info;
    }

















    
}
