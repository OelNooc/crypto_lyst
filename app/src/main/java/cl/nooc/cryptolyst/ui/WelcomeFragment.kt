package cl.nooc.cryptolyst.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cl.nooc.cryptolyst.R
import cl.nooc.cryptolyst.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.findNavController(requireView())
                .navigate(R.id.action_welcomeFragment_to_homeFragment)
        }, 5000)
        return binding.root
    }

}