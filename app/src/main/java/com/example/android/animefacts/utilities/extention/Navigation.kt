package com.example.android.animefacts.utilities.extention

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.android.animefacts.R
import com.example.android.animefacts.utilities.enums.NavigationType

fun FragmentActivity.navigateTo(to: Fragment, bundle: Bundle? = null){
    to.arguments = bundle
    changeNavigation(this, NavigationType.Add, to)
}

fun FragmentActivity.navigateAndReplaceTo(to: Fragment, bundle: Bundle? = null){
    to.arguments = bundle
    changeNavigation(this, NavigationType.Replace, to)
}

fun FragmentActivity.back(to: Fragment){
    changeNavigation(this, NavigationType.Remove, to)
}

private fun changeNavigation(activity: FragmentActivity, state: NavigationType, to: Fragment){
    val transaction = activity.supportFragmentManager.beginTransaction()
    when(state){
        NavigationType.Add -> transaction.add(R.id.fragment_container, to)
        NavigationType.Remove -> transaction.remove(to)
        NavigationType.Replace -> transaction.replace(R.id.fragment_container, to)
    }
    transaction.addToBackStack(null).commit()
}

