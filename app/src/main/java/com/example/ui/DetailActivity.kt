package com.example.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.ui.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val item: SaleItem? by lazy {
        intent.getParcelableExtra<SaleItem>(Constants.ITEM_OBJECT)
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    private var isLike: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("jblee", "itemPosition=$itemPosition")

        binding.ivItemImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                theme
            )
        })

        binding.tvSellerName.text = item?.SellerName
        binding.tvSellerAddress.text = item?.Address
        binding.tvITemTitle.text = item?.ItemTitle
        binding.tvITemDetail.text = item?.ItemDetail
        binding.tvITemDetailPrice.text = item?.Price.toString()

        binding.ivBack.setOnClickListener {
            exit()
        }
    }

    private fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("islike", isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}
