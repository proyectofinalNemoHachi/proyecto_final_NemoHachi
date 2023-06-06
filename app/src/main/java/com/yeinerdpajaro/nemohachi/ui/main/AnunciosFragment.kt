package com.yeinerdpajaro.nemohachi.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeinerdpajaro.nemohachi.databinding.FragmentAnunciosBinding
import com.yeinerdpajaro.nemohachi.model.Anuncio


class AnunciosFragment : Fragment() {

    private var _binding: FragmentAnunciosBinding? = null
    private val binding get() = _binding!!
    private lateinit var anunciosViewModel: AnunciosViewModel
    private lateinit var anunciosAdapter: AnunciosAdapter
    private var anunciosList: ArrayList<Anuncio> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        anunciosViewModel = ViewModelProvider(this)[AnunciosViewModel::class.java]
        _binding = FragmentAnunciosBinding.inflate(inflater,container,false)
        val root: View = binding.root



        anunciosAdapter = AnunciosAdapter(anunciosList,
        onItemClicked = {anuncio -> Log.d("nombre", anuncio.name!!)}
        )



        binding.anunciosRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AnunciosFragment.requireContext())
            adapter = anunciosAdapter
            setHasFixedSize(false)
        }

        anunciosViewModel.loadAnuncios()

        anunciosViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg ->
            Toast.makeText(requireContext(),errorMsg,Toast.LENGTH_SHORT).show()

        }
        anunciosViewModel.anunciosList.observe(viewLifecycleOwner){anuncioList ->
            anunciosAdapter.appendItems(anuncioList)
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}