package com.gomsang.androideasyform.basicregistrationmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gomsang.androideasyform.basicregistration.RegistrationForm

class BasicRegistrationViewModel : ViewModel() {
    val formLiveData = MutableLiveData<RegistrationForm>()
}