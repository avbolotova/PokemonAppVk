package com.example.pokemonappvk.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonappvk.data.remote.RetrofitInstance
import com.example.pokemonappvk.domain.PokeRepository
import com.example.pokemonappvk.data.repository.PokeRepositoryImpl
import com.example.pokemonappvk.domain.modelpokeimage.ModeImagePoke
import com.example.pokemonappvk.domain.modelpokelist.ModelListPoke
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel @AssistedInject constructor(application: Application) : AndroidViewModel(application) {
    private val repo: PokeRepository
    val myListPoke: MutableLiveData<Response<ModelListPoke>> = MutableLiveData()
    val myDetailsInfo: MutableLiveData<Response<ModeImagePoke>> = MutableLiveData()

    init {
        val api = RetrofitInstance.api
        repo = PokeRepositoryImpl(api)
    }

    fun getListPoke() {
        viewModelScope.launch {
            myListPoke.value = repo.getListPoke()
        }
    }

    fun getDetailsInfo(pokemonName: String) {
        viewModelScope.launch {
            myDetailsInfo.value = repo.getPokemonDetails(pokemonName)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }
}