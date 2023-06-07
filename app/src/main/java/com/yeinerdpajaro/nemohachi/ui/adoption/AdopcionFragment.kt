package com.yeinerdpajaro.nemohachi.ui.adoption


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yeinerdpajaro.nemohachi.databinding.FragmentAdopcionBinding
import com.yeinerdpajaro.nemohachi.databinding.FragmentAnunciosBinding
import com.yeinerdpajaro.nemohachi.model.Anuncios
import com.yeinerdpajaro.nemohachi.ui.announcement.AnunciosAdapter
import com.yeinerdpajaro.nemohachi.ui.announcement.AnunciosViewModel


class AdopcionFragment : Fragment() {

    private var _binding: FragmentAdopcionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adopcionViewModel: AdopcionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adopcionViewModel = ViewModelProvider(this)[AdopcionViewModel::class.java]
        _binding = FragmentAdopcionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }
}