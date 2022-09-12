package com.wazirx.exchange.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.wazirx.exchange.viewmodel.CoinViewModel
import com.wazirx.exchange.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.loadData()
    }
}