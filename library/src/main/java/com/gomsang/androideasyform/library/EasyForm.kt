package com.gomsang.androideasyform.library

import androidx.databinding.ObservableField

open class EasyForm {
    private val registeredFields = mutableListOf<EasyField<Any>>()
    private val fieldValidateMap: MutableMap<EasyField<Any>, Boolean> = mutableMapOf()
    var isValidate: ObservableField<Boolean> = ObservableField(false)

    fun <T> registField(field: EasyField<T>): EasyField<T> {
        registeredFields.add(field as EasyField<Any>)
        fieldValidateMap[field] = false
        field.attachForm(this)
        return field
    }

    fun update(easyField: EasyField<Any>) {
        fieldValidateMap[easyField] = easyField.isValid()
        stateUpdate()
    }

    fun getErrorMessages(): List<String> {
        val messages = mutableListOf<String>()
        for (field in registeredFields) {
            if (!field.errorMessage.get().isNullOrEmpty()) {
                messages.add(field.errorMessage.get()!!)
            }
        }
        return messages
    }

    private fun stateUpdate() {
        for (entry in fieldValidateMap.entries) {
            if (!entry.value && entry.key.essential()) {
                isValidate.set(false)
                return
            }
        }
        isValidate.set(true)
    }
}
