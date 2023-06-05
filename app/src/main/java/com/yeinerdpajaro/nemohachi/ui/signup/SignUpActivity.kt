package com.yeinerdpajaro.nemohachi.ui.signup


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yeinerdpajaro.nemohachi.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        val view = signUpBinding.root
        setContentView(view)

        signUpViewModel.errorMsg.observe(this){errorMsg ->
            Toast.makeText(applicationContext,errorMsg,Toast.LENGTH_SHORT).show()
        }
        signUpViewModel.isSuccessSignUp.observe(this){
            onBackPressedDispatcher.onBackPressed()
        }

        signUpBinding.registerButton.setOnClickListener {
            val email = signUpBinding.emailEditText.text.toString()
            val password = signUpBinding.passwordEditText.text.toString()
            val repPassword = signUpBinding.repPasswordEditText.text.toString()
            val name = signUpBinding.nameEditText.text.toString()


            signUpViewModel.validateFields(email,password,repPassword,name)

        }
    }
}
