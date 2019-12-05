package com.gomsang.androideasyform.library

import androidx.databinding.ObservableField

/**
 * Using for validate each field.
 */
class EasyField<T> : ObservableField<T>() {
    private var linkedForm: EasyForm? = null
    private var listenerList = mutableListOf<OnValueChangedListener<T>>()

    private val validators: MutableList<EasyValidator<T>> = mutableListOf()
    private val validatorMessageMap: MutableMap<EasyValidator<T>, String> = mutableMapOf()

    val errorMessage = ObservableField("")
    var essential: () -> Boolean = { true }


    override fun set(value: T?) {
        super.set(value)
        if (value != null) {
            listenerList.forEach {
                it.onChanged(this, value)
            }
            linkedForm?.let {
                it.update(this as EasyField<Any>)
            }
        }
    }

    fun validate(validator: EasyValidator<T>, msg: String) {
        validators.add(validator)
        validatorMessageMap[validator] = msg
    }

    fun validate(function: (T) -> Boolean, msg: String) {
        val validator = object : EasyValidator<T> {
            override fun isValid(value: T): Boolean {
                return function(value)
            }
        }

        validators.add(validator)
        validatorMessageMap[validator] = msg
    }

    fun addOnFieldChangeListener(listener: OnValueChangedListener<T>) {
        if (!listenerList.contains(listener)) {
            listenerList.add(listener)
            if (get() != null) listener.onChanged(this, get())
        }
    }

    fun addOnFieldChangeListener(function: (EasyField<T>, T?) -> Unit) {
        val listener = object : OnValueChangedListener<T> {
            override fun onChanged(easyField: EasyField<T>, value: T?) {
                function.invoke(easyField, value)
            }
        }
        if (!listenerList.contains(listener)) {
            listenerList.add(listener)
            if (get() != null) listener.onChanged(this, get())
        }
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

    interface OnValueChangedListener<T> {
        fun onChanged(easyField: EasyField<T>, value: T?)
    }
}