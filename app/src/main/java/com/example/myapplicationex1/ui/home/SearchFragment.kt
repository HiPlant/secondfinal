package com.example.myapplicationex1.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationex1.PlantsDatabase
import com.example.myapplicationex1.R
import com.example.myapplicationex1.TodayPlantItem
import com.example.myapplicationex1.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSearchBinding
    private var gridLayoutManager: RecyclerView.LayoutManager? = null
    private var searchPlantAdapter: RecyclerView.Adapter<SearchPlantAdapter.SearchPlantViewHolder>? = null
    lateinit var allItemList: ArrayList<TodayPlantItem>
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
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        var db = PlantsDatabase.getInstance(requireContext())
        var itemList: ArrayList<TodayPlantItem> = ArrayList()
        val searchPlantRecycler = binding.root.findViewById<RecyclerView>(R.id.TodayPlantRecyclerSearch)
        setFragmentResultListener("toSearch"){key, bundle ->
            val result = bundle.getString("itemToSearch")
            allItemList = Json.decodeFromString<ArrayList<TodayPlantItem>>(result!!)
            for(i in allItemList){
                itemList.add(i)
            }
            searchPlantAdapter = SearchPlantAdapter(itemList)
            (searchPlantAdapter as? SearchPlantAdapter)?.setImageClickListener(object: SearchPlantAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int) {
                    // 상세페이지로 가는 코드
                    setFragmentResult("plantsElement", bundleOf("item" to Json.encodeToString(itemList[position])))
                    findNavController().navigate(R.id.action_searchFragment_to_registerPlantFragment)
                }
            })
            (searchPlantAdapter as? SearchPlantAdapter)?.setHeartClickListener(object: SearchPlantAdapter.OnItemClickListener{
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
                        (searchPlantAdapter as? SearchPlantAdapter)?.notifyDataSetChanged()// async 작업이 완료 되고 난 후 호출.
                    }
                }
            })
            searchPlantRecycler?.adapter = searchPlantAdapter
            gridLayoutManager = GridLayoutManager(activity, 2,GridLayoutManager.VERTICAL, false)
            searchPlantRecycler?.layoutManager = gridLayoutManager

            binding.Search.addTextChangedListener{
                itemList= ArrayList()
                for(item in allItemList){
                    if(item.pKorName.trim().contains(binding.Search.text.toString().trim())
                        || item.pEngName.trim().contains(binding.Search.text.toString().trim())){
                        itemList.add(item)
                    }
                }
                searchPlantAdapter = SearchPlantAdapter(itemList)
                (searchPlantAdapter as? SearchPlantAdapter)?.setImageClickListener(object: SearchPlantAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        // 상세페이지로 가는 코드
                        setFragmentResult("plantsElement", bundleOf("item" to Json.encodeToString(itemList[position])))
                        findNavController().navigate(R.id.action_searchFragment_to_registerPlantFragment)
                    }
                })
                (searchPlantAdapter as? SearchPlantAdapter)?.setHeartClickListener(object: SearchPlantAdapter.OnItemClickListener{
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
                            (searchPlantAdapter as? SearchPlantAdapter)?.notifyDataSetChanged()// async 작업이 완료 되고 난 후 호출.
                        }
                    }
                })
                Log.i("vp","${binding.Search.text.toString()}${itemList}")
                searchPlantRecycler?.adapter = searchPlantAdapter
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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}