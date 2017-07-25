package com.example.nikos.habittracker.data;

/**
 * Created by NIKOS
 */

import android.provider.BaseColumns;

/**
 * API Contract for the HabitTracker app.
 */
public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitEntry implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table)- Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit - Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME = "name";

        /**
         * Date habit happens.- Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE = "date";

        /**
         * Habit Duration in minutes - Type: INTEGER
         */
        public final static String COLUMN_HABIT_DURATION = "duration";

        /**
         * Habit cost in euros - Type: INTEGER
         */
        public final static String COLUMN_HABIT_COST = "cost";

        /**
         * Habit Evaluation - Values 1-5 - Type: INTEGER
         */
        public final static String COLUMN_HABIT_EVALUATION = "evaluation";

        /**
         * Possible values for the evaluation of the habit.
         */
        public static final int HABIT_POOR = 1;
        public static final int HABIT_GOOD = 2;
        public static final int HABIT_VERY_GOOD = 3;
        public static final int HABIT_EXTRA_GOOD = 4;
        public static final int HABIT_PERFECT = 5;
    }
}