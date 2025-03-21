package com.example.examenfinal2.fragments

import com.example.examenfinal2.adapter.OnClickCharacterListener
import com.example.examenfinal2.api.RickAndMortyCharacter

interface CharacterListener : OnClickCharacterListener {
    fun onCharacterSeleccionado(rickAndMortyCharacter: RickAndMortyCharacter)
}

