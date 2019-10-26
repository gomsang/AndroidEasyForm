package com.gomsang.androideasyform.library

interface EasyValidator<T> {
    fun isValid(value: T): Boolean
}