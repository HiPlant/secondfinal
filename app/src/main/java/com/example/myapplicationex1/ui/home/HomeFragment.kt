package com.example.myapplicationex1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationex1.MainActivity
import com.example.myapplicationex1.MyPlantItem
import com.example.myapplicationex1.MyPlantsDatabase
import com.example.myapplicationex1.PlantsDatabase
import com.example.myapplicationex1.PlantsEntity
import com.example.myapplicationex1.R
import com.example.myapplicationex1.TodayPlantItem
import com.example.myapplicationex1.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToString
import java.util.Collections.copy

class HomeFragment : Fragment() {
//    private var db: PlantsDatabase? = null

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var navController: NavController
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var linearLayoutManager: RecyclerView.LayoutManager? = null
    private var todayPlantAdapter: RecyclerView.Adapter<TodayPlantAdapter.TodayPlantViewHolder>? = null
    private var linearVerticalLayoutManager: RecyclerView.LayoutManager? = null
    private var myPlantAdapter: RecyclerView.Adapter<MyPlantAdapter.MyPlantViewHolder>? = null
    var db: PlantsDatabase? = null
    var myPlants : MyPlantsDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        db = PlantsDatabase.getInstance(requireContext())
        myPlants = MyPlantsDatabase.getInstance(requireContext())


        val todayPlantRecycler = _binding.root.findViewById<RecyclerView>(R.id.TodayPlantRecycler)
        val myPlantRecycler = _binding.MyPlantRecycler

        val itemList = ArrayList<TodayPlantItem>()
        val myItemList = ArrayList<MyPlantItem>()
        CoroutineScope(Dispatchers.IO).launch{
            for(info in db!!.plantsDao().getAll()){
                itemList.add(TodayPlantItem(info.pID,info.pEngName,info.pKorName,info.pImg,info.pDesImg,info.desc,info.categoryImg,info.isLiked))
            }
            Log.i("db", "${myPlants!!.myPlantsDao().getAll()}")
            for(item in myPlants!!.myPlantsDao().getAll()){
                myItemList.add(MyPlantItem(item.pID,item.pEngName,item.pImg,item.pDescImg,item.desc,item.categoryImg,item.nickName,item.lastWater,item.sun,item.temperature))
            }
        }
        //val item1 = TodayPlantItem(1,"Round Cactus","성게 선인장",R.drawable.jaemin_round_cactus,false)
        //itemList.add(item1)
        _binding.ToSearch.setOnClickListener {
            setFragmentResult("toSearch", bundleOf("itemToSearch" to Json.encodeToString(itemList)))
            findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
        }
        todayPlantAdapter = TodayPlantAdapter(itemList)
        myPlantAdapter = MyPlantAdapter(myItemList)

        (todayPlantAdapter as? TodayPlantAdapter)?.setImageClickListener(object: TodayPlantAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 상세페이지로 가는 코드
                    setFragmentResult("plantsElement", bundleOf("item" to Json.encodeToString(itemList[position])))
                    findNavController().navigate(R.id.action_navigation_home_to_registerPlantFragment)
            }
        })
        (myPlantAdapter as? MyPlantAdapter)?.setImageClickListener(object: MyPlantAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 상세페이지로 가는 코드
                setFragmentResult("myplantsElement", bundleOf("myitem" to Json.encodeToString(myItemList[position])))
                findNavController().navigate(R.id.action_navigation_home_to_myPlantDetailFragment)
            }
        })
        (todayPlantAdapter as? TodayPlantAdapter)?.setHeartClickListener(object: TodayPlantAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 찜하기 코드
                CoroutineScope(Dispatchers.Main).launch {
                    val temp: Deferred<Boolean> = async(Dispatchers.IO) { // async 로 결과를 반환
                        val pID = itemList[position].pID
                        val isLiked = db!!.plantsDao().getIsLiked(pID)
                        if (isLiked) { // 이미 즐겨찾기 되어있으면 삭제
                            db!!.plantsDao().updatePlant(!isLiked,pID)
                            itemList.set(position,itemList[position].copy(isLiked=!isLiked))
                            //(todayPlantAdapter as? TodayPlantAdapter)?.notifyDataSetChanged()
                            Log.i("plz","${db!!.plantsDao().getAll()}${pID}${isLiked}")
                            false
                        } else { // 없으면 즐겨찾기 저장
                            db!!.plantsDao().updatePlant(!isLiked,pID)
                            itemList.set(position,itemList[position].copy(isLiked=!isLiked))

                            Log.i("plz","${db!!.plantsDao().getAll()}${pID}${isLiked}")
                            true
                        }
                    }
                    // UI 변경
                    val selected = temp.await();
                    (todayPlantAdapter as? TodayPlantAdapter)?.notifyDataSetChanged()// async 작업이 완료 되고 난 후 호출.
                }
            }
        })
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        linearVerticalLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

        todayPlantRecycler?.adapter = todayPlantAdapter
        todayPlantRecycler?.layoutManager = linearLayoutManager
        myPlantRecycler?.adapter = myPlantAdapter
        myPlantRecycler?.layoutManager = linearVerticalLayoutManager

        Log.i("plz","${itemList}${todayPlantAdapter}${linearLayoutManager}")
//        db = PlantsDatabase.getInstance(requireContext())
//        db!!.parkDao().saveBookmark(PlantsEntity(1,"Round Cactus","성게 선인장","jaemin_round_cactus.png","outdoor_category.png","jaemin_round_cactus_desc.png",false))
//        Log.i("plz","$db")

        val toDescButton = _binding.root.findViewById<ImageView>(R.id.ToDescBtn)

        CoroutineScope(Dispatchers.IO).launch {
            val plantsData = db!!.plantsDao().getAll()
            Log.i("plz", "${plantsData}")
        }

        return _binding.root
    }

}