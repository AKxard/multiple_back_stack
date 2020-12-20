package com.akxard.multiplebackstack.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import com.akxard.multiplebackstack.ui.BaseFragment
import com.akxard.multiplebackstack.ui.catalog.CatalogFragment
import com.akxard.multiplebackstack.ui.detail.DetailFragment
import com.akxard.multiplebackstack.ui.home.HomeFragment
import com.akxard.multiplebackstack.ui.live.LiveFragment
import com.akxard.multiplebackstack.ui.profile.ProfileFragment
import com.akxard.multiplebackstack.ui.schedule.ScheduleFragment
import com.akxard.multiplebackstack.ui.stats.StatsFragment

object nav_graph {
    object dest {
        const val home = 2
        const val live = 3
        const val stats = 4
        const val schedule = 5
        const val profile = 6
        const val catalog = 7
        const val detail = 8
    }
}

fun NavController.createNavGraphByMenu(listMenu: List<NavMenu>): List<NavGraph> {
    val bottomNavGraph = mutableListOf<NavGraph>()
    for (menu in listMenu) {
        bottomNavGraph.add(
            createGraph(
                menu.referenceId.toNavGraphId(),
                menu.referenceType.toStartDestinationId()
            ) {
                fragment<HomeFragment>(nav_graph.dest.home) {
                    label = "HomeFragment"
                    argument(BaseFragment.ARG_REFERENCE_ID) {
                        defaultValue = "homeId"
                        type = NavType.StringType
                    }
                    argument(BaseFragment.ARG_REFERENCE_TYPE) {
                        defaultValue = "homepage"
                        type = NavType.StringType
                    }
                }
                fragment<LiveFragment>(nav_graph.dest.live) {
                    label = "LiveFragment"
                    argument(BaseFragment.ARG_REFERENCE_ID) {
                        defaultValue = "liveId"
                        type = NavType.StringType
                    }
                    argument(BaseFragment.ARG_REFERENCE_TYPE) {
                        defaultValue = "live"
                        type = NavType.StringType
                    }
                }
                fragment<StatsFragment>(nav_graph.dest.stats) {
                    label = "StatsFragment"
                    argument(BaseFragment.ARG_REFERENCE_ID) {
                        defaultValue = "statsId"
                        type = NavType.StringType
                    }
                    argument(BaseFragment.ARG_REFERENCE_TYPE) {
                        defaultValue = "stats"
                        type = NavType.StringType
                    }
                }
                fragment<ScheduleFragment>(nav_graph.dest.schedule) {
                    label = "ScheduleFragment"
                    argument(BaseFragment.ARG_REFERENCE_ID) {
                        defaultValue = "scheduleId"
                        type = NavType.StringType
                    }
                    argument(BaseFragment.ARG_REFERENCE_TYPE) {
                        defaultValue = "schedule"
                        type = NavType.StringType
                    }
                }
                fragment<ProfileFragment>(nav_graph.dest.profile) {
                    label = "ProfileFragment"
                    argument(BaseFragment.ARG_REFERENCE_ID) {
                        defaultValue = "profileId"
                        type = NavType.StringType
                    }
                    argument(BaseFragment.ARG_REFERENCE_TYPE) {
                        defaultValue = "profile"
                        type = NavType.StringType
                    }
                }
                fragment<CatalogFragment>(nav_graph.dest.catalog) {
                    label = "CatalogFragment"
                }
                fragment<DetailFragment>(nav_graph.dest.detail) {
                    label = "HomeFragmentThree"
                }
            }
        )
    }
    return bottomNavGraph
}

fun String.toStartDestinationId(): Int {
    return when (this) {
        "homepage" -> nav_graph.dest.home
        "live" -> nav_graph.dest.live
        "stats" -> nav_graph.dest.stats
        "schedule" -> nav_graph.dest.home
        "profile" -> nav_graph.dest.home
        else -> nav_graph.dest.home
    }
}

fun String.toNavGraphId(): Int {
    return this.take(3).toCharArray().map { it.toInt().toString() }
        .reduce { finalString, element -> finalString + element }.toInt()
}

