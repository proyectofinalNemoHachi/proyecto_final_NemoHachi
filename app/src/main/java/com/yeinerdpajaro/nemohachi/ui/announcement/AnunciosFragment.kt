package com.yeinerdpajaro.nemohachi.ui.announcement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeinerdpajaro.nemohachi.databinding.FragmentAnunciosBinding
import com.yeinerdpajaro.nemohachi.forms.newannouncement.NewAnunciosActivity
import com.yeinerdpajaro.nemohachi.forms.newlost.NewPerdidosActivity
import com.yeinerdpajaro.nemohachi.model.Anuncios


class AnunciosFragment : Fragment() {

    private var _binding: FragmentAnunciosBinding? = null
    private val binding get() = _binding!!
    private lateinit var anunciosViewModel: AnunciosViewModel
    private lateinit var anunciosAdapter: AnunciosAdapter
    private var anunciosList: ArrayList<Anuncios> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        anunciosViewModel = ViewModelProvider(this)[AnunciosViewModel::class.java]
        _binding = FragmentAnunciosBinding.inflate(inflater,container,false)
        val root: View = binding.root

        binding.newAnuncioFloatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), NewAnunciosActivity::class.java)
            startActivity(intent)
        }




        anunciosAdapter = AnunciosAdapter(anunciosList,
        onItemCLicked = {anuncios -> Log.d("nombre", anuncios.name!!)}
        )

        binding.lostRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AnunciosFragment.requireContext())
            adapter = anunciosAdapter
        }

        anunciosViewModel.loadAnuncios()

        anunciosViewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        anunciosViewModel.anunciosList.observe(viewLifecycleOwner){anunciosList ->
            anunciosAdapter.appendItems(anunciosList)
        }








        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}