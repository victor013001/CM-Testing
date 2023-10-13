package co.edu.udea.compumovil.gr03_20232.lab1.ui

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.api.CitiesResponse
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.AddressTextField
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.CityAutoComplete
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.CountryAutoComplete
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.EmailTextField
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.NextButtonCenterEnd
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.PhoneTextField
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.Labs20232Gr03Theme

var phone by mutableStateOf(TextFieldValue(""))
var email by mutableStateOf(TextFieldValue(""))
var address by mutableStateOf(TextFieldValue(""))
var country = mutableStateOf("")
val countries = mutableSetOf<String>()

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ContactDataForm(
    nextScreenFunction: () -> Unit = {}
) {
    val context = LocalContext.current
    ContactDataFormLandscape(context, nextScreenFunction)
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ContactDataFormLandscape(
    context: Context,
    nextScreenFunction: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(0.dp))
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PhoneTextField(context, phone) {
                    newPhone -> phone = newPhone
            }
            EmailTextField(context, email) {
                    newEmail -> email = newEmail
            }
            CountryAutoComplete(
                context = context,
                country = country,
                countries = countries
            )
            AddressTextField(context,address) {
                    newAddress -> address = newAddress
            }
            NextButtonCenterEnd(
                context = context,
                onClickFunction = {
                    contactDataFormNextButtonOnClick(context, nextScreenFunction)
                }
            )
        }
    }
}

fun contactDataFormNextButtonOnClick(
    context: Context,
    nextScreenFunction: () -> Unit = {}
) {
    if (isContactDataValid(context)) {
        logcatAllContactData()
        val toastMessage = context.getString(R.string.contact_data_form_finished_toast_message)
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        nextScreenFunction()
    }
}

fun logcatAllContactData() {
    val logAllDataMessage = StringBuilder()
    logAllDataMessage.append("Información de contacto: \n")
    logAllDataMessage.append("Teléfono: ${phone.text} \n")
    logAllDataMessage.append("Email: ${email.text} \n")
    logAllDataMessage.append("País: ${country.value} \n")
    if (address.text.isNotEmpty()) {
        logAllDataMessage.append("Dirección: ${address.text} \n")
    }
    Log.i("PersonalDataActivity", logAllDataMessage.toString())
}

fun isContactDataValid(context: Context): Boolean {
    val toastMessage = StringBuilder()
    if (!phoneIsValid()) {
        toastMessage.append(context.getString(R.string.phone_invalid))
        toastMessage.append(". ")
    }
    if (!emailIsValid()) {
        toastMessage.append(context.getString(R.string.email_invalid))
        toastMessage.append(". ")
    }

    if (!countryIsValid()) {
        toastMessage.append(context.getString(R.string.country_invalid))
        toastMessage.append(". ")
    }

    if (toastMessage.isNotEmpty()) {
        Toast.makeText(context, toastMessage.toString(), Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

fun countryIsValid(): Boolean {
    if (country.value.isNotEmpty()) {
        if (countries.contains(country.value)) {
            return true
        }
        return false
    }
    return false
}

fun emailIsValid(): Boolean {
    if (email.text.isNotEmpty()) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()
    }
    return false
}

fun phoneIsValid(): Boolean{
    if (phone.text.isNotEmpty()) {
        return phone.text.length == 10
    }
    return false
}

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun ContactDataActivityPreview() {
    Labs20232Gr03Theme {
        val context = LocalContext.current
        ContactDataFormLandscape(context)
    }
}

