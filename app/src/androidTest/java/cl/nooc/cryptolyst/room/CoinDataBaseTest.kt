package cl.nooc.cryptolyst.room

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.nooc.cryptolyst.dao.CoinDao
import cl.nooc.cryptolyst.getOrAwaitValue
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.room.CoinDataBase
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoinDataBaseTest {

    @get:Rule
    var regla = InstantTaskExecutorRule()

    private lateinit var db: CoinDataBase
    private lateinit var dao: CoinDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CoinDataBase::class.java)
            .allowMainThreadQueries().build()
        dao = db.coinDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun test_agregar() {
        var coinData = CoinData(
            "2.8048012337725155",
            "https://blockchain.info/",
            "bitcoin",
            "747614768333.1922004438954904",
            "21000000.0000000000000000",
            "Bitcoin",
            "39298.0675537673886542",
            "1",
            "19024212.0000000000000000",
            "BTC",
            "12362069728.1465272478533820",
            "38914.6831447196622679"
        )
        var lista = ArrayList<CoinData>()
        lista.add(coinData)
        dao.agregar(lista)
        var respuesta = dao.buscar("bitcoin")
        Truth.assertThat(respuesta).isEqualTo(coinData)
    }

    @Test
    fun test_listar() {
        var coinData = CoinData(
            "2.8048012337725155",
            "https://blockchain.info/",
            "bitcoin",
            "747614768333.1922004438954904",
            "21000000.0000000000000000",
            "Bitcoin",
            "39298.0675537673886542",
            "1",
            "19024212.0000000000000000",
            "BTC",
            "12362069728.1465272478533820",
            "38914.6831447196622679"
        )
        var lista = ArrayList<CoinData>()
        lista.add(coinData)
        val listaTest = dao.listar().getOrAwaitValue()
        Truth.assertThat(lista[0].id).isEqualTo("bitcoin")
    }
}