package com.example.maatritva.ui.introslider

import androidx.annotation.DrawableRes
import com.example.maatritva.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {

    data object FirstPage : OnboardingModel(
        image = R.drawable.ma,
        title = "Maatritva",
        description = ""
    )

    data object SecondPage : OnboardingModel(
        image = R.drawable.logo,
        title = "Your Personal Library",
        description = "Organize books in different ways, make your own library"
    )

    data object ThirdPages : OnboardingModel(
        image = R.drawable.logo,
        title = "Search and Filter",
        description = "Get any book you want within a simple search across your device"
    )


}