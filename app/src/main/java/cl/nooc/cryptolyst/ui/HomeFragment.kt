package cl.nooc.cryptolyst.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import cl.nooc.cryptolyst.R
import cl.nooc.cryptolyst.adapter.CoinsAdapter
import cl.nooc.cryptolyst.databinding.FragmentHomeBinding
import cl.nooc.cryptolyst.modelo.CoinData
import cl.nooc.cryptolyst.viewmodel.CoinViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel by activityViewModels<CoinViewModel>()
    private val adapter = CoinsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val user = arguments?.getString("nom")
        sharedPreferences = requireActivity().
        getSharedPreferences(user, AppCompatActivity.MODE_PRIVATE)

        adapter.setMiListener(object: CoinsAdapter.MiListener{
            override fun miOnClick(coin: CoinData) {
                viewModel.updateCoin(coin.id)
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_homeFragment_to_detailFragment)
            }
        })

        with(binding){
            tvUserReg.text = user
            rvCoins.adapter = adapter
        }

        viewModel.coins.observe(viewLifecycleOwner){
            adapter.updateData(it)
        }
        return binding.root
    }

}