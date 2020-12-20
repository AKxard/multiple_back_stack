package com.akxard.multiplebackstack.ui.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.akxard.multiplebackstack.ui.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupNavController(
    activity: ComponentActivity,
    navController: NavController,
    bottomNavGraph: List<NavGraph>
) {
    data class BackStackEntry(
        val graphId: Int,
        val destinationId: Int,
        val bundle: Bundle? = null,
    )

    val multipleBackStack = mutableSetOf<BackStackEntry>()

    navController.graph = bottomNavGraph.first()

    navController.addOnDestinationChangedListener { _, destination, arguments ->
        val addInBackStack =
            multipleBackStack.firstOrNull { backStackEntry ->
                backStackEntry.graphId == navController.graph.id &&
                        backStackEntry.destinationId == destination.id
            }?.let {
                false
            } ?: true

        if (addInBackStack) {
            val argsId = arguments?.get(BaseFragment.ARG_REFERENCE_ID).toString()
            val argsType = arguments?.get(BaseFragment.ARG_REFERENCE_TYPE).toString()
            multipleBackStack.add(
                BackStackEntry(
                    graphId = navController.graph.id,
                    destinationId = destination.id,
                    bundle = createBundle(argsId, argsType)
                )
            )
        }
    }

    setOnNavigationItemSelectedListener { item ->
        navController.graph = bottomNavGraph.first { it.id == item.itemId }
        multipleBackStack.lastOrNull {
            it.graphId == navController.graph.id && it.destinationId !=
                    navController.graph.startDestination
        }
            ?.let { backStackEntry ->
                navController.navigate(
                    backStackEntry.destinationId, backStackEntry.bundle
                )
            }
        true
    }

    activity.onBackPressedDispatcher.addCallback(activity, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            multipleBackStack.lastOrNull()?.let { entry ->
                multipleBackStack.remove(entry)
                multipleBackStack.lastOrNull()?.let {
                    if (navController.graph.id == it.graphId) {
                        navController.navigate(
                            it.destinationId, it.bundle
                        )
                    } else {
                        selectedItemId = it.graphId
                    }
                } ?: run {
                    remove()
                    activity.onBackPressed()
                }
            }

        }
    })

}

fun createBundle(argsId: String, argsType: String): Bundle {
    return bundleOf(
        BaseFragment.ARG_REFERENCE_ID to argsId,
        BaseFragment.ARG_REFERENCE_TYPE to argsType
    )
}