package com.rachma.dailynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.rachma.dailynotes.db.NoteDao
import com.rachma.dailynotes.db.NoteRoomDatabase
import com.rachma.dailynotes.model.Note
import kotlinx.android.synthetic.main.activity_edit.*

// Untuk mendeklarasikan kelas yang bernama EditActivity
class EditActivity : AppCompatActivity() {

    // Yang artinya untuk mendeklarasikan variabel untuk mengedit data dengan menyertakan database dan daonya yaitu NoteRoomDatabase dan NoteDao
    val EDIT_NOTE_EXTRA = "edit_note_extra"
    private lateinit var note: Note
    private var isUpdate = false
    private lateinit var database: NoteRoomDatabase
    private lateinit var dao: NoteDao

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // Untuk memanggil database dan dao yang akan digunakan pada aplikasi ini
        database = NoteRoomDatabase.getDatabase(applicationContext)
        dao = database.getNoteDao()

        // Jika pada bagian edit untuk catatan tidak sama dengan null maka button delete akan ditampilkan
        // Dan akan pada bagian title dan body akan terisikan catatan
        if (intent.getParcelableExtra<Note>(EDIT_NOTE_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            note = intent.getParcelableExtra(EDIT_NOTE_EXTRA)
            edit_text_title.setText(note.title)
            edit_text_body.setText(note.body)

            edit_text_title.setSelection(note.title.length)

        }

        // Ketika  button_save  di klik maka pada bagian title dan body akan dijadikan string
        button_save.setOnClickListener {
            val title = edit_text_title.text.toString()
            val body = edit_text_body.text.toString()

            // Jika pada bagian title dan body kosong, maka akan menampilkan pesan toast berupa catatan tidak boleh kosong
            if (title.isEmpty() && body.isEmpty()){
                Toast.makeText(applicationContext, "Catatan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

            // Dan jika pada bagian title dan body tidak kosong, maka catatan akan disimpan
            else{
                if (isUpdate){
                    saveNote(Note(id = note.id, title = title, body = body))
                }
                else{
                    saveNote(Note(title = title, body = body))
                }
            }

            finish()
        }

        // Ketika button_delete diklik maka catatan akan dihapus
        button_delete.setOnClickListener {
            deleteNote(note)
            finish()
        }

    }

    // Untuk mendeklarasikan function saveNote
    private fun saveNote(note: Note){

        // Jika pada id catatan kosong, maka akan menjalankan function insert dan ketika menuliskan catatan akan disimpan
        if (dao.getById(note.id).isEmpty()){

            dao.insert(note)
        }

        // Jika pada id catatan tidak kosong, maka akan menjalankan function update an ketika menuliskan catatan akan diupdate
        else{

            dao.update(note)
        }

        // Untuk menampilkan pesan toast berupa catatan disimpan
        Toast.makeText(applicationContext, "Catatan disimpan", Toast.LENGTH_SHORT).show()

    }

    // Untuk mendeklarasikan function yang bernama deleteNote
    // Ketika catatan dihapus maka akan menampilkan pesan toast berupa catatan dihapus
    private fun deleteNote(note: Note){
        dao.delete(note)
        Toast.makeText(applicationContext, "Catatan dihapus", Toast.LENGTH_SHORT).show()
    }
}
