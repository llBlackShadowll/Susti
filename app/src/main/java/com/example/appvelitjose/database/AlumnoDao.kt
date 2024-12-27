package com.example.appvelitjose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appvelitjose.models.Alumno

@Dao
interface AlumnoDao {

    @Insert
    suspend fun insertAlumno(alumno: Alumno)

    @Query("SELECT * FROM tbljose WHERE codalumno = :codalumno AND password = :password LIMIT 1")
    suspend fun getAlumno(codalumno: String, password: String): Alumno?
}