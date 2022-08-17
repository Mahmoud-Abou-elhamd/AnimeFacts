package com.example.android.animefacts.data

sealed class NetworkState<T>{
    class Success<T>(val transferredData: T) : NetworkState<T>()
    class Failure<T>(message: String) : NetworkState<T>()
    class Loading<T> : NetworkState<T>()
}
