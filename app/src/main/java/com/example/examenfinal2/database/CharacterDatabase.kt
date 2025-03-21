package com.example.examenfinal2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examenfinal2.api.RickAndMortyCharacter
import com.example.examenfinal2.dao.CharacterDao

@Database(entities = [RickAndMortyCharacter::class], version = 1)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
}