package com.yeinerdpajaro.nemohachi.ui.lost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeinerdpajaro.nemohachi.R
import com.yeinerdpajaro.nemohachi.databinding.CardViewLostItemBinding
import com.yeinerdpajaro.nemohachi.model.Perdidos

class PerdidosAdapter(
    private var perdidosList: ArrayList<Perdidos>,
    private var onItemCLicked: (Perdidos) -> Unit,

    ) : RecyclerView.Adapter<PerdidosAdapter.PerdidosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerdidosViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_lost_item, parent, false)
        return PerdidosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerdidosViewHolder, position: Int) {
        val perdidos = perdidosList[position]
        holder.bind(perdidos)
        holder.itemView.setOnClickListener { onItemCLicked(perdidos) }
    }


    override fun getItemCount(): Int = perdidosList.size

    fun appendItems(newList: ArrayList<Perdidos>){
        perdidosList.clear()
        perdidosList.addAll(newList)
        notifyDataSetChanged()
    }


    class PerdidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewLostItemBinding.bind(itemView)
        fun bind(perdidos: Perdidos) {
            with(binding) {
                petNameTextView.text = perdidos.petname
                cityTextView.text = perdidos.city
                commentTextView.text = perdidos.comment


            }

        }
    }
}