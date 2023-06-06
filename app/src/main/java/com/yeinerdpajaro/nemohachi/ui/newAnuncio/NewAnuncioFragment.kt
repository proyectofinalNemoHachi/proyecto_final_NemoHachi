package com.yeinerdpajaro.nemohachi.ui.newAnuncio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yeinerdpajaro.nemohachi.R
import com.yeinerdpajaro.nemohachi.databinding.FragmentNewAnuncioBinding

class NewAnuncioFragment : Fragment() {

    companion object {
        fun newInstance() = NewAnuncioFragment()
    }

    private lateinit var newAnuncioViewModel: NewAnuncioViewModel
    private lateinit var newAnuncioBinding: FragmentNewAnuncioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newAnuncioBinding =  FragmentNewAnuncioBinding.inflate(inflater, container, false)
        newAnuncioViewModel = ViewModelProvider(this)[NewAnuncioViewModel::class.java]

        val view = newAnuncioBinding.root

        with(newAnuncioBinding){
            registerButton.setOnClickListener{
                newAnuncioViewModel.validateData(
                    nameEditText.text.toString(),
                    commentEditText.text.toString(),
                    catCheckBox.isChecked,
                    dogCheckBox.isChecked,
                    otherCheckBox.isChecked
                )
            }
        }
        newAnuncioViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg ->
            Toast.makeText(requireContext(),errorMsg, Toast.LENGTH_SHORT).show()
        }

        newAnuncioViewModel.createAnuncioSuccess.observe(viewLifecycleOwner){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        return view
    }


}