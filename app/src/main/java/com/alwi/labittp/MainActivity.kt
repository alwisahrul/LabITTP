package com.alwi.labittp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alwi.labittp.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_rekap, R.id.navigation_about, R.id.navigation_settings
            )
        )

        navView.setupWithNavController(navController)

        val inventaris_button: Button = findViewById(R.id.inventaris_button)
        val pinjam_button: Button = findViewById(R.id.pinjam_button)
        val lapor_button: Button = findViewById(R.id.lapor_button)

        inventaris_button.setOnClickListener{
            val inten1 = Intent(this, InventarisActivity::class.java)
            startActivity(inten1)
        }

        pinjam_button.setOnClickListener{
            val inten2 = Intent(this@MainActivity, PeminjamanActivity::class.java)
            startActivity(inten2)

        }

        lapor_button.setOnClickListener{
            val inten3 = Intent(this@MainActivity, LaporanActivity::class.java)
            startActivity(inten3)
        }

    }

}

