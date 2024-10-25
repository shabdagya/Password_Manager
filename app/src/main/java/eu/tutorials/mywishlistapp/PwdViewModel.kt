package eu.tutorials.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.mywishlistapp.data.Password
import eu.tutorials.mywishlistapp.data.PwdRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PwdViewModel(
    private val pwdRepository: PwdRepository = Graph.pwdRepository
):ViewModel() {

    var passwordTitleState by mutableStateOf("")
    var passwordDescriptionState by mutableStateOf("")
    var passwordWebsiteState by mutableStateOf("")


    fun onPwdTitleChanged(newString:String){
        passwordTitleState = newString
    }

    fun onPwdDescriptionChanged(newString: String){
        passwordDescriptionState = newString
    }

    fun onPwdWebsiteChanged(newString: String){
        passwordWebsiteState=newString
    }

    lateinit var getAllPwds: Flow<List<Password>>

    init {
        viewModelScope.launch {
            getAllPwds = pwdRepository.getPwds()
        }
    }

    fun addPwd(password: Password){
        viewModelScope.launch(Dispatchers.IO) {
            pwdRepository.addAPwd(password= password)
        }
    }

    fun getAPwdById(id:Long):Flow<Password> {
        return pwdRepository.getAPwdById(id)
    }

    fun updatePwd(password: Password){
        viewModelScope.launch(Dispatchers.IO) {
            pwdRepository.updateAPwd(password= password)
        }
    }

    fun deletePwd(password: Password?){
        if(password!=null){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    pwdRepository.deleteAPwd(password)
                    getAllPwds = pwdRepository.getPwds()
                }catch(e:Exception){
                    e.printStackTrace()

                }
            }
        }
    }
}