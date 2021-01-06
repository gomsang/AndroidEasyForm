package com.gomsang.androideasyform

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object ErrorBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: Int?) {
        if (errorMessage == null){
            view.error = null
        }else{
            view.error = view.context.getString(errorMessage)
        }
    }
}