package com.gomsang.androideasyform.basicregistrationwitherrorhandle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gomsang.androideasyform.R
import com.gomsang.androideasyform.basicregistration.RegistrationForm
import com.gomsang.androideasyform.basicregistrationmvvm.BasicRegistrationViewModel
import com.gomsang.androideasyform.databinding.ActivityBasicRegistrationWithErrorHandleBinding

class BasicRegistrationWithErrorHandle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityBasicRegistrationWithErrorHandleBinding>(
                this,
                R.layout.activity_basic_registration_with_error_handle
            )

        // getting view model.
        val viewModel = ViewModelProviders.of(this).get(BasicRegistrationViewModel::class.java)

        // setting live data.
        viewModel.formLiveData.observe(this, androidx.lifecycle.Observer {
            it?.let {
                binding.form = it
            }
        })
        if (viewModel.formLiveData.value == null) viewModel.formLiveData.value = RegistrationForm()
    }
}
