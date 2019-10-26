package com.gomsang.androideasyform.library

import android.util.Log
import androidx.databinding.ObservableField

class EasyField<T> : ObservableField<T>() {
    private var linkedForm: EasyForm? = null
    private var onFieldChangeListener: OnFieldChangeListener<T>? = null

    private val validators: MutableList<EasyValidator<T>> = mutableListOf()
    private val validatorMessageMap: MutableMap<EasyValidator<T>, String> = mutableMapOf()

    val errorMessage = ObservableField("")
    var essential: () -> Boolean = { true }

    override fun get(): T? {
        return super.get()
    }

    override fun set(value: T?) {
        super.set(value)
        if (value != null) {
            onFieldChangeListener?.let {
                it.onChanged(this, get())
            }
            linkedForm?.let {
                it.update(this as EasyField<Any>)
            }
        }
    }

    fun attach(validator: EasyValidator<T>, msg: String) {
        validators.add(validator)
        validatorMessageMap[validator] = msg
    }

    fun attach(function: () -> Boolean, msg: String) {
        val validator = object : EasyValidator<T> {
            override fun isValid(value: T): Boolean {
                return function()
            }
        }

        validators.add(validator)
        validatorMessageMap[validator] = msg
    }

    fun setOnFieldChangeListener(listener: OnFieldChangeListener<T>) {
        onFieldChangeListener = listener
        if (get() != null) listener.onChanged(this, get())
    }

    fun setOnFieldChangeListener(function: (EasyField<T>, T?) -> Unit) {
        val listener = object : OnFieldChangeListener<T> {
            override fun onChanged(easyField: EasyField<T>, value: T?) {
                function.invoke(easyField, value)
            }
        }
        onFieldChangeListener = listener
        if (get() != null) listener.onChanged(this, get())
    }

    fun attachForm(form: EasyForm) {
        linkedForm = form
    }

    fun isValid(): Boolean {
        for (validator in validators) {
            if (get() == null || !validator.isValid(get()!!)) {
                errorMessage.set(validatorMessageMap[validator]!!)
                return false
            }
        }
        errorMessage.set("")
        return true
    }

    interface OnFieldChangeListener<T> {
        fun onChanged(easyField: EasyField<T>, value: T?)
    }
}