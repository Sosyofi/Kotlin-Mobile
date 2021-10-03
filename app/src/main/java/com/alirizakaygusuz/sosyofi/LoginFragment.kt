package com.alirizakaygusuz.sosyofi


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentLoginBinding


class LoginFragment() : Fragment() {


    private lateinit var binding: FragmentLoginBinding


    //private val URL = "http://localhost/php-auth/PHP-Web/signup.php"

    private val URL = "http://localhost/php-auth/PHP-Web/includes/login.inc.php"


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
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFirstFragment()
        Navigation.findNavController(view).navigate(action)
    }


    fun click_btnRSecondOK(view: View) {

        //val user: User = User(email = email, hashed_password = password)

        val email: String = binding.txtLoginEmail.text.toString().trim()
        val password: String = binding.txtLoginPassword.text.toString().trim()

        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(context, "Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty()) {
            Toast.makeText(context, "Email Alanını Boş Bırakmayınız!!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Şifre Alanını Boş Bırakılmamalıdır!!", Toast.LENGTH_SHORT).show()
        } else if (!email.isEmpty() && !password.isEmpty()) {



            if(email.isValidEmail()){
                val intent = Intent(context, UserMainActivity::class.java)
                context?.startActivity(intent)
            }else{
                Toast.makeText(context, "Geçerli Bir Email Giriniz!!", Toast.LENGTH_SHORT)
                    .show()
            }


        }


    }


}