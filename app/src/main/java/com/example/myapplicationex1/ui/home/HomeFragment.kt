package com.example.myapplicationex1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myapplicationex1.R
import com.example.myapplicationex1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var navController: NavController
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding.register.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_onBoardingFragment)
        }

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        return _binding.root
    }

}