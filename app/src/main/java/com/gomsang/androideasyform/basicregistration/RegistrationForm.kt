package com.gomsang.androideasyform.basicregistration

import com.gomsang.androideasyform.library.BasicTextValidator
import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm

class RegistrationForm : EasyForm() {
    val name = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "")
    })

    val introduction = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "")

    })

    val password = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "")
    })

    val passwordRepeat = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "")
        validate({
            password.get() == it
        }, "")
    })

}