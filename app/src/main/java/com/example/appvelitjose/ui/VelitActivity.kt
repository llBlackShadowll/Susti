package com.example.appvelitjose.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appvelitjose.database.AppDatabase
import com.example.appvelitjose.databinding.ActivityVelitBinding
import com.example.appvelitjose.models.Alumno
import com.example.appvelitjose.repositories.AlumnoRepository
import kotlinx.coroutines.launch

class VelitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVelitBinding
    private lateinit var repository: AlumnoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVelitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar la base de datos y el repositorio
        val database = AppDatabase.getDatabase(this)
        repository = AlumnoRepository(database.alumnoDao())

        // Insertar datos iniciales
        lifecycleScope.launch {
            repository.insertAlumno(
                Alumno(codalumno = "sm72896374", password = "12345", nombreapellido = "Jose Velit Quintana", especialidad = "Ingeniería de Software")
            )
        }

        binding.btnLogin.setOnClickListener {
            val codAlumno = binding.etCodAlumno.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (codAlumno.isEmpty() || password.isEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = "Por favor, complete todos los campos."
                return@setOnClickListener
            }

            // Validar credenciales en la base de datos
            lifecycleScope.launch {
                val alumno = repository.getAlumno(codAlumno, password)
                if (alumno != null) {
                    binding.tvError.visibility = View.GONE
                    navegarAQuintanaActivity(alumno)
                } else {
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text = "Código o contraseña incorrectos."
                }
            }
        }
    }

    private fun navegarAQuintanaActivity(alumno: Alumno) {
        val intent = Intent(this, QuintanaActivity::class.java)
        intent.putExtra("nombreApellido", alumno.nombreapellido)
        intent.putExtra("especialidad", alumno.especialidad)
        startActivity(intent)
        finish()
    }
}