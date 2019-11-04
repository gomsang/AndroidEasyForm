package com.gomsang.androideasyform.basicregistrationmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gomsang.androideasyform.R
import com.gomsang.androideasyform.basicregistration.RegistrationForm
import com.gomsang.androideasyform.databinding.ActivityBasicRegistrationBinding

class BasicRegistrationWithMVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBasicRegistrationBinding>(
            this,
            R.layout.activity_basic_registration
        )
        val viewModel = ViewModelProviders.of(this).get(BasicRegistrationViewModel::class.java)

        viewModel.formLiveData.observe(this, Observer {
            it?.let {
                binding.form = it
            }
        })
        if (viewModel.formLiveData.value == null) viewModel.formLiveData.value = RegistrationForm()
    }
}