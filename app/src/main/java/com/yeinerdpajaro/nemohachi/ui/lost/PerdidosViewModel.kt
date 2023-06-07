package com.yeinerdpajaro.nemohachi.ui.lost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.yeinerdpajaro.nemohachi.data.PerdidosRepository
import com.yeinerdpajaro.nemohachi.data.ResourceRemote
import com.yeinerdpajaro.nemohachi.model.Perdidos
import kotlinx.coroutines.launch

class PerdidosViewModel : ViewModel() {

    private val perdidosRepository = PerdidosRepository()

   private var perdidosList: ArrayList<Perdidos> = ArrayList()

    private val _perdidosList: MutableLiveData<ArrayList<Perdidos>> = MutableLiveData()
    val perdidoList: LiveData<ArrayList<Perdidos>> = _perdidosList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg


    fun loadPerdidos() {
        perdidosList.clear()
        viewModelScope.launch {
            val result = perdidosRepository.loadPerdidos()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach { document ->
                            val perdidos = document.toObject<Perdidos>()
                            perdidos?.let { perdidosList.add(it) }
                        }
                        _perdidosList.postValue(perdidosList)
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