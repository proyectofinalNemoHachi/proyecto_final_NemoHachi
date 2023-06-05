package com.yeinerdpajaro.nemohachi.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yeinerdpajaro.nemohachi.databinding.ActivityProfileBinding
import com.yeinerdpajaro.nemohachi.model.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileBinding: ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        val view = profileBinding.root
        profileViewModel.loadUserInfo()

        profileViewModel.errorMsg.observe(this){errorMsg ->
            Toast.makeText(applicationContext,errorMsg,Toast.LENGTH_SHORT).show()
        }
        profileViewModel.userLoaded.observe(this){user->

            with(profileBinding){
                emailAddressTextView.text = "Correo: "+ user?.email
                nameEditText.setText(user?.name)

            }

        }



        setContentView(view)
    }



}