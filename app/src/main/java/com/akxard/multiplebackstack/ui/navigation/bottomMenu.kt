package com.akxard.multiplebackstack.ui.navigation

import androidx.annotation.DrawableRes
import com.akxard.multiplebackstack.R

data class NavMenu(
    val title: String,
    val referenceId: String,
    val referenceType: String,
    @DrawableRes val icon: Int,
)

val listNavMenu = listOf(
    NavMenu(
        "Home",
        "Home1234",
        "homepage",
        R.drawable.ic_home
    ),
    NavMenu(
        "Live",
        "Live",
        "live",
        R.drawable.ic_live
    ),
    NavMenu(
        "Stats",
        "Stats",
        "stats",
        R.drawable.ic_stats
    ),
    NavMenu(
        "Schedule",
        "Sf4",
        "schedule",
        R.drawable.ic_schedule
    ),
    NavMenu(
        "Profile",
        "H3454",
        "profile",
        R.drawable.ic_profile
    ),
)