package com.yeinerdpajaro.nemohachi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.appcompat.app.AppCompatActivity
import com.yeinerdpajaro.nemohachi.ui.signup.data.UserRepository
import com.yeinerdpajaro.nemohachi.databinding.ActivityMainBinding
import com.yeinerdpajaro.nemohachi.ui.language.LanguageActivity
import com.yeinerdpajaro.nemohachi.ui.notifications.NotificationActivity
import com.yeinerdpajaro.nemohachi.ui.profile.ProfileActivity
import com.yeinerdpajaro.nemohachi.ui.signin.SignInActivity


class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var userRepository = UserRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), //drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        /*navView.setupWithNavController(navController)*/

        (R.id.action_signout)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main, menu)


        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

              // Handle item selection
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                true
            }
            R.id.action_language -> {
                startActivity(Intent(this@MainActivity, LanguageActivity::class.java))
                true
            }
            R.id.action_notification -> {
                startActivity(Intent(this@MainActivity, NotificationActivity::class.java))
                true
            }
            R.id.action_help -> {
                //showHelp()
                true
            }
            R.id.action_signout -> {

                userRepository.signOut()
                startActivity(Intent(this@MainActivity, SignInActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}