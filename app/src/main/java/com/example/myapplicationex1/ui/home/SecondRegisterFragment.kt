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
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationex1.InputData
import com.example.myapplicationex1.R
import com.example.myapplicationex1.databinding.FragmentFirstRegisterBinding
import com.example.myapplicationex1.databinding.FragmentSecondRegisterBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondRegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSecondRegisterBinding
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
        binding = FragmentSecondRegisterBinding.inflate(inflater, container, false)
        binding.SecondToThird.setOnClickListener{
            var vp = parentFragment?.activity?.findViewById<ViewPager2>(R.id.vpRegister)
            var current = vp?.currentItem
            setFragmentResultListener("FirstToSecond") { key, bundle ->
                val result = bundle.getString("FirstToSecondItem")
                val info1 = Json.decodeFromString<InputData>(result!!)
                var info2 = info1.copy(sun = binding.Sun.text.toString())
                setFragmentResult("SecondToThird", bundleOf("SecondToThirdItem" to Json.encodeToString(info2)))
                Log.i("vp", "$vp$current$info1$info2")
            }
            if(current == 1) {
                vp?.setCurrentItem(current+1, true)
            }
        }
        binding.SecondToFirst.setOnClickListener{
            var vp = parentFragment?.activity?.findViewById<ViewPager2>(R.id.vpRegister)
            var current = vp?.currentItem

            if(current == 1) {
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
         * @return A new instance of fragment SecondRegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondRegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}