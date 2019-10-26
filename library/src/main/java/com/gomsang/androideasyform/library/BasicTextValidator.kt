package com.gomsang.androideasyform.library

class BasicTextValidator {
    class EmptyValidator : EasyValidator<String> {
        override fun isValid(str: String): Boolean {
            if (!str.isNullOrBlank()) return true
            return false
        }
    }
}