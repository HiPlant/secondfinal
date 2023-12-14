package com.example.myapplicationex1.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplicationex1.MyPlantItem
import com.example.myapplicationex1.R
import com.example.myapplicationex1.databinding.FragmentMyPlantDetailBinding
import com.example.myapplicationex1.databinding.FragmentWateringBinding
import kotlinx.serialization.json.Json
import me.ibrahimsn.lib.SmoothBottomBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WateringFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WateringFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentWateringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWateringBinding.inflate(inflater, container, false)
        setFragmentResultListener("towatering"){key,bundle ->
            val result = bundle.getString("towateringitem")
            val myPlant = Json.decodeFromString<MyPlantItem>(result!!)
            binding.MyWateringNickName.text = "${myPlant.nickName}(이)가 쑥쑥 자라는중~"
        }
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            val smoothBottomBar = requireActivity().findViewById<SmoothBottomBar>(R.id.navview)
            smoothBottomBar.visibility = View.VISIBLE// 실행 할 코드
            findNavController().navigate(R.id.action_wateringFragment_to_navigation_home)
        }, 5000)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WateringFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WateringFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}