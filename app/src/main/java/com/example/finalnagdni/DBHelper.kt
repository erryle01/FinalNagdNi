package com.example.finalnagdni



import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Create tables
        db?.execSQL("CREATE TABLE appointments (id INTEGER PRIMARY KEY, date TEXT, time_slot TEXT, user_id INTEGER, is_booked INTEGER)")
        db?.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, username TEXT, role TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Upgrade logic if needed
    }

    fun insertAppointment(date: String, timeSlot: String, userId: Int): Long {
        val values = ContentValues().apply {
            put("date", date)
            put("time_slot", timeSlot)
            put("user_id", userId)
            put("is_booked", 0)
        }
        return writableDatabase.insert("appointments", null, values)
    }

    fun getAppointmentsForDate(date: String): Cursor {
        val db = readableDatabase
        val columns = arrayOf("id", "time_slot")
        val selection = "date = ?"
        val selectionArgs = arrayOf(date)
        return db.query("appointments", columns, selection, selectionArgs, null, null, null)
    }

    companion object {
        private const val DATABASE_NAME = "appointments.db"
        private const val DATABASE_VERSION = 1
    }
}
