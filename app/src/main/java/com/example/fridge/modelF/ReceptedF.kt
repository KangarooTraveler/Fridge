package com.example.fridge.modelF


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "recepted_table")
data class ReceptedF(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Name: String,
    val Opisanie: String,
    val Text: String,
    val ColorR: String?
): Parcelable