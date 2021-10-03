package com.alirizakaygusuz.sosyofi


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterFirstBinding
import android.text.style.UnderlineSpan

import android.text.SpannableString

import android.R
import android.text.TextUtils
import android.util.Patterns

import android.widget.TextView





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
        binding = FragmentRegisterFirstBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnRFirstOK.setOnClickListener { view ->
            click_btnRFirstOK(view)
        }

        binding.txtRFirstLogin.setOnClickListener { view ->
            click_txtRFirstLogin(view)
        }
    }


    fun click_btnRFirstOK(view: View) {

        val email: String = binding.txtRFirstEmail.text.toString().trim()
        val password: String = binding.txtRFirstPassword.text.toString().trim()

        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(context, "Tüm Alanları Doldurunuz!!", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty()) {
            Toast.makeText(context, "Email Alanını Boş Bırakmayınız!!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Şifre Alanını Boş Bırakılmamalıdır!!", Toast.LENGTH_SHORT)
                .show()
        } else if (!email.isEmpty() && !password.isEmpty()) {


            if(email.isValidEmail()){
                val action =
                    RegisterFirstFragmentDirections.actionRegisterFirstFragmentToRegisterSecondFragment(
                        email,
                        password)
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(context, "Geçerli Bir Email Giriniz!!", Toast.LENGTH_SHORT)
                    .show()
            }




        }


    }

    fun click_txtRFirstLogin(view: View) {


        val action = RegisterFirstFragmentDirections.actionRegisterFirstFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }




}