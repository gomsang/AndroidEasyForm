package com.gomsang.androideasyform.basicregistration

import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm
import com.gomsang.androideasyform.library.validators.EasyLab

class RegistrationForm : EasyForm() {
    val name = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your name, please.")
    })

    val introduction = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your introduction, please.")
    })

    val password = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your password, please.")
    })

    val passwordRepeat = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "It is not the same as the password you entered.")
        validate(
            EasyLab.TextSameValidator(this@RegistrationForm, password, this),
            "It is not the same as the password you entered."
        )
    })
}