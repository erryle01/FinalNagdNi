package com.example.finalnagdni.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PetDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "pets.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "pets"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_BREED = "breed"
        private const val COLUMN_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_BREED TEXT, "
                + "$COLUMN_AGE INTEGER)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPet(pet: Pet): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, pet.name)
        contentValues.put(COLUMN_BREED, pet.breed)
        contentValues.put(COLUMN_AGE, pet.age)

        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllPets(): List<Pet> {
        val petList = mutableListOf<Pet>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val pet = Pet(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BREED)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                )
                petList.add(pet)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return petList
    }
}
