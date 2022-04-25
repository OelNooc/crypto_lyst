package cl.nooc.cryptolyst.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.nooc.cryptolyst.cliente.ClienteRetrofit
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.modelo.Coins
import cl.nooc.cryptolyst.repository.ClienteRepository
import cl.nooc.cryptolyst.repository.CoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinViewModel (application: Application) : AndroidViewModel(application) {

    private val clienteRepo = ClienteRepository()
    private val coinRepo = CoinRepository(getApplication())

    val coins = coinRepo.listar()
    val coin = MutableLiveData<CoinData>()

    fun getData()
    {
        CoroutineScope(Dispatchers.IO).launch {
            if(coinRepo.getCount() == 0){
                clienteRepo.getListaCoins().enqueue(object: Callback<Coins>{
                    override fun onResponse(call: Call<Coins>, response: Response<Coins>) {
                        response.body().let {
                            coinRepo.agregar(it!!.data)
                        }
                    }

                    override fun onFailure(call: Call<Coins>, t: Throwable) {
                        Log.e("CALL", t.message.toString())
                    }
                })
            }
        }
      /*  val service = ClienteRetrofit.getInstance(ClienteRetrofit.base_url)
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

        })*/
    }

    fun getDetail(id:String){
        val service = ClienteRetrofit.getInstance(ClienteRetrofit.base_url)
        service.getCoinDetail(id).enqueue(object: Callback<CoinData>{
            override fun onResponse(call: Call<CoinData>, response: Response<CoinData>) {
                response.body().let {
                    coin.postValue(it)
                }
                Log.e("COIN",response.toString())
            }

            override fun onFailure(call: Call<CoinData>, t: Throwable) {
                Log.e("CALL",t.message.toString())
            }
        })
    }

    fun updateCoin(id:String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            coin.postValue(coinRepo.buscar(id))
        }
    }
}