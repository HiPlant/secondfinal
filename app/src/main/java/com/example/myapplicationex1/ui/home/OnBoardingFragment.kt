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
import com.example.myapplicationex1.R
import com.example.myapplicationex1.TodayPlantItem
import com.example.myapplicationex1.databinding.ActivityMainBinding
import com.example.myapplicationex1.databinding.FragmentOnBoardingBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.ibrahimsn.lib.SmoothBottomBar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class OnBoardingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var ViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var binding: FragmentOnBoardingBinding

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
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val smoothBottomBar = requireActivity().findViewById<SmoothBottomBar>(R.id.navview)
        smoothBottomBar.visibility = View.GONE

        setFragmentResultListener("toOnBoarding") { key, bundle ->
            val result = bundle.getString("itemToOnBoarding")
            val plant = Json.decodeFromString<TodayPlantItem>(result!!)
            binding.OnBoardingImg.setImageResource(plant.pDescImg)
            Log.i("onBoarding","$plant")
            setFragmentResult("ToFinish", bundleOf("ToFinishItem" to result))
        }

        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        //Adapter 안에 ViewPager2 상에 띄워줄 fragmentList 생성
        val fragmentList = listOf(FirstRegisterFragment(), SecondRegisterFragment(), ThirdRegisterFragment())

        //ViewPagerAdapter 초기화
        ViewPagerAdapter = OnBoardingViewPagerAdapter(this)
        ViewPagerAdapter.fragments.addAll(fragmentList)

        //ViewPager2와 Adapter 연동
        binding.vpRegister.adapter = ViewPagerAdapter
        binding.dotsIndicator.attachTo(binding.vpRegister)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}