package com.alirizakaygusuz.sosyofi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterFirstBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentRegisterSecondBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentTwitchBinding


class TwitchFragment : Fragment() {

    private lateinit var binding: FragmentTwitchBinding

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


}