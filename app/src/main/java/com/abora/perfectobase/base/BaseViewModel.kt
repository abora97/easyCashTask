package com.abora.perfectobase.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abora.perfectobase.data.remote.networkHandling.NetworkStatus


open class BaseViewModel : ViewModel() {
    lateinit var networkStatus: NetworkStatus
    val loading = MutableLiveData<Boolean>()
    val showMassage = MutableLiveData<String>()

}