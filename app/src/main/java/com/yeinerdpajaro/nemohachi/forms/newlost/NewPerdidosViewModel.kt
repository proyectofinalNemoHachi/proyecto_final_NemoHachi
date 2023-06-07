package com.yeinerdpajaro.nemohachi.forms.newlost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeinerdpajaro.nemohachi.data.PerdidosRepository
import com.yeinerdpajaro.nemohachi.data.ResourceRemote
import com.yeinerdpajaro.nemohachi.model.Perdidos
import kotlinx.coroutines.launch

class NewPerdidosViewModel : ViewModel() {

    private val perdidosRepository = PerdidosRepository()
    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg

    private val _createPerdidosSuccess: MutableLiveData<String?> = MutableLiveData()
    val createPerdidosSuccess: LiveData<String?> = _createPerdidosSuccess

    fun validateData(
        petname: String,
        city: String,
        comment: String,
        isCatSelected: Boolean,
        isDogSelected: Boolean,
        isOtherSelected: Boolean
    ) {
        if (petname.isEmpty() || city.isEmpty() || comment.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
        } else {

            viewModelScope.launch {
                val perdidos = Perdidos(
                    petname = petname,
                    city = city,
                    comment = comment,
                    isCatSelected = isCatSelected,
                    isDogSelected = isDogSelected,
                    isOtherSelected = isOtherSelected
                )
                val result = perdidosRepository.savePerdidos(perdidos)
                result.let { resourceRemote ->
                    when (resourceRemote) {
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("Anuncio guardado")
                            _createPerdidosSuccess.postValue(resourceRemote.data)
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