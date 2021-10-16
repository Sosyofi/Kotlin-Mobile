package com.alirizakaygusuz.sosyofi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterSecondBinding
import com.alirizakaygusuz.sosyofi.isValidTextControl
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterSecondFragment : Fragment() {

    private lateinit var binding: FragmentRegisterSecondBinding

    private lateinit var user: User

    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()


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
        val action =
            com.alirizakaygusuz.sosyofi.view.RegisterSecondFragmentDirections.actionRegisterSecondFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }


    fun click_btnRSecondOK(view: View) {

        val email: String
        val password: String

        val firstName: String = binding.txtRSecondFirstName.text.toString().trim()
        val lastName: String = binding.txtRSecondLastNamee.text.toString().trim()
        val userName: String = binding.txtRSecondUsername.text.toString().trim()


        if (!firstName.isValidTextControl(firstName) || !lastName.isValidTextControl(lastName)) {
            Toast.makeText(context,
                "İsminiz ve Soyisminiz Sadece Harflerden Oluşmalıdır!!",
                Toast.LENGTH_SHORT)
                .show()
        } else if (firstName.isValidTextControl(firstName) && lastName.isValidTextControl(lastName)) {
            if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty()) {
                Toast.makeText(context, "Tüm Alanları Doldurunuz!!", Toast.LENGTH_SHORT).show()
            } else {
                arguments?.let {
                    email =
                        com.alirizakaygusuz.sosyofi.view.RegisterSecondFragmentArgs.fromBundle(
                            it).email
                    password =
                        com.alirizakaygusuz.sosyofi.view.RegisterSecondFragmentArgs.fromBundle(it).password

                    user = User(userName, firstName, lastName, email, password)
                    user?.let {
                        registerUser(it, view)

                    }
                }

            }

        }

    }


    fun registerUser(user: User, view: View) {

        Log.i("User:", user.toString())

        sosyofiAPI.userRegister(user.nickname,
            user.first_name,
            user.last_name,
            user.email,
            user.hashed_password).enqueue(object : Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: Response<SosyofiAPIReply>?,
            ) {

                if (response != null) {

                    val responseBody = response.body()

                    if (responseBody?.success == 1) {
                        //if(response.body()?.success == 1){
                        val user_id:Int? = response.body()?.user_id

                        if (user_id != null) {
                            val intent = Intent(context, UserMainActivity::class.java)
                            intent.putExtra("userId", user_id)
                            context?.startActivity(intent)
                        }

                    } else {
                        if (response.body()?.message.toString().equals("invalidEmail")) {
                            val action =
                                com.alirizakaygusuz.sosyofi.view.RegisterSecondFragmentDirections.actionRegisterSecondFragmentToLoginFragment()
                            Navigation.findNavController(view).navigate(action)
                            Toast.makeText(context,
                                "Bu email kullanılmaktadır farklı bir email adresi giriniz!!",
                                Toast.LENGTH_LONG).show()
                        } else if (response.body()?.message.toString().equals("nicknameExists")) {
                            binding.txtRSecondUsername.setText("", TextView.BufferType.EDITABLE)
                            Toast.makeText(context,
                                "Bu kullanıcı adı kullanılmaktadır farklı bir kullanıcı adı adresi giriniz!!",
                                Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context,
                                "${response.body()?.message.toString()}",
                                Toast.LENGTH_LONG).show()
                        }


                    }


                }

            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.e("Failled:  ", t?.message.toString())
            }


        })

    }
}