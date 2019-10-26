package com.gomsang.androideasyform

import com.gomsang.androideasyform.library.BasicTextValidator
import com.gomsang.androideasyform.library.EasyField
import com.gomsang.androideasyform.library.EasyForm

class BasicRegistForm : EasyForm() {
    val name = registField(EasyField<String>()).apply {
        attach(BasicTextValidator.EmptyValidator(), "Please input your name.")
    }
    val nameError = name.errorMessage

    val desc = registField(EasyField<String>()).apply {
        attach(BasicTextValidator.EmptyValidator(), "Please input your description.")
        essential = {
            name.get() != "alpha"
        }
    }
    val descError = name.errorMessage

    val age = registField(EasyField<String>()).apply {
        attach(BasicTextValidator.EmptyValidator(), "Please input your age.")
        attach({ get()?.toInt()!! > 18 }, "We are not allow age under 18")
    }
}
