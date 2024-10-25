package eu.tutorials.mywishlistapp.data

import kotlinx.coroutines.flow.Flow


class PwdRepository(private val pwdDAO: PwdDAO) {

    suspend fun addAPwd(password:Password){
        pwdDAO.addAPwd(password)
    }

    fun getPwds(): Flow<List<Password>> = pwdDAO.getAllPwds()

    fun getAPwdById(id:Long) :Flow<Password> {
        return pwdDAO.getAPwdById(id)
    }

    suspend fun updateAPwd(password:Password){
        pwdDAO.updateAPwd(password)
    }

    suspend fun deleteAPwd(password: Password){
        pwdDAO.deleteAPwd(password)
    }

}