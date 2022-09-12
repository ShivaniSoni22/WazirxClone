package com.wazirx.exchange.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wazirx.exchange.CoinViewModel
import com.wazirx.exchange.R
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        val symbol = intent.getStringExtra("Symbol")
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        symbol?.let {
            viewModel.getDetailInfo(it).observe(this) {
                tvVolume.text = String.format("%.2f", it.volume?.toDouble() ?: 0f)
                tvPrice.text = String.format("%.2f", it.lastPrice?.toDouble() ?: 0f)
                tvMinPrice.text = String.format("%.2f", it.lowPrice?.toDouble() ?: 0f)
                tvMaxPrice.text = String.format("%.2f", it.highPrice?.toDouble() ?: 0f)
                tvLastUpdate.text = it.getFormattedTime()
                tvFromSymbol.text = it.baseAsset?.uppercase() ?: "NA"
                tvToSymbol.text = it.quoteAsset?.uppercase() ?: "NA"
            }
        }
    }

}
