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
    private static final String TABLE_NAME = "SnapkiValues";

    // variables for this table:
    // below variable is for our id column.
    private static final String ID_COL = "id";
    // below variable is for the columns corresponding to the values of the accelerometer
    private static final String AX_COL = "ax";
    private static final String AY_COL = "ay";
    private static final String AZ_COL = "az";
    // below variable is for the values corresponding to the gyroscope
    private static final String GX_COL = "gx";
    private static final String GY_COL = "gy";
    private static final String GZ_COL = "gz";

    //below variable is for the column corresponding to the instant
    private static final String T_COL = "t";

    // below variable is for our course name column
    private static final String DATE_COL = "date";

    // creating a constructor for our database handler (does not work without this)
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AX_COL + " INTEGER,"
                + AY_COL + " INTEGER,"
                + AZ_COL + " INTEGER,"
                + GX_COL + " INTEGER,"
                + GY_COL + "INTEGER,"
                + GZ_COL + "INTEGER,"
                + DATE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(Integer ax, Integer ay, Integer az, Integer gx, Integer gy, Integer gz) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        //Get current date
        Date currentTime = Calendar.getInstance().getTime();
        String data = DateFormat.getDateInstance().format(currentTime);


        // on below line we are passing all values
        // along with its key and value pair.
        values.put(AX_COL, ax);
        values.put(AY_COL, ay);
        values.put(AZ_COL, az);
        values.put(GX_COL, gx);
        values.put(GY_COL, gy);
        values.put(GZ_COL, gz);
        values.put(DATE_COL, data);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
