package cl.nooc.cryptolyst

import java.io.File
import java.io.InputStreamReader
import java.lang.StringBuilder

object FileReader {

    fun lectorJson(archivo:String) : String{
        val input =  File("src/main/assets/$archivo").inputStream() //InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.assets.open(archivo)
        val builder = StringBuilder()
        val lector = InputStreamReader(input,"UTF-8")
        lector.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}