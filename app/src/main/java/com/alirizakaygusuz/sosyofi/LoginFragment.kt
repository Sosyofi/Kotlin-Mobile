package com.alirizakaygusuz.sosyofi


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.parseAsHtml
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentLoginBinding
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception


class LoginFragment() : Fragment() {


    private lateinit var binding: FragmentLoginBinding


    //private val URL = "http://localhost/php-auth/PHP-Web/signup.php"

    private val URL = "http://192.168.1.104/php-auth/PHP-Web/includes/mobile_login.inc.php"


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


    fun login(email: String, password: String){

        var user = User()
        var followerUser = User()
        var bos = ""



        val request = object : StringRequest(Method.POST, URL, Response.Listener { reply ->

            Log.i("Search Reply:", reply)


            try {

                val jsonObject = JSONObject(reply)
                val userList = jsonObject.getJSONArray("user")


                var followerUserList = ArrayList<User>()

                for (i in 0 until userList.length()) {

                    val userJson = userList.getJSONObject(i)


                    user.set(userJson.getInt("id"))
                    user.first_name = userJson.getString("first_name")
                    user.last_name = userJson.getString("last_name")
                    user.email = userJson.getString("email")
                    user.nickname = userJson.getString("nickname")

                     userJson.getString("instagram").equals("null").let { result->
                         if(result){
                             user.instagram = ""
                         }else{
                             user.instagram =  userJson.getString("instagram")
                         }

                     }



                    userJson.getString("twitch").equals("null").let { result->

                        if(result){
                            user.twitch = ""
                        }else{
                            user.twitch = userJson.getString("twitch")

                        }

                    }

                    userJson.getString("twitter").equals("null").let { result->
                        if(result){
                            user.twitter = ""
                        }else{
                            user.twitter = userJson.getString("twitter")

                        }

                    }

                    userJson.getString("unsplash").equals("null").let { result->
                        if(result){
                            user.unsplash = ""
                        }else{
                            user.unsplash = userJson.getString("unsplash")

                        }

                    }

                    userJson.getString("bio").equals("null").let { result->
                        if(result){
                            user.bio = ""
                        }else{
                            user.bio =  userJson.getString("bio")

                        }


                    }

                    user.followers_count = userJson.getInt("followers_count")
                    user.followed_count = userJson.getInt("followed_count")
                   //val followers:List<User> = userJson.getJSONArray("followers")

                    val control = userJson.getString("followers")

                    Log.i("Deneme0", control)

                    if(!control.equals("null")){
                        val followerUserJsonObject= userJson.getJSONObject("followers")

                        val followerUserJsonArray= followerUserJsonObject.getJSONArray("follower_user")


                        for (i in 0 until followerUserJsonArray.length()) {
                            val followerUserJson = followerUserJsonArray.getJSONObject(i)

                            followerUser.set(userJson.getInt("id"))
                            followerUser.first_name = followerUserJson.getString("first_name")
                            followerUser.last_name = followerUserJson.getString("last_name")
                            followerUser.email = followerUserJson.getString("email")
                            followerUser.nickname = followerUserJson.getString("nickname")
                            followerUser.instagram = followerUserJson.getString("instagram")
                            followerUser.twitch = followerUserJson.getString("twitch")
                            followerUser.twitter = followerUserJson.getString("twitter")
                            followerUser.unsplash =  followerUserJson.getString("unsplash")
                            followerUser.bio = followerUserJson.getString("bio")

                            followerUser.followers_count = followerUserJsonObject.getInt("followers_count")

                            //Log.i("Follower",userJson.getJSONObject("followers").toString())

                            followerUserList.add(followerUser)
                        }

                    }


                    //followerList.add(follower)

                    bos = "asdasdsadsadsadasdasdasdsa"


                    Log.i("**************:", "**************")
                    Log.i("User first name:", user.first_name)
                    Log.i("User last name:", user.last_name)
                    Log.i("User mail:", user.email)
                    Log.i("User nickname:", user.nickname)
                    Log.i("User instagram:", user.instagram)
                    Log.i("User twitch:", user.twitch)
                    Log.i("User twitter:", user.twitter)
                    Log.i("User unsplash:", user.unsplash)
                    Log.i("User bio:", user.bio)
                    Log.i("**************:", "**************")

                    //kontrol sağlanmalı !!!
                    val intent = Intent(context, UserMainActivity::class.java)
                    intent.putExtra("user",user)
                    intent.putExtra("followerUserList", followerUserList)
                   // intent.putExtras("")
                    context?.startActivity(intent)

                }



            } catch (e: JSONException) {
                e.printStackTrace()
            }



        }, Response.ErrorListener { error -> error.printStackTrace() }) {

            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String, String>()

                params["email"] = email
                params["hashed_password"] = password

                return params
            }


        }

        Volley.newRequestQueue(context).add(request)
    }




}