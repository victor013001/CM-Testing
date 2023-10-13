package co.edu.udea.compumovil.gr03_20232.lab1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr03_20232.lab1.ui.ContactDataForm
import co.edu.udea.compumovil.gr03_20232.lab1.ui.PersonalDataForm
import co.edu.udea.compumovil.gr03_20232.lab1.ui.ResultDataScreen

enum class Labs20232Gr03Screen(@StringRes val title: Int) {
    PersonalData(title = R.string.personal_data_form_title),
    ContactData(title = R.string.contact_data_form_title),
    ResultData(title = R.string.title_result_data)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Labs20232Gr03Bar(
    currentScreen: Labs20232Gr03Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(currentScreen.title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
            )
                },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.previous)
                    )
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Labs20232Gr03App(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Labs20232Gr03Screen.valueOf(
        backStackEntry?.destination?.route ?: Labs20232Gr03Screen.PersonalData.name
    )
    Scaffold(
        topBar = {
            Labs20232Gr03Bar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Labs20232Gr03Screen.PersonalData.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Labs20232Gr03Screen.PersonalData.name) {
                PersonalDataForm(
                    nextScreenFunction = {
                        navController.navigate(Labs20232Gr03Screen.ContactData.name)
                    }
                )
            }
            composable(route = Labs20232Gr03Screen.ContactData.name) {
                ContactDataForm(
                    nextScreenFunction = {
                        navController.navigate(Labs20232Gr03Screen.ResultData.name)
                    }
                )
            }
            composable(route = Labs20232Gr03Screen.ResultData.name) {
                ResultDataScreen()
            }
        }
    }
}


