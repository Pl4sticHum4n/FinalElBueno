package com.example.finalelbueno.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalelbueno.database.DatabaseManager
import com.example.finalelbueno.database.MyAppDataSource
import com.example.finalelbueno.database.Pokemon
import kotlinx.coroutines.launch

class RightViewModel: ViewModel() {
    fun save(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).save(pokemon)
        }
    }
}