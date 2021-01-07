package com.gomsang.androideasyform.library

import androidx.databinding.ObservableField

/**
 * Form Body
 */
open class EasyForm {
    private val registeredFields = mutableListOf<EasyField<*>>()
    private val fieldValidStateMap: MutableMap<EasyField<*>, Boolean> = mutableMapOf()

    // Current error message. (one of field)
    val errorMessage: ObservableField<Int> = ObservableField(R.string.empty_string)

    var isValidate: ObservableField<Boolean> = ObservableField(false)

    /**
     * register field(input) to form body
     * @param T Field(input)'s data type
     * @param field Field(input)
     * @return registered field(input)
     */
    fun <T> registField(field: EasyField<T>): EasyField<T> {
        registeredFields.add(field)
        fieldValidStateMap[field] = false
        field.attachForm(this)
        return field
    }


    fun update(easyField: EasyField<*>) {
        fieldValidStateMap[easyField] = easyField.valid
        stateUpdate()

        getErrorMessages().also {
            if (it.isNotEmpty()) {
                errorMessage.set(it[0])
            } else {
                errorMessage.set(R.string.empty_string)
            }
        }
    }

    /**
     * @return all error messages of the fields registered in the form
     */
    private fun getErrorMessages(): List<Int> {
        val messages = mutableListOf<Int>()
        for (field in registeredFields) {
            if (!field.valid && field.errorMessage.get() != null) {
                messages.add(field.errorMessage.get()!!)
            }
        }
        return messages
    }

    /**
     * Check the registered fields and determine whether the entire form is verified
     */
    private fun stateUpdate() {
        for (entry in fieldValidStateMap.entries) {
            if (!entry.value && entry.key.required()) {
                isValidate.set(false)
                return
            }
        }
        isValidate.set(true)
    }
}
