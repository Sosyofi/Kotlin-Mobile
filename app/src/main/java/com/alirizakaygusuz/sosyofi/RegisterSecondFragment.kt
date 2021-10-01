package com.alirizakaygusuz.sosyofi

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterSecondBinding


class RegisterSecondFragment : Fragment() {

    private lateinit var binding : FragmentRegisterSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterSecondBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.txtRSecondLogin.setOnClickListener { view ->
            click_txtRSecondLogin(view)
        }
    }

    fun click_txtRSecondLogin(view: View) {
        val action = RegisterSecondFragmentDirections.actionRegisterSecondFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }


}