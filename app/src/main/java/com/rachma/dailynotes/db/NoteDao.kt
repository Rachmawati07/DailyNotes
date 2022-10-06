package com.rachma.dailynotes.db

import androidx.room.*
import com.rachma.dailynotes.model.Note

// Untuk menjadikan interface NoteDao sebagai DAO
@Dao

// Untuk mendeklarasikan interface yang bernama NoteDao
interface NoteDao {

    // Untuk menambahkan anotation @Insert untuk menambah data, lalu membuat fungsi addNote()
    @Insert
    fun insert(note: Note)

    // Untuk menambahkan anotation @Update untuk mengubah data, lalu membuat fungsi updateNote()
    @Update
    fun update(note: Note)

    // Untuk menambahkan anotation @Delete untuk menambah data, lalu membuat fungsi deleteNote()
    @Delete
    fun delete(note: Note)

    // Untuk menampilkan data dari tabel note, lalu membuat fungsi getNotes() yang berisi data dari kelas Note
    @Query("SELECT * FROM notes")
    fun getAll() : List<Note>

    // Untuk menampilkan data dengan id tertentu, lalu membuat fungsi getNotes() dengan parameter note_id untuk mengambil id data
    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Int) : List<Note>
}