package co.edu.udea.compumovil.gr03_20232.lab1

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class MainActivityComposeTest {

    @get:Rule
    val mainActivityComposeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun fillFormTest() {

        val personaDataText = mainActivityComposeTestRule.activity
            .getString(
                R.string.personal_data_form_title
            )
        val nameText = mainActivityComposeTestRule.activity
            .getString(
                R.string.name_text_field_hint
            )
        val surnameText = mainActivityComposeTestRule.activity
            .getString(
                R.string.surname_text_field_hint
            )
        val sexOptionTest = mainActivityComposeTestRule.activity
            .getString(
                R.string.female_sex
            )
        val schoolGradeOptionsText = mainActivityComposeTestRule.activity
            .getString(
                R.string.school_grade_title
            )
        val schoolGradeOptionText = mainActivityComposeTestRule.activity
            .getString(
                R.string.school_grade_university
            )
        val nextText = mainActivityComposeTestRule.activity
            .getString(
                R.string.next
            )
        val contactDataText = mainActivityComposeTestRule.activity
            .getString(
                R.string.contact_data_form_title
            )
        val phoneText = mainActivityComposeTestRule.activity
            .getString(
                R.string.phone_text_field_hint
            )
        val emailText = mainActivityComposeTestRule.activity
            .getString(
                R.string.email_text_field_hint
            )
        val addressText = mainActivityComposeTestRule.activity
            .getString(
                R.string.address_text_field_hint
            )
        val resultText = mainActivityComposeTestRule.activity
            .getString(
                R.string.title_result_data
            )

        val nextButtonNode = hasText(nextText) and hasClickAction()

        val countryAutoCompleteTag = "countryAutoComplete"

        val inputNameSara = "Sara"
        val inputSurnameUribe = "Uribe"
        val inputPhoneSara = "1234567890"
        val inputEmailSara = "sara@email.com"
        val inputColombia = "Colombia"
        val inputAddressSara = "Calle 123"

        mainActivityComposeTestRule
            .onNodeWithText(personaDataText)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(nameText)
            .performTextInput(inputNameSara)

        mainActivityComposeTestRule
            .onNodeWithText(surnameText)
            .performTextInput(inputSurnameUribe)

        mainActivityComposeTestRule
            .onNodeWithTag(sexOptionTest)
            .performClick()

        mainActivityComposeTestRule
            .onNodeWithText(schoolGradeOptionsText)
            .performClick()

        mainActivityComposeTestRule
            .onNodeWithText(schoolGradeOptionText)
            .performClick()

        mainActivityComposeTestRule
            .onNode(nextButtonNode)
            .performClick()

        mainActivityComposeTestRule
            .onNodeWithText(contactDataText)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(phoneText)
            .performTextInput(inputPhoneSara)

        mainActivityComposeTestRule
            .onNodeWithText(emailText)
            .performTextInput(inputEmailSara)

        mainActivityComposeTestRule
            .onNodeWithTag(countryAutoCompleteTag)
            .performTextInput(inputColombia)

        mainActivityComposeTestRule
            .onNodeWithText(addressText)
            .performTextInput(inputAddressSara)

        mainActivityComposeTestRule
            .onNodeWithText(resultText)
            .assertExists()

        mainActivityComposeTestRule
            .onNode(nextButtonNode)
            .performClick()

        mainActivityComposeTestRule
            .onNodeWithText(inputNameSara)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(inputSurnameUribe)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(sexOptionTest)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(schoolGradeOptionText)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(inputPhoneSara)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(inputEmailSara)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(inputColombia)
            .assertExists()

        mainActivityComposeTestRule
            .onNodeWithText(inputAddressSara)
            .assertExists()
    }
}