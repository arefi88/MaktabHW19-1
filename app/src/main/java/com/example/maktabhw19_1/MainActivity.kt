package com.example.maktabhw19_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maktabhw19_1.databinding.ActivityMainBinding
import com.example.maktabhw19_1.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (NetworkConnection.connected(this)){
            Toast.makeText(this,"net is connected", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"net is not connected", Toast.LENGTH_SHORT).show()
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navHostFragment.findNavController()
        appBarConfiguration=AppBarConfiguration(
            setOf(R.id.popularMoviesFragment,R.id.upcomingMoviesFragment)
        )
        setupActionBarWithNavController(
            navController,appBarConfiguration
        )
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}