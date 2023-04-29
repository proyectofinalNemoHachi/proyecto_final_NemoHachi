package com.yeinerdpajaro.nemohachi.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yeinerdpajaro.nemohachi.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)


    }

}
