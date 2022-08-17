package com.example.android.animefacts.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.animefacts.R
import com.example.android.animefacts.data.models.animeList.Anime
import com.example.android.animefacts.presentation.interfaces.AnimeListener

class AnimeAdapter(private val animeList: List<Anime>, val animeListener: AnimeListener) :
    RecyclerView.Adapter<AnimeAdapter.AnimeHolder>(){
        inner class AnimeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val animeCard: CardView
            val name: TextView
            val image: ImageView

            init {
                animeCard = itemView.findViewById(R.id.anime_card)
                name = itemView.findViewById(R.id.anime_name_txt)
                image = itemView.findViewById(R.id.anime_img)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder {
        return AnimeHolder(LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        val currentAnime = animeList[position]
        holder.name.text = animeList[position].name
        Glide.with(holder.itemView.context)
            .load(animeList[position].image)
            .into(holder.image)

        holder.animeCard.setOnClickListener {
            animeListener.onClickItem(animeList[position].id, animeList[position].name)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}