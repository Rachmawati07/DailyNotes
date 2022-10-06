package com.rachma.dailynotes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Untuk mendeklarasikan anotasi entitas untuk menentukan nama tabelnya yaitu notes
@Entity(tableName = "notes")

//Untuk mendeklarasikan anotasi parcelable untuk membuat objek parcelable
@Parcelize
data class Note(

    // Untuk mendeklrasikan anotasi primarikey untuk menentukan primary keynya yaitu id
    // Untuk mendeklrasikan anotasi ColumnInfo untuk menentukan nama kolomnya yaitu title dan body
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "body") var body: String = ""
) : Parcelable {
}