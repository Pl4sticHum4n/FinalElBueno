package com.example.finalelbueno.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.finalelbueno.R
import com.example.finalelbueno.database.Pokemon
import com.example.finalelbueno.databinding.FragmentLeftBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class LeftFragment : Fragment() {
    private lateinit var queue: RequestQueue


//    fun generateData(): List<Pokemon>{
//        queue = Volley.newRequestQueue(context)
//        val pokemons = mutableListOf<Pokemon>()
//        for(x in 1..11){
//            val url = "https://pokeapi.co/api/v2/pokemon/${x}"
//            val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response->
//                pokemons.add(Pokemon("${response.getString("id").toInt()}", "${response.getString("name")}","${response.getJSONArray("stats").getJSONObject(0).getString("base_stat")}", "${response.getJSONArray("stats").getJSONObject(1).getString("base_stat")}","${response.getJSONArray("stats").getJSONObject(3).getString("base_stat")}","${response.getJSONArray("stats").getJSONObject(2).getString("base_stat")}","${response.getJSONArray("stats").getJSONObject(4).getString("base_stat")}","${response.getJSONArray("stats").getJSONObject(5).getString("base_stat")}","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${x}.png"))
//                //val img = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${x}.png"
//                //Picasso.get().load(img).into(binding.pkmnGif)
//            },
//                Response.ErrorListener { errorMessage ->
//                    Log.d("JSONResponse", "error: $errorMessage")
//                }
//            )
//            queue.add(jsonRequest)
//        }
//        return pokemons
//    }

    private lateinit var binding: FragmentLeftBinding
    private val leftViewModel: LeftViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeftBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receiverPokemon = arguments?.getString("namePokemon")
        binding.myPokemons.layoutManager = LinearLayoutManager(view?.context)


        leftViewModel.getPokemons()
        leftViewModel.savedPokemon.observe(viewLifecycleOwner, { pokemonList ->
            if (!pokemonList.isNullOrEmpty()){
                val adapter = LeftAdapter(pokemonList, leftViewModel)
                binding.myPokemons.adapter = adapter
                for(savedpkmn in pokemonList){
                    Log.d("obtainedpokemons", "id registro: ${savedpkmn.PkmnID}, pokemon de fragment: ${savedpkmn.PkmnName}, sprite de pokemon: ${savedpkmn.PkmnSprite}")
                }
            }else{
                Log.d("obtainedpokemons", "no hay nada por ver")
            }
        })
        //leftViewModel.delete()

    }

}