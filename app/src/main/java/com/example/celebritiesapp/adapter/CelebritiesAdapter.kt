package com.example.celebritiesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.celebritiesapp.R
import com.example.celebritiesapp.activity.MainActivity
import com.example.celebritiesapp.model.Celebrity

class CelebritiesAdapter(val context: Context, val celebrities:List<Celebrity>, val listener: CelebrityClickListener):RecyclerView.Adapter<CelebritiesAdapter.CelebrityViewHolder>(){

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebrityViewHolder {
        val view = layoutInflater.inflate(R.layout.item_celebrity, parent, false)
        return CelebrityViewHolder(view, context, listener)
    }

    override fun getItemCount(): Int {
        return this.celebrities.size
    }

    override fun onBindViewHolder(holder: CelebrityViewHolder, position: Int) {
        holder.name.text = celebrities[position].name
        holder.foto.setImageResource(context.resources.getIdentifier(celebrities[position].fotoPath, "drawable", context.packageName))
    }

    class CelebrityViewHolder(view: View, context: Context, listener: CelebrityClickListener ):RecyclerView.ViewHolder(view){
        val cardView:CardView = view.findViewById(R.id.item_celebrity_cardview)
        val name:TextView = view.findViewById(R.id.item_celebrity_name)
        val foto:ImageView = view.findViewById(R.id.item_celebrity_image)

        init{
            view.setOnClickListener{
                listener.onClick(it, adapterPosition)
            }
        }
    }
}