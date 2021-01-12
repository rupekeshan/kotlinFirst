package com.example.firstkotlin

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.firstkotlin.ui.Add_todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var navController: NavController
    private lateinit var toolBar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_include as Toolbar?)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Notes"
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//        toolBar = toolbar_include as Toolbar
//        toolBar.setupWithNavController(navController)
//        setupActionBarWithNavController(navController)

        addFAB.setOnClickListener {
//            Navigation.findNavController(this,R.id.navHost_fragment).navigate(R.id.add_todo)

            val dialog: Add_todo = Add_todo()
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.add_todo -> {
////                Navigation.findNavController(this,R.id.navHost_fragment).navigate(R.id.add_todo)
//            }
//        }
        return super.onOptionsItemSelected(item)
    }


}