package com.alirizakaygusuz.sosyofi


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterFirstBinding





class RegisterFirstFragment : Fragment() {




    private lateinit var binding: FragmentRegisterFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterFirstBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val rightConstraintLayout = getView()?.findViewById(R.id.constraintLeftRFirst) as ConstraintLayout













        binding.btnRFirstOK.setOnClickListener { view->
            click_btnRFirstOK(view)
        }

        binding.txtRFirstLogin.setOnClickListener { view ->
            click_txtRFirstLogin(view)
        }
    }


    fun click_btnRFirstOK(view: View) {

        val email: String = binding.txtRFirstEmail.text.toString()
        val password: String = binding.txtRFirstPassword.text.toString()

        val action =  RegisterFirstFragmentDirections.actionRegisterFirstFragmentToRegisterSecondFragment(email , password)
        Navigation.findNavController(view).navigate(action)

    }

    fun click_txtRFirstLogin(view: View) {




        val action = RegisterFirstFragmentDirections.actionRegisterFirstFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }





}