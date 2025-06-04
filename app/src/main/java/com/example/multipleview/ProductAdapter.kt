package com.example.multipleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton // Tetap ada untuk LowViewHolder
import android.widget.ImageView    // Ditambahkan untuk HighViewHolder
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HIGH = 1
        private const val TYPE_LOW = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (productList[position].quantity >= 20) TYPE_HIGH else TYPE_LOW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_HIGH) {
            val view = inflater.inflate(R.layout.item_product_high, parent, false)
            HighViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_low_stock, parent, false)
            LowViewHolder(view)
        }
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = productList[position]
        if (holder.itemViewType == TYPE_HIGH) {
            (holder as HighViewHolder).bind(product)
        } else if (holder.itemViewType == TYPE_LOW) {
            (holder as LowViewHolder).bind(product)
        }
    }

    // ViewHolder untuk produk stok tinggi
    inner class HighViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.productImage)
        private val minus: ImageView = itemView.findViewById(R.id.btnMinus)
        private val plus: ImageView = itemView.findViewById(R.id.btnPlus)
        private val qty: TextView = itemView.findViewById(R.id.quantityText)

        fun bind(product: Product) {
            image.setImageResource(product.imageResId)
            qty.text = product.quantity.toString()

            minus.setOnClickListener {
                if (product.quantity > 10) {
                    product.quantity--
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        notifyItemChanged(adapterPosition)
                    }
                }
            }

            plus.setOnClickListener {
                product.quantity++
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    // ViewHolder untuk produk stok rendah
    inner class LowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imgProduct)
        private val warning: TextView = itemView.findViewById(R.id.txtWarning)
        private val minusBtn: ImageButton = itemView.findViewById(R.id.btnMinus)
        private val plusBtn: ImageButton = itemView.findViewById(R.id.btnPlus)
        private val qty: TextView = itemView.findViewById(R.id.txtQty)

        fun bind(product: Product) {
            image.setImageResource(product.imageResId)
            qty.text = product.quantity.toString()
            warning.text = "Stok hampir habis!"

            minusBtn.setOnClickListener {
                if (product.quantity > 10) {
                    product.quantity--
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        notifyItemChanged(adapterPosition)
                    }
                }
            }

            plusBtn.setOnClickListener {
                product.quantity++
              if (adapterPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }
}