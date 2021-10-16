package com.alirizakaygusuz.sosyofi.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentLoginBinding
import com.alirizakaygusuz.sosyofi.isValidEmail
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback


class LoginFragment() : Fragment() {


    private lateinit var binding: FragmentLoginBinding


    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
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
        val action =
            com.alirizakaygusuz.sosyofi.view.LoginFragmentDirections.actionLoginFragmentToRegisterFirstFragment()
        Navigation.findNavController(view).navigate(action)
    }


    fun click_btnRSecondOK(view: View) {


        val email: String = binding.txtLoginEmail.text.toString().trim()
        val password: String = binding.txtLoginPassword.text.toString().trim()

        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(context, "Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty()) {
            Toast.makeText(context, "Email Alanını Boş Bırakmayınız!!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Şifre Alanını Boş Bırakılmamalıdır!!", Toast.LENGTH_SHORT)
                .show()
        } else if (!email.isEmpty() && !password.isEmpty()) {


            if (email.isValidEmail()) {

                login(email, password)

            } else {
                Toast.makeText(context, "Geçerli Bir Email Giriniz!!", Toast.LENGTH_SHORT)
                    .show()
            }


        }

    }


    fun login(email: String, password: String) {

        sosyofiAPI.userLogin(email, password).enqueue(object : Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: retrofit2.Response<SosyofiAPIReply>?,
            ) {

                if (response != null) {

                    val responseBody = response.body()

                    if (responseBody?.success == 1) {
                        //if(response.body()?.success == 1){
                        val user_id: Int? = response.body()?.user_id

                        if (user_id != null) {
                            val intent = Intent(context, UserMainActivity::class.java)
                            intent.putExtra("userId", user_id)
                            context?.startActivity(intent)
                        }

                    } else {
                        Toast.makeText(context,
                            "Böyle bir hesap bulunmamaktadır!!",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.i("Hata ::", t?.message.toString())
            }

        })


    }


}