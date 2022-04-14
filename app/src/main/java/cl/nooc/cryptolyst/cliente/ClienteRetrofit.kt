package cl.nooc.cryptolyst.cliente

import cl.nooc.cryptolyst.servicio.CoinsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClienteRetrofit {

    companion object{
        private const val base_url = "https://api.coincap.io/v2/"

        fun getInstance() : CoinsService
        {
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CoinsService::class.java)
        }
    }
}