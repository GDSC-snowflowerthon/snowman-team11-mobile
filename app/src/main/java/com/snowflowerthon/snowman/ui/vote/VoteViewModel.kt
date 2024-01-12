package com.snowflowerthon.snowman.ui.vote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snowflowerthon.snowman.data.enums.Clothes

class VoteViewModel : ViewModel() {

    val selectedOuterwear = MutableLiveData<Clothes>()

    val selectedInnerwear = MutableLiveData<Clothes>()

    val selectedItem = MutableLiveData<Clothes>()

    val selectedMuffler = MutableLiveData<Clothes>()

}