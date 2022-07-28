package com.example.petlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petlist.R
import com.example.petlist.activity.DetailsActivity
import com.example.petlist.models.DataRepository
import com.example.petlist.models.Pets

class PetAdapter(context: Context) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {
    var petList: List<Pets>?

    init {
        petList = DataRepository.getPetListModels(context)?.pets
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PetViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pet_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bindValue(petList?.get(position))
    }

    override fun getItemCount() = petList?.size ?: 0

    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var petImageView: ImageView? = itemView.findViewById(R.id.pet_image)
        private var petTextView: TextView? = itemView.findViewById(R.id.pet_text)

        fun bindValue(petModel: Pets?) {
            petImageView?.let {
                Glide.with(it.context)
                    .load(petModel?.imageUrl)
                    .centerCrop()
                    .fitCenter()
                    .into(it)

                it.contentDescription = it.context.getString(R.string.pet_image_desc_details, petModel?.title)
            }
            petTextView?.apply {
                text = petModel?.title
            }

            itemView.setOnClickListener {
                petModel?.let { it1 -> DetailsActivity.open(it.context, it1) }
            }
        }
    }
}