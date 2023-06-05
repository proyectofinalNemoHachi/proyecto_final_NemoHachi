package com.yeinerdpajaro.nemohachi.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeinerdpajaro.nemohachi.data.ResourceRemote
import com.yeinerdpajaro.nemohachi.data.UserRepository
import com.yeinerdpajaro.nemohachi.model.User
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: MutableLiveData<String?> = _errorMsg


    private val _isSuccessSignUp: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignUp: LiveData<Boolean> = _isSuccessSignUp

    fun validateFields(email: String, password: String, repPassword: String, name: String) {
        if (email.isEmpty() || password.isEmpty() || repPassword.isEmpty() || name.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
        } else {
            if (password.length < 6) {
                _errorMsg.value = "La contraseña debe tener mínimo 6 dígitos"
            } else {
                if (password != repPassword) {
                    _errorMsg.value = "Las contraseñas deben ser iguales"
                }
                else {

                    viewModelScope.launch {
                       val result = userRepository.signUpUser(email, password)
                        result.let { resourceRemote ->
                            when(resourceRemote){
                                is ResourceRemote.Success ->{

                                    //TODO crear el usuario en BD
                                    val user = User(uid = resourceRemote.data,
                                        name =name,
                                        email = email
                                    )
                                    //TODO Almacenar el usuario en BD
                                    createUser(user)
                                       _isSuccessSignUp.postValue(true)
                                }
                                is ResourceRemote.Error ->{
                                    var msg = resourceRemote.message
                                    when(resourceRemote.message){
                                        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de Internet"
                                        "The email address is already in use by another account." -> msg = "Ya existe una cuenta con este correo electrónico"
                                        "The email address is badly formatted." -> msg = "El correo electrónico está mal escrito"

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
        }

    }

    private fun createUser(user: User) {
        viewModelScope.launch {
            val result = userRepository.createUser(user)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _isSuccessSignUp.postValue(true)
                        _errorMsg.postValue("¡Registro Exitoso!")
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