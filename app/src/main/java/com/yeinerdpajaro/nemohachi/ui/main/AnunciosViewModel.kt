package com.yeinerdpajaro.nemohachi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.yeinerdpajaro.nemohachi.model.Anuncio
import com.yeinerdpajaro.nemohachi.ui.signup.data.AnuncioRepository
import com.yeinerdpajaro.nemohachi.ui.signup.data.ResourceRemote
import kotlinx.coroutines.launch

class AnunciosViewModel : ViewModel() {

    private val anunciosRepository = AnuncioRepository()

    private var anuncioList: ArrayList<Anuncio> = ArrayList()

    private val _anunciosList: MutableLiveData<ArrayList<Anuncio>> = MutableLiveData()
    val anunciosList: LiveData<ArrayList<Anuncio>> = _anunciosList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg

    fun loadAnuncios() {
        anuncioList.clear()
        viewModelScope.launch {
            val result = anunciosRepository.loadSeries()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach { document ->
                            val anuncio = document.toObject<Anuncio>()
                            anuncio?.let { anuncioList.add(it) }
                        }
                        _anunciosList.postValue(anuncioList)
                    }

                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _errorMsg.postValue(msg)

                    }

                    else -> {

                    }
                }
            }
        }
    }

}