package com.yeinerdpajaro.nemohachi.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeinerdpajaro.nemohachi.R
import com.yeinerdpajaro.nemohachi.databinding.CardViewAnuncioItemBinding
import com.yeinerdpajaro.nemohachi.model.Anuncio

class AnunciosAdapter (

    private val anuncioList: ArrayList<Anuncio>,
    private val onItemClicked: (Anuncio)-> Unit,
    ) : RecyclerView.Adapter<AnunciosAdapter.AnunciosViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnunciosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_anuncio_item,parent,false)
        return AnunciosViewHolder(view)
    }
    override fun onBindViewHolder(holder: AnunciosViewHolder, position: Int) {
        val anuncio = anuncioList[position]
        holder.bind(anuncio)
        holder.itemView.setOnClickListener{onItemClicked(anuncio)}
    }

    override fun getItemCount(): Int = anuncioList.size

    fun appendItems(newList: ArrayList<Anuncio>){
        anuncioList.clear()
        anuncioList.addAll(newList)
        notifyDataSetChanged()
    }


    class AnunciosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding =  CardViewAnuncioItemBinding.bind(itemView)

        fun bind(anuncio: Anuncio){
            with(binding){
                nameTextView.text = anuncio.name
                commentTextView.text = "Descripción: ${anuncio.comment}"
            }

        }

    }
}
