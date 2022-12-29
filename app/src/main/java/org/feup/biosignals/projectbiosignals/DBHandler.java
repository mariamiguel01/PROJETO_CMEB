package org.feup.biosignals.projectbiosignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    // below variable is for our database name.
    private static final String DB_NAME = "BackSaver";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for the table that saves the values from snapki
    private static final String TABLE_EULER_ANGLES = "EulerAngles";
    // variables for this table:
    // below variable is for our id column.
    private static final String ID_COL = "id";
    // below variable is for the columns corresponding to the values of the accelerometer
    private static final String PITCH = "pitch";
    private static final String ROLL = "roll";
    private static final String YAW = "yaw";


    // below variable is for our course name column
    private static final String DATE_COL = "date";

    // creating a constructor for our database handler (does not work without this)
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_EULER_ANGLES+ " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PITCH + " REAL,"
                + ROLL + " REAL,"
                + YAW + " REAL,"
                + DATE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EULER_ANGLES);
        onCreate(db);
    }

}
