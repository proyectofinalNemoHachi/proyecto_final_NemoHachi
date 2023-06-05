package com.yeinerdpajaro.nemohachi.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeinerdpajaro.nemohachi.ui.signup.data.UserRepository

class SplashViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val _isSessionActive: MutableLiveData<Boolean> = MutableLiveData()
    val isSessionActive: LiveData<Boolean> = _isSessionActive

    fun validateSessionActive() {
        val isSessionActive = userRepository.isSessionActive()
        _isSessionActive.postValue(isSessionActive)

    }

}