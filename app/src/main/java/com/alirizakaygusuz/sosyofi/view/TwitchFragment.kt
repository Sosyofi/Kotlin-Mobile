package com.alirizakaygusuz.sosyofi.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentTwitchBinding
import com.alirizakaygusuz.sosyofi.model.User


class TwitchFragment : Fragment() {

    private lateinit var binding: FragmentTwitchBinding
    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTwitchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.let {
            user =      com.alirizakaygusuz.sosyofi.view.TwitchFragmentArgs.fromBundle(
                it).user
        }



        binding.imgvTwitchLeft.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.TwitchFragmentDirections.actionTwitchFragmentToUnsplashFragment(
                user)
            Navigation.findNavController(it).navigate(action)


        }

        binding.imgvTwitchRight.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.TwitchFragmentDirections.actionTwitchFragmentToMInstagramFragment(
                    user)
            Navigation.findNavController(it).navigate(action)

        }


        binding.txtTwitch.text = user.twitch

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(user.twitch!!)

    }


}