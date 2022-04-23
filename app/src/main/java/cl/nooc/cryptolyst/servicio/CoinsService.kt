package cl.nooc.cryptolyst.servicio

import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.modelo.Coins
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsService {

    @GET("assets")
    fun getCoins() : Call<Coins>

    @GET("assets/{coin}")
    fun getCoinDetail(@Path("coin") coin:String) : Call<CoinData>
}