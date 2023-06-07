package com.yeinerdpajaro.nemohachi.forms.newlost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yeinerdpajaro.nemohachi.databinding.ActivityNewLostBinding

class NewPerdidosActivity : AppCompatActivity() {

    private lateinit var newPerdidosBinding: ActivityNewLostBinding
    private lateinit var newPerdidosViewModel: NewPerdidosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newPerdidosBinding = ActivityNewLostBinding.inflate(layoutInflater)
        newPerdidosViewModel = ViewModelProvider(this)[NewPerdidosViewModel::class.java]
        val view = newPerdidosBinding.root
        setContentView(view)

        with(newPerdidosBinding){
            registerButton.setOnClickListener{
                newPerdidosViewModel.validateData(
                    namePetEditText.text.toString(),
                    nameCityEditText.text.toString(),
                    commentEditText.text.toString(),
                    catCheckBox.isChecked,
                    dogCheckBox.isChecked,
                    otherCheckBox.isChecked
                )
            }
        }
        newPerdidosViewModel.errorMsg.observe(this){errorMsg ->
            Toast.makeText(applicationContext,errorMsg, Toast.LENGTH_SHORT).show()
        }
        newPerdidosViewModel.createPerdidosSuccess.observe(this){
            onBackPressedDispatcher.onBackPressed()
        }
    }

}