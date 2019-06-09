package com.example.mydiagnosis.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mydiagnosis.R
import com.example.mydiagnosis.fragment.*
import com.example.mydiagnosis.usecase.DiagnoseUseCase
import com.example.mydiagnosis.viewmodel.UserViewModel
import com.facebook.stetho.Stetho
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MyDiagnosisActivity : AppCompatActivity() {

    private var mDrawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private lateinit var userViewModel : UserViewModel

    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symtoms)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerToggle = setupDrawerToggle()

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer?.addDrawerListener(drawerToggle)

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout)

        val nvDrawer = findViewById<NavigationView>(R.id.nvView)

        // Setup drawer view
        setupDrawerContent(nvDrawer)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        Stetho.initializeWithDefaults(this)

        linearLayout = findViewById(R.id.homeScreen)
        linearLayout.visibility = View.VISIBLE

        // UI elements here
        val userNameInput = findViewById<EditText>(R.id.userName)

        val userAgeInput = findViewById<EditText>(R.id.userAgeInput)

        val userGenderInput = findViewById<EditText>(R.id.userGenderInput)

        val userNameTextView = findViewById<TextView>(R.id.userNameTextView)

        val submitButton = findViewById<Button>(R.id.submitUserInfoBtn)


        if(userViewModel.getUserName(this) == "") {

            userNameTextView.visibility = View.GONE

            submitButton.setOnClickListener {

                val userName = userNameInput.editableText.toString()

                val userAge = userAgeInput.editableText.toString().toInt()

                val userGender = userGenderInput.editableText.toString()

                userViewModel.saveUserInfo(this, userName, userAge, userGender)

                submitButton.visibility = View.GONE
                userNameInput.visibility = View.GONE
                userAgeInput.visibility = View.GONE
                userGenderInput.visibility = View.GONE

                userNameTextView.visibility = View.VISIBLE
                userNameTextView.text = "Welcome ${userViewModel.getUserName(this)}"

            }


        } else {
            userNameTextView.visibility = View.VISIBLE
            userNameTextView.text = "Welcome ${userViewModel.getUserName(this)}"

            userNameInput.visibility = View.GONE
            userAgeInput.visibility = View.GONE
            userGenderInput.visibility = View.GONE
            submitButton.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        if (setupDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    fun selectDrawerItem(menuItem: MenuItem) {

        linearLayout.visibility = View.GONE
        // Create a new fragment and specify the fragment to show based on nav item clicked
        var fragment: Fragment? = null
        val fragmentClass: Class<*>
        when (menuItem.itemId) {
            R.id.nav_first_fragment -> fragmentClass = FirstFragment::class.java
            R.id.nav_second_fragment -> fragmentClass = SecondFragment::class.java
            R.id.nav_third_fragment -> fragmentClass = ThirdFragment::class.java
            R.id.nav_mentions -> fragmentClass = NlpFragment::class.java
            R.id.nav_diagnosis -> fragmentClass = DiagnosisFragment::class.java
            else -> fragmentClass = FirstFragment::class.java
        }

        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the fragment by replacing any existing fragment
        val fragmentManager = supportFragmentManager

        if(fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.isChecked = true
        // Set action bar title
        title = menuItem.title
        // Close the navigation drawer
        mDrawer?.closeDrawers()
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        setupDrawerToggle().syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        setupDrawerToggle().onConfigurationChanged(newConfig)
    }
}
