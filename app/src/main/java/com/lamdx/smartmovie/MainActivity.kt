package com.lamdx.smartmovie

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.lamdx.smartmovie.databinding.ActivityMainBinding
import com.lamdx.smartmovie.ui.base.BaseListFragment
import com.lamdx.smartmovie.utils.Constant
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseListFragment.Callback {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<TextView>(R.id.toolbar_title)?.text = "Smart Movie"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.cus_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        loadNavHostFragment()

        val application = (application as SmartMovieApplication)
        val config = application.appConfiguration
        val preferenceFileKey = application.preferenceFileKey
        val sharedPreferences = getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE)
        if (config == null) {
            initConfiguration(sharedPreferences)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_view_mode -> {
                val posterMode = ContextCompat.getDrawable(this, R.drawable.sharp_dataset_24)
                val detailsMode = ContextCompat.getDrawable(this, R.drawable.view_content_24)

                item.icon = if (item.title == "Icon Mode") {
                    item.title = "Detail Mode"
                    detailsMode
                } else {
                    item.title = "Icon Mode"
                    posterMode
                }
                mainViewModel.targetViewModel.value = item.title.toString()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initConfiguration(sharedPreferences: SharedPreferences) {
        mainViewModel.configuration.observe(this) {
            val appConfig = Gson().toJson(it)
            sharedPreferences.edit().putString(Constant.APP_CONFIG, appConfig).apply()
        }
    }

    private fun loadNavHostFragment() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_discover,
                R.id.navigation_search,
                R.id.navigation_genres,
                R.id.navigation_artists
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            navController.navigate(it.itemId)
            true
        }
    }

    override fun onShowMovieDetail(movieId: Int) {
        navController.navigate(R.id.navigation_details, bundleOf("movieId" to movieId))
    }

}