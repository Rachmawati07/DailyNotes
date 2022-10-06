package com.rachma.dailynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachma.dailynotes.db.NoteRoomDatabase
import com.rachma.dailynotes.model.Note
import kotlinx.android.synthetic.main.activity_main.*

// Untuk mendeklarasikan class yang bernama MainActivity
class MainActivity : AppCompatActivity() {

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Untuk mendapatan catatan yang sebelumnya telah dibuat
        getNotesData()

        // Ketika catatan yang telah dibuat diklik maka akan menuju ke EditActivity
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }

    }

    // Untuk mendeklarasikan function getNotesData
    private fun getNotesData(){

        // Untuk mendapatkan database dan dao
        val database = NoteRoomDatabase.getDatabase(applicationContext)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<Note>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)

        // Jika pada listItem tidak kosong, maka tulisan pada text_view_note_empty akan hilang
        if (listItems.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }
        // Dan jika pada listItem kosong, maka tulisan pada text_view_note_empty akan dimunculkan
        else{
            text_view_note_empty.visibility = View.VISIBLE
        }
    }

    // Mendeklarasikan fungsi setupRecyclerView yang akan memanggil activity noteAdapter
    // Untuk menampilkan tampilan catatan kelayar
    private fun setupRecyclerView(listItems: ArrayList<Note>){
        recycler_view_main.apply {
            adapter = NoteAdapter(listItems, object : NoteAdapter.NoteListener{
                override fun OnItemClicked(note: Note) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_NOTE_EXTRA, note)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    // Untuk memanggil function yang bernama onResume
    // Dan catatan akan ditampilkan
    override fun onResume() {
        super.onResume()
        getNotesData()
    }
}
