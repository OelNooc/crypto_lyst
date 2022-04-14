package cl.nooc.cryptolyst.servicio

import cl.nooc.cryptolyst.modelo.Coins
import retrofit2.Call
import retrofit2.http.GET

interface CoinsService {

    @GET("assets")
    fun getCoins() : Call<Coins>
}