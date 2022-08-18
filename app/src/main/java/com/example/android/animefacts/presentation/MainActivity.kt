package com.example.android.animefacts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.android.animefacts.databinding.ActivityMainBinding
import com.example.android.animefacts.presentation.base.BaseActivity
import com.example.android.animefacts.presentation.fragment.HomeFragment
import com.example.android.animefacts.utilities.extention.navigateTo

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val inflate: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate
    override fun setup() {
        navigateTo(HomeFragment())
    }
}