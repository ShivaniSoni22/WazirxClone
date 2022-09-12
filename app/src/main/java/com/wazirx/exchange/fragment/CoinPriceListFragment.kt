package com.wazirx.exchange.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.wazirx.exchange.viewmodel.CoinViewModel
import com.wazirx.exchange.viewmodel.CoinViewModelFactory
import com.wazirx.exchange.adapters.CoinInfoAdapter
import com.wazirx.exchange.databinding.FragmentCoinPriceListBinding
import com.wazirx.exchange.pojo.CoinInfo
import kotlinx.android.synthetic.main.fragment_coin_price_list.*

class CoinPriceListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, CoinViewModelFactory(requireActivity().application)
        )[CoinViewModel::class.java]
    }
    private lateinit var binding: FragmentCoinPriceListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinPriceListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoinInfoAdapter(requireActivity())
        rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                findNavController().navigate(
                    CoinPriceListFragmentDirections.actionCoinPriceListFragmentToCoinDetailFragment(
                        coinPriceInfo.symbol)
                )
            }
        }
        viewModel.priceList.observe(viewLifecycleOwner) {
            adapter.coinInfoList = it
        }
    }

}
