package cl.nooc.cryptolyst.repository

import android.content.Context
import androidx.lifecycle.LiveData
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.modelo.Coins
import cl.nooc.cryptolyst.room.CoinDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinRepository(var context: Context) {

    private val db = CoinDataBase.getInstancia(context)

    fun agregar(coin: Coins){
        CoroutineScope(Dispatchers.IO).launch {
            db.coinDao().agregar(coin)
        }
    }

    fun listar(): LiveData<List<CoinData>>{
        return db.coinDao().listar()
    }

    fun buscar(id:String): CoinData{
        return db.coinDao().buscar(id)
    }

    fun getCount(): Int{
        return db.coinDao().contar()
    }
}