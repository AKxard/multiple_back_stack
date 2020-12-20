package com.akxard.multiplebackstack

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.akxard.multiplebackstack.databinding.MainActivityBinding
import com.akxard.multiplebackstack.ui.navigation.createNavGraphByMenu
import com.akxard.multiplebackstack.ui.navigation.listNavMenu
import com.akxard.multiplebackstack.ui.navigation.setupNavController
import com.akxard.multiplebackstack.ui.navigation.toNavGraphId

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var _binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        listNavMenu.forEachIndexed { index, navMenu ->
            _binding.bottomNavView.menu.add(
                Menu.NONE,
                navMenu.referenceId.toNavGraphId(),
                index,
                navMenu.title
            ).setIcon(navMenu.icon)
        }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val bottomNavGraph = navHostFragment.navController.createNavGraphByMenu(listNavMenu)

        _binding.bottomNavView.setupNavController(
            this,
            navHostFragment.navController,
            bottomNavGraph
        )
    }
}