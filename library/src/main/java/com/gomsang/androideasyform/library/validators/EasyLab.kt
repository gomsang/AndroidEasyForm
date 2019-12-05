package com.gomsang.androideasyform.library.validators

import android.util.Log
import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm
import com.gomsang.androideasyform.library.EasyValidator

class EasyLab {
    class TextEmptyValidator : EasyValidator<String> {
        override fun isValid(str: String): Boolean {
            if (!str.isBlank()) return true
            return false
        }
    }

    class TextSameValidator(private val form: EasyForm, private val a: EasyField<String>, private val b: EasyField<String>) : EasyValidator<String> {
        init {
            a.addOnFieldChangeListener { _, _ ->
                form.update(b as EasyField<Any>)
            }
            b.addOnFieldChangeListener { _, _ ->
                form.update(b as EasyField<Any>)
            }
        }

        override fun isValid(value: String): Boolean {
            return a.get() == b.get()
        }
    }
}