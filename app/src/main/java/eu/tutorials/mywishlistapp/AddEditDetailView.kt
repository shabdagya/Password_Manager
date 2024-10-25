package eu.tutorials.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorials.mywishlistapp.data.Password
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: PwdViewModel,
    navController: NavController
){

    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val password = viewModel.getAPwdById(id).collectAsState(initial = Password(0L, "", ""))

    if (password.value != null) {
        viewModel.passwordTitleState = password.value.title ?: ""
        viewModel.passwordDescriptionState = password.value.description ?: ""
        viewModel.passwordWebsiteState = password.value.website ?: ""
        viewModel.passwordTitleState = ""
        viewModel.passwordDescriptionState = ""
        viewModel.passwordWebsiteState = ""
    }

    Scaffold(
        topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.update_password)
    else stringResource(id = R.string.add_password)
    ) {navController.navigateUp()}
    },
        scaffoldState = scaffoldState
        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            PwdTextField(label = "Website",
                value = viewModel.passwordWebsiteState,
                onValueChanged = {
                    viewModel.onPwdWebsiteChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            PwdTextField(label = "Username",
                value = viewModel.passwordTitleState,
                onValueChanged = {
                    viewModel.onPwdTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            PwdTextField(label = "Password",
                value = viewModel.passwordDescriptionState,
                onValueChanged = {
                    viewModel.onPwdDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick={
                if(viewModel.passwordTitleState.isNotEmpty() &&
                    viewModel.passwordDescriptionState.isNotEmpty()
                    && viewModel.passwordWebsiteState.isNotEmpty()){
                    if(id != 0L){
                        viewModel.updatePwd(
                            Password(
                                id = id,
                                title = viewModel.passwordTitleState.trim(),
                                description = viewModel.passwordDescriptionState.trim(),
                                website= viewModel.passwordWebsiteState.trim()
                            )
                        )
                    }else{

                        viewModel.addPwd(
                            Password(
                                title = viewModel.passwordTitleState.trim(),
                                description = viewModel.passwordDescriptionState.trim(),
                                website = viewModel.passwordWebsiteState.trim()

                                )
                        )
                        snackMessage.value = "Password has been added"
                    }
                }else{
                    //
                    snackMessage.value = "Enter fields to add Password"
                }
                scope.launch {
                    //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }){
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_password)
                    else stringResource(
                        id = R.string.add_password
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }

            Spacer(modifier=Modifier.height(16.dp))

            if (id != 0L) {
                val wishState = viewModel.getAPwdById(id).collectAsState(initial = null)
                val pwd=wishState.value
                if (pwd != null) {
                    Button(
                        onClick = {
                            viewModel.deletePwd(pwd)

                                scope.launch {
                                    navController.navigateUp()
                                }


                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_wish),
                            color = Color.White,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }
                }
            }


        }
    }

}


@Composable
fun PwdTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )


    )
}



