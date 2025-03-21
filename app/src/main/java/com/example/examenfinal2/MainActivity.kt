package com.example.examenfinal2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.examenfinal2.databinding.ActivityMainBinding
import com.example.examenfinal2.fragments.ApiFragment
import com.example.examenfinal2.fragments.Evento1Fragment
import com.example.examenfinal2.fragments.Evento2Fragment
import com.example.examenfinal2.fragments.EventoPestanaFragment
import com.example.examenfinal2.fragments.EventoPestanaFragment2
import com.example.examenfinal2.fragments.InicioFragment
import com.example.examenfinal2.fragments.RoomFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Inicializa DrawerLayout
        drawerLayout = binding.drawerLayout



    //Inicializamos con el HomeFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InicioFragment())
            .commit()



        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                /*
                R.id.navigation_api -> {
                    replaceFragment(ApiFragment())
                }
                */
                R.id.navigation_room -> {
                    replaceFragment(RoomFragment())
                }
                R.id.navigation_eventos1 -> {
                    replaceFragment(Evento1Fragment())
                }
                R.id.navigation_eventos2 -> {
                    replaceFragment(Evento2Fragment())
                }
                R.id.navigation_eventos3 -> {
                    replaceFragment(EventoPestanaFragment())
                }
                R.id.navigation_eventos4 -> {
                    replaceFragment(EventoPestanaFragment2())
                }
            }
            false
        }

        // cosas del tolbar
        val toolBar: androidx.appcompat.widget.Toolbar =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        setSupportActionBar(toolBar)


        // Configurar el botón de hamburguesa para abrir/cerrar el menú lateral
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

    }


    // cosas del tolbar

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.api -> {
                navigateToFragment(ApiFragment()) // Navega al fragmento API
                true
            }

            R.id.room -> {
                navigateToFragment(RoomFragment()) // Navega al fragmento Room
                true
            }

            R.id.eventos1 -> {
                navigateToFragment(Evento1Fragment()) // Evento 1 Fragment
                true
            }

            R.id.eventoss2 -> {
                navigateToFragment(Evento2Fragment()) // Evento 2 Fragment
                true
            }

            R.id.eventos3 -> {
                navigateToFragment(EventoPestanaFragment()) // Evento 3 Fragment
                true
            }

            R.id.eventos4 -> {
                navigateToFragment(EventoPestanaFragment2()) // Evento 4 Fragment
                true
            }

            R.id.activity2 -> {
                navigateToActivity(PruebaActivity::class.java) // Inicia una nueva Activity
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    // 🔄 Función para navegar a un Fragment
    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment) // Asegúrate de que este ID sea correcto
            .addToBackStack(null) // Para poder volver atrás con el botón back
            .commit()
    }

    // 🚀 Función para iniciar una nueva Activity
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }




}