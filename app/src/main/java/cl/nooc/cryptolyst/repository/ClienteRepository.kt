package cl.nooc.cryptolyst.repository

import cl.nooc.cryptolyst.cliente.ClienteRetrofit
import cl.nooc.cryptolyst.modelo.Coins
import retrofit2.Call

class ClienteRepository {

    private val cliente = ClienteRetrofit.getInstance(ClienteRetrofit.base_url)

    fun getListaCoins(): Call<Coins>{
        return cliente.getCoins()
    }
}