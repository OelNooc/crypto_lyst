package cl.nooc.cryptolyst.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import cl.nooc.cryptolyst.R
import cl.nooc.cryptolyst.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val user = arguments?.getString("nom")
        sharedPreferences = requireActivity().
        getSharedPreferences(user, AppCompatActivity.MODE_PRIVATE)

        with(binding){
            tvUserReg.text = user
        }
        return binding.root
    }

}