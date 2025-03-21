package com.example.examenfinal2.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfinal2.R
import com.example.examenfinal2.adapter.CharacterAdapter
import com.example.examenfinal2.adapter.OnClickCharacterListener
import com.example.examenfinal2.api.RetrofitClient
import com.example.examenfinal2.api.RickAndMortyCharacter
import com.example.examenfinal2.databinding.DialogPersonalBinding
import com.example.examenfinal2.databinding.FragmentApiBinding
import com.example.examenfinal2.databinding.FragmentEvento2Binding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Evento2Fragment : Fragment(), OnClickCharacterListener {

    //Declaramos la variablesa utilizar
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentEvento2Binding
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEvento2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.api.getAllCharacters()

            // Verifica si la respuesta fue exitosa
            if (response.results != null) {
                val characters = response.results

                // Log para mostrar cada personaje recibido
                characters.forEach { character ->
                    Log.d("CHARACTER_LIST", "Nombre: ${character.name}, Estado: ${character.status}, Especie: ${character.species}")
                }

                withContext(Dispatchers.Main) {
                    characterAdapter = CharacterAdapter(characters,this@Evento2Fragment)
                    linearLayoutManager = LinearLayoutManager(requireContext())
                    binding.recycler.apply {
                        layoutManager = linearLayoutManager
                        adapter = characterAdapter
                    }
                }
            } else {
                Log.e("CHARACTER_LIST", "Error al obtener personajes")
            }
        }

    }



    // Para realizar onClick
    override fun onClick(rickAndMortyCharacter: RickAndMortyCharacter) {

        // Cargar la imagen con Picasso
        Picasso.get()
            .load(rickAndMortyCharacter.image)
            .placeholder(R.drawable.ic_launcher_background) // Opcional: imagen mientras carga
            .error(R.drawable.portada_rickand_morty)             // Opcional: imagen si hay error
            .into(binding.imagen)

        // Actualizar los TextViews con la info del personaje
        binding.tvNombre.text = "Nombre: ${rickAndMortyCharacter.name}"
        binding.tvEstado.text = "Estado: ${rickAndMortyCharacter.status}"
        binding.tvEspecie.text = "Especie: ${rickAndMortyCharacter.species}"
        binding.tvTipo.text = "Tipo: ${rickAndMortyCharacter.type.ifEmpty { "Desconocido" }}"
        binding.tvGenero.text = "Género: ${rickAndMortyCharacter.gender}"
        // Inflamos el binding del layout del diálogo
        val dialogBinding = DialogPersonalBinding.inflate(layoutInflater)

        // Asignamos datos con binding
        with(dialogBinding) {
            Picasso.get()
                .load(rickAndMortyCharacter.image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView1)

            tvnombre.text = "Nombre: ${rickAndMortyCharacter.name}"
            tvEstado.text = "Estado: ${rickAndMortyCharacter.status}"
            tvEspecie.text = "Especie: ${rickAndMortyCharacter.species}"
            tvTipo.text = "Tipo: ${rickAndMortyCharacter.type.ifEmpty { "Desconocido" }}"
            tvGenero.text = "Género: ${rickAndMortyCharacter.gender}"
        }

        // Mostrar el diálogo con la vista de binding
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Detalles del Personaje")
            .setView(dialogBinding.root)  // Aquí se usa binding.root
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }





}