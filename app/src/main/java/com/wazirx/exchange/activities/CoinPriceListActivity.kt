package com.wazirx.exchange.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wazirx.exchange.CoinViewModel
import com.wazirx.exchange.adapters.CoinInfoAdapter
import com.wazirx.exchange.R
import com.wazirx.exchange.pojo.CoinInfo
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        val adapter = CoinInfoAdapter(this)
        viewModel.loadData()
        val coinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = Intent(this@CoinPriceListActivity, CoinDetailActivity::class.java)
                intent.putExtra("Symbol", coinPriceInfo.symbol)
                startActivity(intent)
            }
        }
        adapter.onCoinClickListener = coinClickListener
        rvCoinPriceList.adapter = adapter
        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }

}
