package com.example.examenfinal2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examenfinal2.api.RickAndMortyCharacter
import com.example.examenfinal2.databinding.ActivityCharacterDetailBinding
import com.example.examenfinal2.fragments.CharacterDetailFragment
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recibimos los datos enviados
        val name = intent.getStringExtra("name")
        val status = intent.getStringExtra("status")
        val species = intent.getStringExtra("species")
        val type = intent.getStringExtra("type")
        val gender = intent.getStringExtra("gender")
        val image = intent.getStringExtra("image")

        // Asignamos los valores a las vistas
        binding.tvNombre.text = "Nombre: $name"
        binding.tvEstado.text = "Estado: $status"
        binding.tvEspecie.text = "Especie: $species"
        binding.tvTipo.text = "Tipo: ${type?.ifEmpty { "Desconocido" }}"
        binding.tvGenero.text = "Género: $gender"

        Picasso.get().load(image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.ivCharacter)
    }
    //Esto es para pasar de activity a fragment
    /*
    override fun onClick(rickAndMortyCharacter: RickAndMortyCharacter) {

        val fragment = CharacterDetailFragment.newInstance(rickAndMortyCharacter)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment) // Usa el ID correcto
            .addToBackStack(null)
            .commit()
    }

     */
}
