package com.yeinerdpajaro.nemohachi.ui.newAnuncio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeinerdpajaro.nemohachi.model.Anuncio
import com.yeinerdpajaro.nemohachi.ui.signup.data.AnuncioRepository
import com.yeinerdpajaro.nemohachi.ui.signup.data.ResourceRemote
import com.yeinerdpajaro.nemohachi.ui.signup.data.UserRepository
import kotlinx.coroutines.launch

class NewAnuncioViewModel : ViewModel() {

    private val anuncioRepository = AnuncioRepository()
    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg

    private val _createAnuncioSucces: MutableLiveData<String?> = MutableLiveData()
    val createAnuncioSuccess: LiveData<String?> = _createAnuncioSucces

    fun validateData(
        name: String,
        comment: String,
        isCatSelected: Boolean,
        isDogSelected: Boolean,
        isOtherSelected: Boolean
    ) {
        if (name.isEmpty() || comment.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
        } else {

            viewModelScope.launch {
                val anuncio = Anuncio(
                    name = name,
                    comment = comment,
                    isCatSelected = isCatSelected,
                    isDogSelected = isDogSelected,
                    isOtherSelected = isOtherSelected
                )
                val result = anuncioRepository.saveSerie(anuncio)
                result.let { resourceRemote ->
                    when (resourceRemote) {
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("Anuncio guardado")
                            _createAnuncioSucces.postValue(resourceRemote.data)
                        }

                        is ResourceRemote.Error -> {

                        }

                        else -> {

                        }
                    }
                }
            }

        }

    }

}