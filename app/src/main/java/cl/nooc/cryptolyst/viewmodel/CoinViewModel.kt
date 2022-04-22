package cl.nooc.cryptolyst.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.nooc.cryptolyst.cliente.ClienteRetrofit
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.modelo.Coins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinViewModel (application: Application) : AndroidViewModel(application) {

    val coins = MutableLiveData<Coins>()
    val coin = MutableLiveData<CoinData>()

    fun getData()
    {
        val service = ClienteRetrofit.getInstance(ClienteRetrofit.base_url)
        service.getCoins().enqueue(object  : Callback<Coins> {
            override fun onResponse(call: Call<Coins>, response: Response<Coins>) {
                response.body().let {
                    coins.postValue(it)
                }
                Log.e("COINS",response.toString())
            }
            override fun onFailure(call: Call<Coins>, t: Throwable) {
                Log.e("CALL",t.message.toString())
            }

        })
    }

    fun updateCoin(coin:CoinData)
    {
        this.coin.value = coin
    }
}