package com.example.examenfinal2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.examenfinal2.api.RickAndMortyCharacter

@Dao
interface CharacterDao {

    @Insert
    fun insertAll(characters : RickAndMortyCharacter)
    @Delete
    fun delete(character : RickAndMortyCharacter)
    @Query("SELECT * FROM characters")
    fun getAll(): List<RickAndMortyCharacter>

    @Query("SELECT * FROM characters WHERE status = 'Alive'")
    fun getAliveCharacters(): List<RickAndMortyCharacter>

}