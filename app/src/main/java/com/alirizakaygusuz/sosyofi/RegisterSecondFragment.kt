package com.alirizakaygusuz.sosyofi

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterSecondBinding
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception


class RegisterSecondFragment: Fragment() {

    private lateinit var binding: FragmentRegisterSecondBinding

    private lateinit var user: User


    //private val URL = "http://localhost/php-auth/PHP-Web/signup.php"
    private val URL = "http://192.168.1.104/php-auth/PHP-Web/includes/signup.inc.php"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.txtRSecondLogin.setOnClickListener {
            click_txtRSecondLogin(it)
        }


        binding.btnRSecondOK.setOnClickListener {
            click_btnRSecondOK(it)
        }
    }


    fun click_txtRSecondLogin(view: View) {
        val action = RegisterSecondFragmentDirections.actionRegisterSecondFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }


    fun click_btnRSecondOK(view: View) {


        val email: String
        val password: String

        val firstName: String = binding.txtRSecondFirstName.text.toString()
        val lastName: String = binding.txtRSecondLastNamee.text.toString()
        val userName: String = binding.txtRSecondUsername.text.toString()

        arguments?.let {
            email = RegisterSecondFragmentArgs.fromBundle(it).email
            password = RegisterSecondFragmentArgs.fromBundle(it).password
            //val hash_password = password.hashCode()
            Log.i("Ratata:", "$email  $password")
            user = User(userName, firstName, lastName, email, password)

            user?.let {
                postUserRegister(it)
            }
        }

        Log.i("User:",user.toString())

    }


    fun postUserRegister(user: User){

        val request = object : StringRequest(Method.POST,URL,Response.Listener { reply ->

            Log.i("User Add Reply :",reply)

        },Response.ErrorListener { e -> e.printStackTrace() }){

            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String,String>()

                params["nickname"] = user.nickname
                params["first_name"] = user.first_name
                params["last_name"] = user.last_name
                params["email"] = user.email
                params["hashed_password"] = user.hashed_password


                return params
            }
        }

        Volley.newRequestQueue(activity).add(request)
    }
}