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
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationex1.InputData
import com.example.myapplicationex1.R
import com.example.myapplicationex1.databinding.FragmentHomeBinding
import com.example.myapplicationex1.databinding.FragmentThirdRegisterBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdRegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentThirdRegisterBinding

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
        binding = FragmentThirdRegisterBinding.inflate(inflater, container, false)
        binding.finishRegister.setOnClickListener{
            setFragmentResultListener("SecondToThird"){key, bundle ->
                val result = bundle.getString("SecondToThirdItem")
                val info2 = Json.decodeFromString<InputData>(result!!)
                val info3 = info2.copy(temperature = binding.Temp.text.toString())
                setFragmentResult("ThirdToFinish", bundleOf("ThirdToFinishItem" to Json.encodeToString(info3)))
                Log.i("vp","${info2}${info3}")
            }
            findNavController().navigate(R.id.action_onBoardingFragment_to_finishRegisterFragment)
        }
        binding.ThirdToSecond.setOnClickListener{
            var vp = parentFragment?.activity?.findViewById<ViewPager2>(R.id.vpRegister)
            var current = vp?.currentItem
            Log.i("vp","$vp$current")
            if(current == 2) {
                vp?.setCurrentItem(current-1, true)
            }
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
         * @return A new instance of fragment ThirdRegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdRegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}