package com.wazirx.exchange.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wazirx.exchange.utils.convertTimestampToTime

@Entity(tableName = "full_price_list")
data class CoinInfo(
    @PrimaryKey
    @SerializedName("symbol"     )@Expose var symbol     : String = "NA",
    @SerializedName("baseAsset"  )@Expose var baseAsset  : String? = null,
    @SerializedName("quoteAsset" )@Expose var quoteAsset : String? = null,
    @SerializedName("openPrice"  )@Expose var openPrice  : String? = null,
    @SerializedName("lowPrice"   )@Expose var lowPrice   : String? = null,
    @SerializedName("highPrice"  )@Expose var highPrice  : String? = null,
    @SerializedName("lastPrice"  )@Expose var lastPrice  : String? = null,
    @SerializedName("volume"     )@Expose var volume     : String? = null,
    @SerializedName("bidPrice"   )@Expose var bidPrice   : String? = null,
    @SerializedName("askPrice"   )@Expose var askPrice   : String? = null,
    @SerializedName("at"         )@Expose var at         : Long?    = null
) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(at)
    }

}
