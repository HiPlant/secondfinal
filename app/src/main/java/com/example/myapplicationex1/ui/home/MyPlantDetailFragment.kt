package com.example.myapplicationex1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplicationex1.MyPlantItem
import com.example.myapplicationex1.R
import com.example.myapplicationex1.databinding.FragmentFinishRegisterBinding
import com.example.myapplicationex1.databinding.FragmentMyPlantDetailBinding
import kotlinx.serialization.json.Json
import me.ibrahimsn.lib.SmoothBottomBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPlantDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPlantDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMyPlantDetailBinding
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
        val smoothBottomBar = requireActivity().findViewById<SmoothBottomBar>(R.id.navview)
        binding = FragmentMyPlantDetailBinding.inflate(inflater, container, false)

        setFragmentResultListener("myplantsElement"){key, bundle ->
            val result = bundle.getString("myitem")
            val myPlant = Json.decodeFromString<MyPlantItem>(result!!)
            binding.MyNameOfPlant.text = myPlant.nickName
            binding.MyPlantDesc.setImageResource(myPlant.desc)
            binding.MyDescPlantImg.setImageResource(myPlant.pDescImg)
            setFragmentResult("towatering",bundleOf("towateringitem" to result))
        }

        binding.WateringBtn.setOnClickListener{
            findNavController().navigate(R.id.action_myPlantDetailFragment_to_wateringFragment)
        }

        smoothBottomBar.visibility = View.GONE
        // Inflate the layout for this fragment
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPlantDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}