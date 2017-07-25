package com.example.nikos.habittracker;

/**
 * Created by NIKOS
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nikos.habittracker.data.HabitContract;
import com.example.nikos.habittracker.data.HabitDbHelper;

import static com.example.nikos.habittracker.data.HabitDbHelper.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        //Inputs some dummy data into database when MainActivity is Created
        insertHabit("jogging",
                "20-07-2017",
                30,
                0,
                1);

        //Read the table records and write into the logs
        Cursor readCursor = queryAll();
        try {
            while (readCursor.moveToNext()) {
                Log.v(LOG_TAG,
                        "HABIT: "
                                + readCursor.getInt(0) + " / "     //Habit _id
                                + readCursor.getString(1) + " / "  //Habit Name
                                + readCursor.getString(2) + " / "  //Habit Date
                                + readCursor.getInt(3) + " / "     //Habit Duration
                                + readCursor.getInt(4) + " / "     //Habit cost
                                + readCursor.getInt(5));          //Habit evaluation
            }
        } finally {
            //Closing cursor now that we are done
            readCursor.close();
        }
    }

    /**
     * This method inserts Creates Habit table
     * @param mName - Name of the habit
     * @param mDate - Date when it has happened (e.g. 2017-07-07)
     * @param mDuration - Duration of the habit (e.g. 50)
     * @param mCost - Habit Cost in Euros (e.g. 30)
     * @param mEvaluation - Habit evaluation stars 1-5
     */
    private void insertHabit(String mName, String mDate, int mDuration, int mCost, int mEvaluation) {
        //Gets the data respository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, mName);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, mDate);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DURATION, mDuration);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_COST, mCost);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_EVALUATION, mEvaluation);
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.v("CatalogActivity", "New row ID " + newRowId);
    }

    public Cursor queryAll() {
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_DATE,
                HabitContract.HabitEntry.COLUMN_HABIT_DURATION,
                HabitContract.HabitEntry.COLUMN_HABIT_COST,
                HabitContract.HabitEntry.COLUMN_HABIT_EVALUATION
        };

        // Perform a query on the pets table
        return sqLiteDatabase.query(
                HabitContract.HabitEntry.TABLE_NAME, // The table to query
                projection,                          // The columns to return
                null,                                // The columns for the WHERE clause
                null,                                // The values for the WHERE clause
                null,                                // Don't group the rows
                null,                                // Don't filter by row groups
                null                                 // The sort order
        );
    }
}