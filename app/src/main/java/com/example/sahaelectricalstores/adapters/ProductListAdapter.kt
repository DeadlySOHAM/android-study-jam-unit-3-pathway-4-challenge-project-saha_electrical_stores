package com.example.sahaelectricalstores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.dataSource.ProductListDataSource
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.example.sahaelectricalstores.fragments.Product

class ProductListAdapter (
    private val sharedViewBinding : OrderViewModel ,
    private val binding : Product
): RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private val items = ProductListDataSource.productList

    class ProductListViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val itemName : TextView = view.findViewById(R.id.product_name)
        val itemPic : ImageView = view.findViewById(R.id.product_img)
        val card : CardView = view.findViewById(R.id.Card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.product_recycler_view_holder,parent,false)
        return ProductListViewHolder(adapterLayout)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ProductListAdapter.ProductListViewHolder, position: Int) {
        val product = items[position]
        holder.itemName.text = product.name
        holder.itemPic.setImageResource(product.pic)

        holder.card.setOnClickListener{
            sharedViewBinding.productSelected(product.id,product.name)
            binding.goToNext()
        }
    }

}