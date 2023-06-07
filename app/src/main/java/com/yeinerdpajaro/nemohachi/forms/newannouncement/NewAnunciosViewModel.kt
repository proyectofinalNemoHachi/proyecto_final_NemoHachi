package com.yeinerdpajaro.nemohachi.forms.newannouncement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeinerdpajaro.nemohachi.data.AnunciosRepository
import com.yeinerdpajaro.nemohachi.data.ResourceRemote
import com.yeinerdpajaro.nemohachi.model.Anuncios
import kotlinx.coroutines.launch

class NewAnunciosViewModel : ViewModel() {
    private val anunciosRepository = AnunciosRepository()
    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg

    private val _createAnunciosSuccess: MutableLiveData<String?> = MutableLiveData()
    val createAnunciosSuccess: LiveData<String?> = _createAnunciosSuccess

    fun validateData(
        name: String,
        comment: String,

        ) {
        if (name.isEmpty() || comment.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
        } else {

            viewModelScope.launch {
                val anuncios = Anuncios(
                    name = name,
                    comment = comment,
                    )
                val result = anunciosRepository.saveAnuncio(anuncios)
                result.let { resourceRemote ->
                    when (resourceRemote) {
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("Anuncio guardado")
                            _createAnunciosSuccess.postValue(resourceRemote.data)
                        }

                        is ResourceRemote.Error -> {

                            val msg = resourceRemote.message
                            _errorMsg.postValue(msg)

                        }

                        else -> {

                        }
                    }
                }
            }

        }

    }
}