package com.example.multipleview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.multipleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val products = listOf(
            Product(R.drawable.cetaphil, 20),
            Product(R.drawable.cetaphil, 1),
            Product(R.drawable.cetaphil, 20),
            Product(R.drawable.cetaphil, 1),
            Product(R.drawable.cetaphil, 20),
            Product(R.drawable.cetaphil, 1),
            Product(R.drawable.cetaphil, 23),
            Product(R.drawable.cetaphil, 55)
        )

        adapter = ProductAdapter(products)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }
}
