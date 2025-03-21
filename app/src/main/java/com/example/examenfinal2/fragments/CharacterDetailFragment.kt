package com.example.examenfinal2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.examenfinal2.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibe los argumentos enviados desde el otro fragment
        val args = arguments
        val name = args?.getString("name")
        val status = args?.getString("status")
        val species = args?.getString("species")
        val type = args?.getString("type")
        val gender = args?.getString("gender")
        val image = args?.getString("image")

        // Asigna los valores a las vistas
        binding.tvNombre.text = "Nombre: $name"
        binding.tvEstado.text = "Estado: $status"
        binding.tvEspecie.text = "Especie: $species"
        binding.tvTipo.text = "Tipo: ${type?.ifEmpty { "Desconocido" }}"
        binding.tvGenero.text = "Género: $gender"

        Picasso.get().load(image)
            .placeholder(com.example.examenfinal2.R.drawable.ic_launcher_background)
            .into(binding.ivCharacter)
    }

    companion object {
        fun newInstance(character: com.example.examenfinal2.api.RickAndMortyCharacter): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()
            val bundle = Bundle().apply {
                putString("name", character.name)
                putString("status", character.status)
                putString("species", character.species)
                putString("type", character.type)
                putString("gender", character.gender)
                putString("image", character.image)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
