package com.example.project2wishlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.R
import com.example.wishlist.WishlistItem

class WishlistAdapter(private val wishlistItems: List<WishlistItem>) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val urlTextView: TextView = itemView.findViewById(R.id.urlTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wishlistItems[position]

        holder.nameTextView.text = "Name: ${item.name}"
        holder.priceTextView.text = "Price: ${item.price}"
        holder.urlTextView.text = "URL: ${item.url}"
    }

    override fun getItemCount(): Int {
        return wishlistItems.size
    }
}
