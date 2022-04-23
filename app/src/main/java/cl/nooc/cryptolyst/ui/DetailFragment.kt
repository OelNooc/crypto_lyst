package cl.nooc.cryptolyst.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.nooc.cryptolyst.databinding.FragmentDetailBinding
import cl.nooc.cryptolyst.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso
import java.util.*

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by activityViewModels<CoinViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.coin.observe(viewLifecycleOwner){
            with(binding){
                Picasso.get().load(getImage(it.symbol)).resize(500,500).into(ivDCoin)
                tvSym.text = it.symbol
                tvDNom.text = it.name
                tvDPrice.text = "$" + it.priceUsd.subSequence(0,7)
                tvDNSupply.text = it.supply
                tvDIDMCap.text = it.marketCapUsd
            }
        }
        return binding.root
    }

    private fun getImage(symbol: String) =
        "https://static.coincap.io/assets/icons/${symbol.lowercase(Locale.getDefault())}@2x.png"

}