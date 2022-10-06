package com.rachma.dailynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rachma.dailynotes.model.Note

// Untuk mendeklarasikan class yang bernama NoteAdapter
class NoteAdapter(
    private val listItems: ArrayList<Note>,
    private val listener: NoteListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    // Untuk memanggil kelas getItemCount
    override fun getItemCount(): Int {
        return listItems.size
    }

    // Untuk memanggil kelas onBindViewHolder dengan menyertakan NoteViewHolder
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listItems[position]

        // Ketika tampilan teks title dan body diklik maka akan menjalankan fungsi onRead(note) yaitu untuk membaca data
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.body
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    // Untuk mendeklarasikan class yang bernama NoteViewHolder yang nantinya akan ditampilkan dengan RecyclerView
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
    }

    // Untuk mendeklarasikan interface yang bernama NoteListener
    interface NoteListener{
        fun OnItemClicked(note: Note)
    }
}

