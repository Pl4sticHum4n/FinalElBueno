package com.example.finalelbueno.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalelbueno.database.DatabaseManager
import com.example.finalelbueno.database.MyAppDataSource
import com.example.finalelbueno.database.Pokemon
import kotlinx.coroutines.launch

class LeftViewModel:ViewModel() {
    val savedPokemon = MutableLiveData<List<Pokemon>>()
    fun getPokemons(){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.instance.database.pokemonDao()
            savedPokemon.value = MyAppDataSource(pokemonDao).getPokemons().value
        }
    }

    fun delete(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).delete(pokemon)
            getPokemons()
        }
    }
}