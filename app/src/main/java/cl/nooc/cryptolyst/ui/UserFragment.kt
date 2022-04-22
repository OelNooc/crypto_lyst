package cl.nooc.cryptolyst.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import cl.nooc.cryptolyst.R
import cl.nooc.cryptolyst.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().
        getSharedPreferences("usuario", AppCompatActivity.MODE_PRIVATE)

        if(sharedPreferences.getInt("valor",0) == 0){
            sharedPreferences.edit().putInt("valor",1).commit()
        }

        with(binding){

            btnIngreso.setOnClickListener {
                val usuario = etUsuario.text.toString()
                if(usuario.isNotEmpty()) {
                    if(!revisarUsuario()) {
                        sharedPreferences.edit().putString("user${getValor()}", usuario).commit()
                        sharedPreferences.edit().putInt("valor", getValor() + 1).commit()
                        sharedPreferences = requireActivity().
                        getSharedPreferences(usuario, AppCompatActivity.MODE_PRIVATE)
                        val bundle = Bundle()
                        bundle.putString("nom", usuario)
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_userFragment_to_welcomeFragment, bundle)
                    }
                } else{
                    etUsuario.error = "Este campo no puede estar vacío"
                }
            }
        }
        return binding.root
    }

    private fun getValor():Int = sharedPreferences.getInt("valor", 0)

    private fun revisarUsuario(): Boolean{
        with(binding){
            val user = etUsuario.text.toString()

            for(i in 0..getValor()){
                val texto = sharedPreferences.getString("user$i", "")
                if(user == texto){
                    val bundle = Bundle()
                    bundle.putString("nom", user)

                    Navigation.findNavController(requireView()).
                    navigate(R.id.action_userFragment_to_homeFragment, bundle)
                    return true
                }
            }
            return false
        }
    }

}