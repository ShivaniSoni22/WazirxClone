package com.wazirx.exchange.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wazirx.exchange.api.ApiService
import com.wazirx.exchange.database.AppDatabase
import com.wazirx.exchange.pojo.CoinInfo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    var priceList = db.coinPriceInfoDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    fun loadData() {
        val disposable = ApiService.getInstance().getAllCoinsInfo()
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({ coinInfo ->
                db.coinPriceInfoDao().insertPriceInfoList(coinInfo ?: mutableListOf())
                Log.d("TEST_OF_LOADING_DATA", "Success: $coinInfo")
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}