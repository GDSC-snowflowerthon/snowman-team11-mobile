package com.snowflowerthon.snowman.ui.vote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snowflowerthon.snowman.data.enums.Clothes

class VoteViewModel : ViewModel() {
    val balaclava = MutableLiveData<Boolean>()
    val coat = MutableLiveData<Boolean>()
    val ear = MutableLiveData<Boolean>()
    val hand = MutableLiveData<Boolean>()
    val longPadding = MutableLiveData<Boolean>()
    val longShirt = MutableLiveData<Boolean>()
    val muffler = MutableLiveData<Boolean>()
    val neat = MutableLiveData<Boolean>()
    val shortPadding = MutableLiveData<Boolean>()


    val selectedOuterwear = MutableLiveData<Clothes>()

}