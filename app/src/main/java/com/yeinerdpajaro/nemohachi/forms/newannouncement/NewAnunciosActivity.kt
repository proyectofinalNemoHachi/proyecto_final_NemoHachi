package com.yeinerdpajaro.nemohachi.forms.newannouncement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yeinerdpajaro.nemohachi.databinding.ActivityNewAnunciosBinding

class NewAnunciosActivity : AppCompatActivity() {
    private lateinit var newAnunciosBinding: ActivityNewAnunciosBinding
    private lateinit var newAnunciosViewModel: NewAnunciosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newAnunciosBinding = ActivityNewAnunciosBinding.inflate(layoutInflater)
        newAnunciosViewModel = ViewModelProvider(this)[NewAnunciosViewModel::class.java]
        val view = newAnunciosBinding.root
        setContentView(view)

        with(newAnunciosBinding){
            registerButton.setOnClickListener{
                newAnunciosViewModel.validateData(
                    nameEditText.text.toString(),
                    commentEditText.text.toString(),
                )
            }
        }
        newAnunciosViewModel.errorMsg.observe(this){errorMsg ->
            Toast.makeText(applicationContext,errorMsg, Toast.LENGTH_SHORT).show()
        }
        newAnunciosViewModel.createAnunciosSuccess.observe(this){
            onBackPressedDispatcher.onBackPressed()
        }
    }
}