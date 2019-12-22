# AndroidEasyForm

This library provides you easy form management expereince. Check formed-email, text-length, retyping correct check, and other custom validating available on our library.

## Support Environment

You will change your environment to under written if you weren't same with it.

- Android X migrated project
- Using Kotlin project
- Using Data Binding project

## Caution!

It's just close to a personal suggestion. So, each version update can have major changes. 

Fix the library version as much as possible, and be sure to read the readme file when updating.

## Advantages of this method

- Easy constructed form-validating
- Making highly readable management environment
- Easy to use with LiveData on MVVM structure.

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



In your layout file, just evidence your class file that inherits our EasyForm class.

```xml
<data>    
    <variable name="form"   
              type="com.gomsang.androideasyform.basicregistration.RegistrationForm" />
</data>
```

and set 2-way binding on your own edittext, or other views.

```xml
<EditText
          android:id="@+id/nameEditText"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:hint="Name"
          android:text="@={form.name}" />
```



After set all this, you can set enable setting by calling isValidate(). Just using databinding like this.

```xml
<Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:enabled="@{form.isValidate()}"
        android:text="REGIST" />
```

## Additional Features

### using with LiveData, on MVVM architecture.

```kotlin
class BasicRegistrationViewModel : ViewModel() {
    val formLiveData = MutableLiveData<RegistrationForm>()
}
```

```kotlin
// getting view model.
val viewModel = ViewModelProviders.of(this).get(BasicRegistrationViewModel::class.java)

// setting live data.
viewModel.formLiveData.observe(this, Observer {
    it?.let {
        binding.form = it
    }
})

if (viewModel.formLiveData.value == null) viewModel.formLiveData.value = RegistrationForm()
```

## Automatically shown error message

You can show your error messages dynamically, using Binding Adapter and TextInputLayout on Material Library by Google.

```kotlin
class RegistrationForm : EasyForm() {
    val name = registField(EasyField<String>().apply {
        validate(EasyLab.TextEmptyValidator(), "Input your name, please.")
    })
    val nameError = name.errorMessage
}
```

```kotlin
@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String) {
    view.error = errorMessage
}
```

```xml
<com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Name"
            app:errorText="@{form.nameError}">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@={form.name}" />
     
</com.google.android.material.textfield.TextInputLayout>
```

