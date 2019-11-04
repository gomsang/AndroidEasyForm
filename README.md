# AndroidEasyForm

## Caution!

It's just close to a personal suggestion. So, each version update can have major changes. 

Fix the library version as much as possible, and be sure to read the readme file when updating.

## Support Environment

You will change your environment to under written if you weren't same with it.

- Android X migrated project
- using DataBinding project

## Advantages of this method

- Easy constructed form-validating
- Making highly readable management environment
- Easy to use with LiveData

## How to Use?

First, Create a class that inherits the EasyForm class. 

```kotlin
class RegistrationForm : EasyForm() {
}
```

Add the input fields like this.

```kotlin
class RegistrationForm : EasyForm() {
	 val name = registField(EasyField<String>())
}
```

This is just adding an empty field. Now add the Validator you want to use.

```kotlin
class RegistrationForm : EasyForm() {
	val name = registField(EasyField<String>().apply {
        validate(BasicTextValidator.EmptyValidator(), "Input your name, please.")
    })
}
```