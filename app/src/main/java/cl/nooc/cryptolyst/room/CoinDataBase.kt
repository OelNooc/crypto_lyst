package cl.nooc.cryptolyst.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.nooc.cryptolyst.dao.CoinDao
import cl.nooc.cryptolyst.modelo.CoinData

@Database(entities = [CoinData::class], version = 1)
abstract class CoinDataBase: RoomDatabase() {

    abstract fun coinDao(): CoinDao

    companion object{
        @Volatile
        private var instancia:CoinDataBase? = null

        fun getInstancia(context: Context): CoinDataBase{

            if(instancia == null){

                synchronized(this){
                    instancia = Room.databaseBuilder(context,
                    CoinDataBase::class.java, "coins_db")
                        .build()
                }
            }
            return instancia!!
        }
    }
}