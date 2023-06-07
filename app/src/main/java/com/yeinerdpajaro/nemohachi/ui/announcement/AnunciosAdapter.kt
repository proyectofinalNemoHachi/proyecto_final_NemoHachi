package com.yeinerdpajaro.nemohachi.ui.announcement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeinerdpajaro.nemohachi.R
import com.yeinerdpajaro.nemohachi.databinding.CardViewAnnouncementItemBinding
import com.yeinerdpajaro.nemohachi.databinding.CardViewLostItemBinding
import com.yeinerdpajaro.nemohachi.model.Anuncios
import com.yeinerdpajaro.nemohachi.model.Perdidos
import com.yeinerdpajaro.nemohachi.ui.lost.PerdidosAdapter

class AnunciosAdapter (
    private var anunciosList: ArrayList<Anuncios>,
    private var onItemCLicked: (Anuncios) -> Unit,
        ): RecyclerView.Adapter<AnunciosAdapter.AnunciosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnunciosViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_announcement_item, parent, false)
        return AnunciosViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnunciosViewHolder, position: Int) {
        val anuncios = anunciosList[position]
        holder.bind(anuncios)
        holder.itemView.setOnClickListener { onItemCLicked(anuncios) }
    }

    override fun getItemCount(): Int = anunciosList.size

    fun appendItems(newList: ArrayList<Anuncios>){
        anunciosList.clear()
        anunciosList.addAll(newList)
        notifyDataSetChanged()
    }

    class AnunciosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewAnnouncementItemBinding.bind(itemView)
        fun bind(anuncios: Anuncios) {
            with(binding) {
                nameTextView.text = anuncios.name
                commentTextView.text = anuncios.comment


            }

        }
    }

}