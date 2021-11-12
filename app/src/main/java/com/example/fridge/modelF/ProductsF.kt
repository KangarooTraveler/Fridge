package com.example.fridge.modelF

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "products_table")
data class ProductsF (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameP: String,
    val countP: Int,
    val dateP: String?,
    val countD: Int?
): Parcelable