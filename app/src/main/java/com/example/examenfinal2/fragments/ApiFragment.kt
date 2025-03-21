package com.example.examenfinal2.fragments

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
import com.example.examenfinal2.databinding.FragmentApiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ApiFragment : Fragment(), OnClickCharacterListener {

    //Declaramos la variablesa utilizar
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentApiBinding
    private lateinit var linearLayoutManager: LinearLayoutManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApiBinding.inflate(inflater, container, false)
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
                    characterAdapter = CharacterAdapter(characters,this@ApiFragment)
                    linearLayoutManager = LinearLayoutManager(requireContext())
                    binding.recycler.apply {
                        layoutManager = linearLayoutManager
                        adapter = characterAdapter
                    }
                }
            } else {
                Log.e("gatos_LIST", "Error al obtener gatos")
            }
        }

    }



    // Para realizar onClick
    override fun onClick(rickAndMortyCharacter: RickAndMortyCharacter) {
        val inflater = LayoutInflater.from(requireContext())
        val layout = inflater.inflate(R.layout.toast_layout, requireActivity().findViewById(R.id.lytLayout))

        val mensaje = layout.findViewById<TextView>(R.id.texto)
        mensaje.text = "Seleccion: ${rickAndMortyCharacter.name}, ${rickAndMortyCharacter.status}, ${rickAndMortyCharacter.species}, ${rickAndMortyCharacter.type}, ${rickAndMortyCharacter.gender}"

        val toast = Toast(requireContext()).apply {
            duration = Toast.LENGTH_LONG
            view = layout
        }

        toast.show()
    }

}