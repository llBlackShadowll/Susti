package com.example.appvelitjose.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.appvelitjose.R
import com.example.appvelitjose.databinding.ActivityQuintanaBinding

class QuintanaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuintanaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuintanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Obtener datos enviados desde VelitActivity
        val nombreApellido = intent.getStringExtra("nombreApellido")
        val especialidad = intent.getStringExtra("especialidad")

        if (nombreApellido == null || especialidad == null) {
            // Si los datos no están disponibles, termina la actividad.
            finish()
            return
        }

        // Configurar el encabezado del NavigationView
        val headerView = binding.navView.getHeaderView(0)
        val tvNombreApellido = headerView.findViewById<TextView>(R.id.tvNombreApellido)
        val tvEspecialidad = headerView.findViewById<TextView>(R.id.tvEspecialidad)

        tvNombreApellido.text = nombreApellido
        tvEspecialidad.text = especialidad
    }
}