package com.rachma.dailynotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rachma.dailynotes.model.Note

// Untuk mendeklarasikan anotasi database untuk menentukan entitas dan mengatur versi
// Dan untuk menjadikan data class Note yang akan digunakan sebagai database, dan versi databasenya adalah 1
@Database(entities = [Note::class], version = 1, exportSchema = false)

// Untuk membuat abstract class NoteDB yang akan mewarisi fungsi dari class RoomDatabase
abstract class NoteRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {
            return INSTANCE ?: synchronized(this) {

                // Untuk membuat database yang bernama note_db
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_db"
                )
                    // Untuk mengijinkan Room untuk mengeksekusi tugas di thread utama
                    .allowMainThreadQueries()

                    // Untuk mengijinkan Room untuk membuat ulang tabel jika tidak ada migrasi yang ditemukan
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    // Mendeklarasikan function getNoteDao untuk mendapatkan NoteDao
    abstract fun getNoteDao() : NoteDao
}
