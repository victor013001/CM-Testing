package co.edu.udea.compumovil.gr03_20232.lab1

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityComposeTest {

    @get:Rule
    val mainActivityComposeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupMoodTrackerAppNavHost() {
        mainActivityComposeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            Labs20232Gr03App(navController = navController)
        }
    }

    fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
    }

    @Test
    fun fillFormTest() {

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

        val nextButtonNode = hasText(nextText) and hasClickAction()

        val countryAutoCompleteTag = "countryAutoComplete"

        val inputNameSara = "Sara"
        val inputSurnameUribe = "Uribe"
        val inputPhoneSara = "1234567890"
        val inputEmailSara = "sara@email.com"
        val inputColombia = "Colombia"
        val inputAddressSara = "Calle 123"

        navController.assertCurrentRouteName(Labs20232Gr03Screen.PersonalData.name)
        mainActivityComposeTestRule
            .onNode(nextButtonNode)
            .performClick()
        navController.assertCurrentRouteName(Labs20232Gr03Screen.PersonalData.name)

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

        navController.assertCurrentRouteName(Labs20232Gr03Screen.ContactData.name)

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
            .onNode(nextButtonNode)
            .performClick()

        navController.assertCurrentRouteName(Labs20232Gr03Screen.ResultData.name)

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