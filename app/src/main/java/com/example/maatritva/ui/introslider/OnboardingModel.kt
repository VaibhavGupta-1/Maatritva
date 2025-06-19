package com.example.maatritva.ui.introslider

import androidx.annotation.DrawableRes
import com.example.maatritva.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,

) {

    data object FirstPage : OnboardingModel(
        image = R.drawable.ma,
        title = "Maatritva"
    )

    data object SecondPage : OnboardingModel(
        image = R.drawable.mom,
        title = "Every mother deserves the best care"
    )

    data object ThirdPages : OnboardingModel(
        image = R.drawable.doc,
        title = "Nutrition's certified by doctors"
    )


}