package com.yeinerdpajaro.nemohachi.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yeinerdpajaro.nemohachi.databinding.ActivitySignInBinding
import com.yeinerdpajaro.nemohachi.ui.main.TabbedActivity
import com.yeinerdpajaro.nemohachi.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var signInBinding: ActivitySignInBinding
    private lateinit var signSingInViewModel: SingInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        signSingInViewModel = ViewModelProvider(this)[SingInViewModel::class.java]
        val view = signInBinding.root
        setContentView(view)
        signSingInViewModel.errorMsg.observe(this){errorMsg ->
            Toast.makeText(applicationContext,errorMsg, Toast.LENGTH_SHORT).show()
        }
        signSingInViewModel.isSuccessSignIn.observe(this){errosMsg->
            val intent = Intent(this,TabbedActivity::class.java)
            startActivity(intent)
            finish()
        }
        signInBinding.createAnAccountButton.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        signInBinding.accessButton.setOnClickListener {
            val email = signInBinding.emailEditText.text.toString()
            val password = signInBinding.passwordEditText.text.toString()
            signSingInViewModel.validateField(email,password)

        }
    }
}