package com.gomsang.androideasyform.library

import androidx.databinding.ObservableField

/**
 * Using for validate each field.
 */
class EasyField<T> : ObservableField<T>() {
    private var linkedForm: EasyForm? = null
    private var listenerList = mutableListOf<OnValueChangedListener<T>>()

    private val validators: MutableList<EasyValidator<T>> = mutableListOf()
    private val validatorMessageMap: MutableMap<EasyValidator<T>, Int> = mutableMapOf()

    var valid = false

    val errorMessage = ObservableField<Int>(R.string.empty_string)
    private var errorValidator: EasyValidator<*>? = null

    var required: () -> Boolean = { true }

    override fun set(value: T?) {
        super.set(value)
        if (value != null) {
            // call all changed listener registered
            listenerList.forEach {
                it.onChanged(this, value)
            }
            updateValidState()
        }
    }

    fun updateValidState() {
        checkValid().also {
            if (valid != it.first || errorValidator != it.second) {
                valid = it.first
                errorValidator = it.second

                errorMessage.set(if (it.second == null) R.string.empty_string else validatorMessageMap[it.second])
                linkedForm?.update(this)
            }
        }
    }

    fun attachForm(form: EasyForm) {
        linkedForm = form
    }

    fun validate(validator: EasyValidator<T>, msg: Int = R.string.empty_string) {
        validators.add(validator)
        validatorMessageMap[validator] = msg
    }

    fun validate(function: (T) -> Boolean, msg: Int = R.string.empty_string) {
        val validator = object : EasyValidator<T> {
            override fun isValid(value: T): Boolean {
                return function(value)
            }
        }

        validators.add(validator)
        validatorMessageMap[validator] = msg
    }

    private fun checkValid(): Pair<Boolean, EasyValidator<*>?> {
        for (validator in validators) {
            if (get() == null || !validator.isValid(get()!!)) {
                return Pair(false, validator)
            }
        }
        return Pair(true, null)
    }

    interface OnValueChangedListener<T> {
        fun onChanged(easyField: EasyField<T>, value: T?)
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
}