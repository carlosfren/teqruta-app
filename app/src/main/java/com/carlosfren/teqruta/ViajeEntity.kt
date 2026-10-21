package com.carlosfren.teqruta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viajes")
data class ViajeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tecnico: String,
    val clinica: String,
    val kilometraje: String,
    val peaje: String,
    val fecha: String
)