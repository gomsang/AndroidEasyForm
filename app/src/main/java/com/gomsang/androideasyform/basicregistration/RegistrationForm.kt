package com.gomsang.androideasyform.basicregistration

import com.gomsang.androideasyform.R
import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm
import com.gomsang.androideasyform.library.validators.EasyLab

class RegistrationForm : EasyForm() {
    val name = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), R.string.message_warn_registration_name_empty)
    })
    val nameError = name.errorMessage

    val email = registField(EasyField<String>()).apply {
        validate(EasyLab.TextEmptyValidator(), R.string.message_warn_registration_email_empty)
        validate(EasyLab.EmailValidator(), R.string.message_warn_registration_email_email)
    }
    val emailError = email.errorMessage

    val introduction = registField(EasyField<String>().apply {
        validate(
            EasyLab.TextEmptyValidator(),
            R.string.message_warn_registration_introduction_empty
        )
    })
    val introductionError = introduction.errorMessage

    val password = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), R.string.message_warn_registration_password_empty)
        validate(
            EasyLab.TextLengthValidator(4, 12),
            R.string.message_warn_registration_password_length
        )
    })
    val passwordError = password.errorMessage

    val passwordRepeat = registField(EasyField<String>().apply {
        validate(
            EasyLab.TextEmptyValidator(),
            R.string.message_warn_registration_password_check_empty
        )
        validate(
            EasyLab.TextSameValidator(password, this),
            R.string.message_warn_registration_password_check_not_same
        )
    })
    val passwordRepeatError = passwordRepeat.errorMessage
}