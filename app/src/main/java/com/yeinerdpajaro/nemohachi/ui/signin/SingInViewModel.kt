package com.yeinerdpajaro.nemohachi.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeinerdpajaro.nemohachi.data.ResourceRemote
import com.yeinerdpajaro.nemohachi.data.UserRepository
import kotlinx.coroutines.launch

class SingInViewModel:ViewModel() {

    private val userRepository = UserRepository()
    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg : MutableLiveData<String?> = _errorMsg

    private val _isSuccessSignIn: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignIn: LiveData<Boolean> = _isSuccessSignIn

    fun validateField(email: String, password: String) {

        if(email.isEmpty() || password.isEmpty()){
            _errorMsg.value = "Debe digitar todos los campos"
        }else{
            viewModelScope.launch {

                when(val resourceRemote = userRepository.signInUser(email,password)){
                    is ResourceRemote.Success ->{
                        _isSuccessSignIn.postValue(true)
                    }
                    is ResourceRemote.Error ->{
                        var msg = resourceRemote.message
                        when(resourceRemote.message){
                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de Internet"
                            "The email address is badly formatted." -> msg = "El correo electrónico está mal escrito"
                            "The password is invalid or the user does not have a password." -> msg = "La contraseña es invalida o el correo electrónico no tiene una contraseña"
                        }
                        _errorMsg.postValue(msg)

                    }
                    else ->{

                    }
                }
            }
        }

    }
}