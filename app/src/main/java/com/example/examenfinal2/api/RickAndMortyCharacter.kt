package com.example.examenfinal2.api

import androidx.room.Entity
import androidx.room.PrimaryKey

data class RickAndMortyResponse(
    val results: List<RickAndMortyCharacter>
)

@Entity(tableName = "characters")
data class RickAndMortyCharacter(
    @PrimaryKey val id: Int,
    val name:String,
    val status:String,
    val species:String,
    val type:String,
    val gender:String,
    val image:String
)