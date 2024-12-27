package com.example.appvelitjose.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbljose")
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val codalumno: String,
    val nombreapellido: String,
    val especialidad: String
)