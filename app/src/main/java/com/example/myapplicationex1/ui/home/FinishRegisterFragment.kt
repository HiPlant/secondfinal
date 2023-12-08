package com.example.myapplicationex1.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplicationex1.InputData
import com.example.myapplicationex1.MyPlantsDatabase
import com.example.myapplicationex1.MyPlantsEntity
import com.example.myapplicationex1.R
import com.example.myapplicationex1.TodayPlantItem
import com.example.myapplicationex1.databinding.FragmentFinishRegisterBinding
import com.example.myapplicationex1.databinding.FragmentThirdRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.ibrahimsn.lib.SmoothBottomBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FinishRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FinishRegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var myPlants :MyPlantsDatabase? = null
    private lateinit var binding: FragmentFinishRegisterBinding
    lateinit var myNickName: TextView

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
        myPlants = MyPlantsDatabase.getInstance(requireContext())
        binding = FragmentFinishRegisterBinding.inflate(inflater, container, false)
        setFragmentResultListener("BoardingToFinish"){key, bundle ->
            val result = bundle.getString("BoardingToFinishItem")
            val inputData = Json.decodeFromString<InputData>(result!!)
            myNickName = binding.MyNickName
            myNickName.text = "${inputData.nickName}이(가) 등록되었습니다!"
            Log.i("vp","${inputData} 내 닉네임은 : ${myNickName}")
        }

        setFragmentResultListener("ToFinish"){key, bundle ->
            val result = bundle.getString("ToFinishItem")
            val plantData = Json.decodeFromString<TodayPlantItem>(result!!)
            Log.i("vp", "${plantData}이녀석맞나?")
            CoroutineScope(Dispatchers.Main).launch {

            }

        }
        //val receivedValue = arguments?.getString("ThirdToFinish")
        //val inputData = Json.decodeFromString<InputData>(receivedValue!!)
        //Log.i("vp","${inputData} 내 닉네임은 : ${myNickName}")
        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(R.id.action_finishRegisterFragment_to_navigation_home)
            val smoothBottomBar = requireActivity().findViewById<SmoothBottomBar>(R.id.navview)
            smoothBottomBar.visibility = View.VISIBLE// 실행 할 코드
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
         * @return A new instance of fragment FinishRegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FinishRegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}