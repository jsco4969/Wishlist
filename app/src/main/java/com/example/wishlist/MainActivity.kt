package com.example.wishlist
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2wishlist.WishlistAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.ListRv)
        adapter = WishlistAdapter(wishlistItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val fabAddItem: FloatingActionButton = findViewById(R.id.fabAddItem)
        fabAddItem.setOnClickListener {
            showAddItemDialog()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemToWishlist(item: WishlistItem) {
        wishlistItems.add(item)
        adapter.notifyDataSetChanged()
    }


    @SuppressLint("MissingInflatedId")
    private fun showAddItemDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.dialog_add_item, null)

        val itemNameEditText: EditText = view.findViewById(R.id.editTextItemName)
        val itemPriceEditText: EditText = view.findViewById(R.id.editTextItemPrice)
        val itemURLEditText: EditText = view.findViewById(R.id.editTextItemURL)

        builder.setView(view)
            .setTitle("Add Item to Wishlist")
            .setPositiveButton("Add") { _, _ ->
                // Handle positive button click (Add)
                val itemName = itemNameEditText.text.toString()
                val itemPrice = itemPriceEditText.text.toString()
                val itemURL = itemURLEditText.text.toString()

                if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemURL.isNotEmpty()) {
                    val newItem = WishlistItem(itemName, itemPrice, itemURL)
                    addItemToWishlist(newItem)
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                // Handle negative button click (Cancel)
            }

        val dialog = builder.create()
        dialog.show()
    }
}
