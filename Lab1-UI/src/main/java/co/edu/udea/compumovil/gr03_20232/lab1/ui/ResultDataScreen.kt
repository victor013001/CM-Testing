package co.edu.udea.compumovil.gr03_20232.lab1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultDataScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        FullNameText()
        SurnameText()
        SexText()
        SchoolGradeText()
        PhoneText()
        EmailText()
        CountryText()
        AddressText()
    }
}

@Composable
fun FullNameText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = name.text,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun SurnameText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = surname.text,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun SexText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = selectedSexOption.value,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun SchoolGradeText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = selectedSchoolGradeOption,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun PhoneText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = phone.text,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun EmailText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = email.text,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun CountryText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = country.value,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun AddressText() {
    Text(
        modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
        text = address.text,
        fontSize = 16.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Medium
    )
}

