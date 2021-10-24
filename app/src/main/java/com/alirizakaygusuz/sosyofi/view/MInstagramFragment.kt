package com.alirizakaygusuz.sosyofi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.databinding.FragmentMinstagramBinding
import com.alirizakaygusuz.sosyofi.model.User

class MInstagramFragment : Fragment() {


    private lateinit var binding: FragmentMinstagramBinding

    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMinstagramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            user = com.alirizakaygusuz.sosyofi.view.TwitchFragmentArgs.fromBundle(
                it).user
        }


        binding.imgvInstagramRight.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.MInstagramFragmentDirections.actionMInstagramFragmentToTwitterFragment(
                    user)

            Navigation.findNavController(it).navigate(action)
        }


        binding.imgvInstagramLeft.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.MInstagramFragmentDirections.actionMInstagramFragmentToTwitterFragment(
                    user)
            Navigation.findNavController(it).navigate(action)

        }


        binding.txtInstagram.text = user.instagram

    }


}