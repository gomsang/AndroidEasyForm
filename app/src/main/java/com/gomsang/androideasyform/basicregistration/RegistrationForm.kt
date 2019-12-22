package com.gomsang.androideasyform.basicregistration

import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm
import com.gomsang.androideasyform.library.validators.EasyLab

class RegistrationForm : EasyForm() {
    val name = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your name, please.")
    })
    val nameError = name.errorMessage

    val email = registField(EasyField<String>()).apply {
        validate(EasyLab.TextEmptyValidator(), "Input your email, please.")
        validate(EasyLab.EmailValidator(), "Please write a valid email.")
    }
    val emailError = email.errorMessage

    val introduction = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your introduction, please.")
    })
    val introductionError = introduction.errorMessage

    val password = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your password, please.")
        validate(EasyLab.TextLengthValidator(4, 12), "Password must be 4 or more and 12 or less.")
    })
    val passwordError = password.errorMessage

    val passwordRepeat = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "It is not the same as the password you entered.")
        validate(
            EasyLab.TextSameValidator(this@RegistrationForm, password, this),
            "It is not the same as the password you entered."
        )
    })
    val passwordRepeatError = passwordRepeat.errorMessage
}