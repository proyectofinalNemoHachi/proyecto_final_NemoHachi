package com.yeinerdpajaro.nemohachi.ui.announcement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.yeinerdpajaro.nemohachi.model.Anuncios
import com.yeinerdpajaro.nemohachi.data.AnunciosRepository
import com.yeinerdpajaro.nemohachi.data.ResourceRemote
import kotlinx.coroutines.launch

class AnunciosViewModel : ViewModel() {

    private val anunciosRepository = AnunciosRepository()

    private var anuncioList: ArrayList<Anuncios> = ArrayList()

    private val _anunciosList: MutableLiveData<ArrayList<Anuncios>> = MutableLiveData()
    val anunciosList: LiveData<ArrayList<Anuncios>> = _anunciosList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg

    fun loadAnuncios() {
        anuncioList.clear()
        viewModelScope.launch {
            val result = anunciosRepository.loadAnuncios()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach { document ->
                            val anuncios = document.toObject<Anuncios>()
                            anuncios?.let { anuncioList.add(it) }
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