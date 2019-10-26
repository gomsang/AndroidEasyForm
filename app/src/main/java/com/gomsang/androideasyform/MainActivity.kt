package com.gomsang.androideasyform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gomsang.androideasyform.databinding.ActivityMainBinding
import com.gomsang.androideasyform.library.EasyForm

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.form = BasicRegistForm()
    }
}