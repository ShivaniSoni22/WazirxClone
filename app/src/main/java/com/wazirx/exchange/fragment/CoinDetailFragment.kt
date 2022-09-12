package com.wazirx.exchange.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wazirx.exchange.viewmodel.CoinViewModel
import com.wazirx.exchange.viewmodel.CoinViewModelFactory
import com.wazirx.exchange.databinding.FragmentCoinDetailBinding

class CoinDetailFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, CoinViewModelFactory(requireActivity().application)
        )[CoinViewModel::class.java]
    }
    val args: CoinDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCoinDetailBinding
    private lateinit var symbol: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        symbol = args.symbol
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailInfo(symbol).observe(viewLifecycleOwner) {
            binding.tvVolume.text = String.format("%.2f", it.volume?.toDouble() ?: 0f)
            binding.tvPrice.text = String.format("%.2f", it.lastPrice?.toDouble() ?: 0f)
            binding.tvMinPrice.text = String.format("%.2f", it.lowPrice?.toDouble() ?: 0f)
            binding.tvMaxPrice.text = String.format("%.2f", it.highPrice?.toDouble() ?: 0f)
            binding.tvLastUpdate.text = it.getFormattedTime()
            binding.tvFromSymbol.text = it.baseAsset?.uppercase() ?: "NA"
            binding.tvToSymbol.text = it.quoteAsset?.uppercase() ?: "NA"
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
