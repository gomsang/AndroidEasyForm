package com.gomsang.androideasyform.library.validators

import android.util.Patterns
import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyValidator

/**
 * pre-constructed validator for regular purpose of forms.
 */
class EasyLab {
    class TextEmptyValidator : EasyValidator<String> {
        override fun isValid(value: String): Boolean {
            if (!value.isBlank()) return true
            return false
        }
    }

    class TextLengthValidator(private val min: Int, private val max: Int) : EasyValidator<String> {
        override fun isValid(value: String): Boolean {
            if (value.length in min..max) return true
            return false
        }
    }

    class EmailValidator : EasyValidator<String> {
        override fun isValid(value: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(value).matches()
        }
    }

    class TextSameValidator(
        private val a: EasyField<String>,
        private val b: EasyField<String>
    ) : EasyValidator<String> {
        init {
            a.addOnFieldChangeListener { _, _ ->
                b.updateValidState()
            }
            b.addOnFieldChangeListener { _, _ ->
                a.updateValidState()
            }
        }

        override fun isValid(value: String): Boolean {
            return a.get() == b.get()
        }
    }
}