package com.wazirx.exchange.api

import com.wazirx.exchange.pojo.CoinInfo
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("sapi/v1/tickers/24hr")
    fun getAllCoinsInfo(): Single<List<CoinInfo>?>

    @GET("sapi/v1/ticker/24hr")
    fun getSymbolDetails(
        @Query("symbol") symbol: String,
    ): Single<List<CoinInfo>?>

    companion object {
        var retrofitInterface: ApiService? = null

        fun getInstance(): ApiService {
            if (retrofitInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.wazirx.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                retrofitInterface = retrofit.create(ApiService::class.java)
            }
            return retrofitInterface!!
        }
    }
}