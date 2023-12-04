package com.example.myapplicationex1

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplicationex1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    var db: PlantsDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PlantsDatabase.getInstance(this)
        CoroutineScope(Dispatchers.IO).launch { // 코루틴 사용 비동기로 실행.
            db!!.plantsDao().saveBookmark(PlantsEntity(1,"Round Cactus","성게 선인장",R.drawable.jaemin_round_cactus,R.drawable.jaemin_desc_round_cactus,R.drawable.jaemin_round_cactus_desc,R.drawable.jaemin_outdoor_category,false))
            db!!.plantsDao().saveBookmark(PlantsEntity(2,"Fishbone Cactus","피쉬본 선인장",R.drawable.jaemin_fishbone_cactus,R.drawable.jaemin_desc_fishbone_cactus,R.drawable.jaemin_fishbone_cactus_desc,R.drawable.jaemin_office_category,false))
            db!!.plantsDao().saveBookmark(PlantsEntity(3,"Christmas Cactus","게발 선인장",R.drawable.jaemin_christmas_cactus,R.drawable.jaemin_desc_christmas_cactus,R.drawable.jaemin_christmas_cactus_desc,R.drawable.jaemin_other_category,false))
            db!!.plantsDao().saveBookmark(PlantsEntity(4,"Tulip","튤립",R.drawable.jaemin_tulip,R.drawable.jaemin_desc_tulip,R.drawable.jaemin_tulip_desc,R.drawable.jaemin_outdoor_category,false))
            db!!.plantsDao().saveBookmark(PlantsEntity(5,"Siam Tulip","샴튤립",R.drawable.jaemin_siam_tulip,R.drawable.jaemin_desc_siam_tulip,R.drawable.jaemin_siam_tulip_desc,R.drawable.jaemin_outdoor_category,false))
            val plantsData = db!!.plantsDao().getAll()
            Log.i("plz", "${plantsData}")
        }

//        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
        setupActionBarWithNavController(navController)
        //navView.setupWithNavController(navController)

        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_menu)
        val menu = popupMenu.menu
        //binding.bottomBar.setupWithNavController(menu, navController)
//        bottombar.setupWithNavController(navController)
        binding.navview.setupWithNavController(menu, navController)

        supportActionBar!!.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                showToast("Another Menu Item 1 Selected")
            }

            R.id.navigation_dashboard -> {
                showToast("Another Menu Item 2 Selected")
            }

            R.id.navigation_notifications -> {
                showToast("Another Menu Item 3 Selected")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    //
//    private fun setupSmoothBottomMenu() {
//        val popupMenu = PopupMenu(this, null)
//        popupMenu.inflate(R.menu.bottom_nav_menu)
//        val menu = popupMenu.menu
//        val bottombar = binding.navview
//        //binding.bottomBar.setupWithNavController(menu, navController)
//        bottombar.setupWithNavController(navController)
//    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}