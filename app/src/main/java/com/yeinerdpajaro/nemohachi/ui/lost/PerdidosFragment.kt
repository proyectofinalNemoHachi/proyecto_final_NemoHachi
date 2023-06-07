package com.yeinerdpajaro.nemohachi.ui.lost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeinerdpajaro.nemohachi.databinding.FragmentPerdidosBinding
import com.yeinerdpajaro.nemohachi.forms.newlost.NewPerdidosActivity
import com.yeinerdpajaro.nemohachi.model.Perdidos


class PerdidosFragment : Fragment() {
    private var _binding: FragmentPerdidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var perdidosViewModel: PerdidosViewModel

    private lateinit var perdidosAdapter: PerdidosAdapter

    private var perdidosList: ArrayList<Perdidos> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        perdidosViewModel = ViewModelProvider(this)[PerdidosViewModel::class.java]
        _binding = FragmentPerdidosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.newlostFloatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), NewPerdidosActivity::class.java)
            startActivity(intent)
        }

        perdidosAdapter = PerdidosAdapter(perdidosList,
            onItemCLicked = { perdidos -> Log.d("nombre", perdidos.petname!!) }
        )

        binding.lostRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PerdidosFragment.requireContext())
            adapter = perdidosAdapter
        }

        perdidosViewModel.loadPerdidos()

        perdidosViewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        perdidosViewModel.perdidoList.observe(viewLifecycleOwner){perdidosList ->
            perdidosAdapter.appendItems(perdidosList)
        }





        return root
    }

}