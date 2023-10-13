package co.edu.udea.compumovil.gr03_20232.lab1.ui

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.FullNameText
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.SurnameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.NameTextField
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.NextButtonCenterEnd
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.SchoolGradeSpinner
import co.edu.udea.compumovil.gr03_20232.lab1.ui.composable.SexRadioButtons
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.Labs20232Gr03Theme
import kotlin.text.StringBuilder

var name by mutableStateOf(TextFieldValue(""))
var surname by mutableStateOf(TextFieldValue(""))
var selectedSexOption = mutableStateOf("")
var selectedSchoolGradeOption by mutableStateOf("")


@Composable
fun PersonalDataForm(
    nextScreenFunction: () -> Unit = {}
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            PersonalDataFormPortrait(context, nextScreenFunction)
        }
        else -> {
            PersonalDataFormLandscape(context, nextScreenFunction)
        }
    }

}

@Composable
fun PersonalDataFormLandscape(
    context: Context,
    nextScreenFunction: () -> Unit = {}
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {
        Spacer(modifier = Modifier.height(0.dp))
        FullNameText(context)
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            NameTextField(context, 0.5F, name) {
                    newName -> name = newName
            }
            SurnameTextField(context, 1F, surname) {
                    newSurname -> surname = newSurname
            }
        }
        SexRadioButtons(context, selectedSexOption)
        SchoolGradeSpinner(context, selectedSchoolGradeOption) {
                newSelectedSchoolGradeOption -> selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }
        NextButtonCenterEnd(
            context = context,
            onClickFunction = {
                personalDataFormNextButtonOnClick(context, nextScreenFunction)
            }
        )
    }
}

@Composable
fun PersonalDataFormPortrait(
    context: Context,
    nextScreenFunction: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(2.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(35.dp),
    ) {
        Spacer(modifier = Modifier.height(0.dp))
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FullNameText(context)
            NameTextField(context, 0.7f, name) {
                    newName -> name = newName

            }
            SurnameTextField(context, 0.7f, surname) {
                    newSurname -> surname = newSurname
            }
        }
        SexRadioButtons(context, selectedSexOption)
        SchoolGradeSpinner(context, selectedSchoolGradeOption) {
                newSelectedSchoolGradeOption -> selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }
        NextButtonCenterEnd(
            context = context,
            onClickFunction = {
                personalDataFormNextButtonOnClick(context, nextScreenFunction)
            }
        )
    }
}

fun personalDataFormNextButtonOnClick(
    context: Context,
    nextScreenFunction: () -> Unit = {}
) {
    if (isDataValid(context)) {
        trimNameAndSurname()
        logcatAllData()
        nextScreenFunction()
    }
}

fun logcatAllData() {
    val logAllDataMessage = StringBuilder()
    logAllDataMessage.append("Información personal: \n")
    logAllDataMessage.append("Nombre completo: ${name.text} ${surname.text} \n")
    if (selectedSexOption.value.isNotEmpty()) {
        logAllDataMessage.append("Sexo: ${selectedSexOption.value} \n")
    }
    if (selectedSchoolGradeOption.isNotEmpty()) {
        logAllDataMessage.append("Grado escolaridad: $selectedSchoolGradeOption \n")
    }
    Log.i("PersonalDataActivity", logAllDataMessage.toString())
}

fun trimNameAndSurname() {
    name = name.copy(text = name.text.trim())
    surname = surname.copy(text = surname.text.trim())
}

fun isDataValid(context: Context) : Boolean {
    val toastMessage = StringBuilder()
    if (!nameIsValid()) {
        toastMessage.append(context.getString(R.string.invalid_name_toast_message))
        toastMessage.append(". ")
    }
    if (!surnameIsValid()) {
        toastMessage.append(context.getString(R.string.invalid_surname_toast_message))
        toastMessage.append(". ")
    }
    if (toastMessage.isNotEmpty()) {
        Toast.makeText(context, toastMessage.toString(), Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

fun nameIsValid(): Boolean{
    if (name.text.isNotEmpty()) {
        return name.text.contains(Regex("[a-zA-Z]"))
    }
    return false
}

fun surnameIsValid(): Boolean {
    if (surname.text.isNotEmpty()) {
        return surname.text.contains(Regex("[a-zA-Z]"))
    }
    return false
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val context = LocalContext.current
    Labs20232Gr03Theme {
        PersonalDataFormPortrait(context)
    }
}