package com.example.appvelitjose.repositories

import com.example.appvelitjose.database.AlumnoDao
import com.example.appvelitjose.models.Alumno

class AlumnoRepository(private val alumnoDao: AlumnoDao) {

    // Inserta un alumno en la base de datos
    suspend fun insertAlumno(alumno: Alumno) {
        alumnoDao.insertAlumno(alumno)
    }

    // Obtiene un alumno según su código y contraseña
    suspend fun getAlumno(codalumno: String, password: String): Alumno? {
        return alumnoDao.getAlumno(codalumno, password)
    }
}