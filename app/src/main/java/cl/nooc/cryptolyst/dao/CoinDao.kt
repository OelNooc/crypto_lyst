package cl.nooc.cryptolyst.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.modelo.Coins

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregar(coins: Coins)

    @Query("select Id, Name, PriceUsd," +
            " ChangePercent24hr, Explorer, MarketCapUsd, MaxSupply, Rank, Supply," +
            "Symbol, VolumeUsd24Hr, VWap24Hr from coin")
    fun listar(): LiveData<List<CoinData>>

    @Query("select Id, Name, PriceUsd," +
            " ChangePercent24hr, Explorer, MarketCapUsd, MaxSupply, Rank, Supply," +
            "Symbol, VolumeUsd24Hr, VWap24Hr from coin where id = :id")
    fun buscar(id:String) : CoinData

    @Query("select count(*) from coin")
    fun contar(): Int
}