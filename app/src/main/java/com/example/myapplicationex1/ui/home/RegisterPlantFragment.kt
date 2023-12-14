package com.example.myapplicationex1.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplicationex1.R
import com.example.myapplicationex1.TodayPlantItem
import com.example.myapplicationex1.databinding.FragmentOnBoardingBinding
import com.example.myapplicationex1.databinding.FragmentRegisterPlantBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.json.Json.Default.encodeToString
import kotlinx.serialization.json.decodeFromJsonElement
import me.ibrahimsn.lib.SmoothBottomBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterPlantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterPlantFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentRegisterPlantBinding
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
        // Inflate the layout for this fragment
        binding = FragmentRegisterPlantBinding.inflate(inflater, container, false)
        val smoothBottomBar = requireActivity().findViewById<SmoothBottomBar>(R.id.navview)

        setFragmentResultListener("plantsElement") { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("item")
            // Do something with the result...
            Log.i("plz","$result")
            val plant = Json.decodeFromString<TodayPlantItem>(result!!)
            binding.DescPlantImg.setImageResource(plant.pDescImg)
            binding.PlantDesc.setImageResource(plant.pDesc)
            binding.NameOfPlant.setText(plant.pKorName)
            Log.i("plz","${plant.pEngName}")
            setFragmentResult("toOnBoarding",bundleOf("itemToOnBoarding" to result))
        }



        smoothBottomBar.visibility = View.GONE



        binding.RegisterBtn.setOnClickListener{
            findNavController().navigate(R.id.action_registerPlantFragment_to_onBoardingFragment)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterPlantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterPlantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}