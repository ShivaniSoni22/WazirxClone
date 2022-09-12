package com.wazirx.exchange.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wazirx.exchange.pojo.CoinInfo

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM full_price_list ORDER BY at DESC")
    fun getPriceList(): LiveData<List<CoinInfo>>

    @Query("SELECT * FROM full_price_list WHERE symbol == :fSymbol LIMIT 1")
    fun getPriceInfoAboutCoin(fSymbol: String): LiveData<CoinInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceInfoList(coinInfoList: List<CoinInfo>)
}
