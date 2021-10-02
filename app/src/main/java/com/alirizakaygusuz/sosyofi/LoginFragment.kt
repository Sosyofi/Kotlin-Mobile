package com.alirizakaygusuz.sosyofi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentLoginBinding
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class LoginFragment : Fragment() {

    private lateinit var constraintLayout: ConstraintLayout

    private lateinit var binding: FragmentLoginBinding


    private lateinit var myQueue: RequestQueue

    private lateinit var myListener: Response.Listener<JSONObject>

    private val myUrlGet = "https://reqres.in/api/users?page=2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.txtLoginRegister.setOnClickListener { view ->
            click_txtLoginRegister(view)
        }


        binding.btnLoginOK.setOnClickListener { view ->
            click_btnRSecondOK(view)
        }
    }


    fun click_txtLoginRegister(view: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFirstFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun click_btnRSecondOK(view: View) {

    }

}