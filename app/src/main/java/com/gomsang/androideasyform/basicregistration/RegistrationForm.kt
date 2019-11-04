package com.gomsang.androideasyform.basicregistration

import com.gomsang.androideasyform.library.BasicTextValidator
import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm

class RegistrationForm : EasyForm() {
    val name = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "Input your name, please.")
    })

    val introduction = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "Input your introduction, please.")
    })

    val password = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "Input your password, please.")
    })

    val passwordRepeat = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "It is not the same as the password you entered.")
        validate({
            password.get() == it
        }, "")
    })
}