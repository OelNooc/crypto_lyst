package cl.nooc.cryptolyst.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class CoinData(
    @ColumnInfo(name = "ChangePercent24hr")
    val changePercent24Hr: String,
    @ColumnInfo(name = "Explorer", defaultValue = "NULL")
    val explorer: String?,
    @ColumnInfo(name = "Id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "MarketCapUsd")
    val marketCapUsd: String,
    @ColumnInfo(name = "MaxSupply", defaultValue = "NULL")
    val maxSupply: String?,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "PriceUsd")
    val priceUsd: String,
    @ColumnInfo(name = "Rank")
    val rank: String,
    @ColumnInfo(name = "Supply")
    val supply: String,
    @ColumnInfo(name = "Symbol")
    val symbol: String,
    @ColumnInfo(name = "VolumeUsd24Hr")
    val volumeUsd24Hr: String,
    @ColumnInfo(name = "VWap24Hr", defaultValue = "NULL")
    val vwap24Hr: String?
)