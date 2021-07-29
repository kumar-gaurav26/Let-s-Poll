package com.example.letspoll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawerLayout=findViewById(R.id.drawerLayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frame)
        navigationView=findViewById(R.id.navigationView)
        setUpToolbar()

        var actionBarDrawerToggle=ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open_drawer,R.string.close_drawer)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()



        navigationView.setNavigationItemSelectedListener {
           when(it.itemId){
               R.id.profile->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.frame,ProfileFragment())
                       .addToBackStack("Profile")
                       .commit()

                   drawerLayout.closeDrawers()

                   
               }
               R.id.setting->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.frame,SettingFragment())
                       .addToBackStack("Setting")
                       .commit()

                   drawerLayout.closeDrawers()
               }
               R.id.feedback->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.frame,FeedbackFragment())
                       .addToBackStack("Feedback")
                       .commit()

                   drawerLayout.closeDrawers()
               }
               R.id.logout->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.frame,LogoutFragment())
                       .addToBackStack("Logout")
                       .commit()

                   drawerLayout.closeDrawers()
               }
               R.id.aboutapp->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.frame,AboutappFragment())
                       .addToBackStack("Aboutapp")
                       .commit()

                   drawerLayout.closeDrawers()
               }
           }

            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar()
    {
        setSupportActionBar(toolbar)
        supportActionBar?.title="LETSPOLL"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId

        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

}