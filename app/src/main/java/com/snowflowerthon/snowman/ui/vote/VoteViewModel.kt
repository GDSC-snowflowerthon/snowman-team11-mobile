package com.snowflowerthon.snowman.ui.vote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val variableX = MutableLiveData<Boolean>()
}