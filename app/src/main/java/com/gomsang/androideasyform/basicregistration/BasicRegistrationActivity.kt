package com.gomsang.androideasyform.basicregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gomsang.androideasyform.R
import com.gomsang.androideasyform.databinding.ActivityBasicRegistrationBinding

class BasicRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBasicRegistrationBinding>(this,
            R.layout.activity_basic_registration
        )
        binding.form = RegistrationForm()
    }
}