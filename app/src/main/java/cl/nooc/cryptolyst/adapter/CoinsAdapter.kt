package cl.nooc.cryptolyst.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.nooc.cryptolyst.R
import cl.nooc.cryptolyst.databinding.CoinsLayoutBinding
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.modelo.Coins
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class CoinsAdapter : RecyclerView.Adapter<CoinsAdapter.CustomViewHolder>() {

    var lista:List<CoinData> = ArrayList()
    lateinit var listener: MiListener

    class CustomViewHolder(itemView: View, val listener: MiListener) : RecyclerView.ViewHolder(itemView)
    {
        private val binding = CoinsLayoutBinding.bind(itemView)

        private fun getImage(symbol: String) =
            "https://static.coincap.io/assets/icons/${symbol.lowercase(Locale.getDefault())}@2x.png"

        fun bindData(coin:CoinData)
        {
            with(binding)
            {
                Picasso.get().load(getImage(coin.symbol)).resize(300,300).into(ivCoin)
                tvNameCoin.text = coin.name
                tvPrecio.text = "USD $" + coin.priceUsd.subSequence(0,7)
                itemView.setOnClickListener {
                    listener.miOnClick(coin)
                }
            }
        }
    }

    fun updateData(coins: List<CoinData>)
    {
        lista = coins
        notifyDataSetChanged()
    }

    interface MiListener{
        fun miOnClick(coin:CoinData)
    }

    fun setMiListener(listener: MiListener)
    {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coins_layout,parent,false)
        return CustomViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int = lista.size
}