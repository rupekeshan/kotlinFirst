package com.example.firstkotlin

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.databinding.ActivityMainBinding
import com.example.firstkotlin.ui.AddTodo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarInclude.toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Notes"
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//        toolBar = toolbar_include as Toolbar
//        toolBar.setupWithNavController(navController)
//        setupActionBarWithNavController(navController)

        binding.addFAB.setOnClickListener {
//            Navigation.findNavController(this,R.id.navHost_fragment).navigate(R.id.add_todo)

            val dialog: AddTodo = AddTodo()
            dialog.show(supportFragmentManager, "show")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        Log.e(TAG, "onBackPressed: ${supportFragmentManager.backStackEntryCount}")
        super.onBackPressed()
    }


}