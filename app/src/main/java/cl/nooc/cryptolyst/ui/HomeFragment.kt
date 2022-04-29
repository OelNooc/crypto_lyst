package cl.nooc.cryptolyst.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
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
        setHasOptionsMenu(true)

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

        viewModel.filtro.observe(viewLifecycleOwner){
            adapter.updateData(viewModel.coins.value!!.filter{
                    coin -> coin.name.contains(it, true)})
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "CryptoMonedas"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filtro.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isEmpty())
                {
                    viewModel.filtro.value = ""
                }
                return false
            }
        })
    }
}