package cl.nooc.cryptolyst.cliente

import cl.nooc.cryptolyst.FileReader
import com.google.common.truth.Truth
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Before
import org.junit.Test

class ClienteRetrofitTest {


    private val server = MockWebServer()
    private val body = FileReader.lectorJson("coins.json")

    @Before
    fun setUp() {
        server.start(8080)
        server.enqueue(MockResponse().setResponseCode(200).setBody(body))
        server.url("assets")
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun test_apiSuccessCantidad()
    {
        val call = ClienteRetrofit.getInstance("http://localhost:8080/").getCoins()
        val coins = call.execute().body()
        var lista = coins?.data
        Truth.assertThat(lista?.size).isEqualTo(8)
    }

    @Test
    fun test_apiFailCantidad()
    {
        val call = ClienteRetrofit.getInstance("http://localhost:8080/").getCoins()
        val coins = call.execute().body()
        var lista = coins?.data
        Truth.assertThat(lista?.size).isNotEqualTo(0)
    }

    @Test
    fun test_apiSurccess()
    {
        val call = ClienteRetrofit.getInstance("http://localhost:8080/").getCoins()
        val coins = call.execute().body()
        var lista = coins?.data
        Truth.assertThat(lista?.get(0)?.id).isEqualTo("bitcoin")
    }
}