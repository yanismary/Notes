package com.neac.userapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor (
    private val userDao: UserDao
) {

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }

    fun getAllUsers() : Flow<List<User>> {
        return userDao.getAllUsers()
    }

    fun searchUser(query : String) : Flow<List<User>> {
        return userDao.searchUser(query)
    }
}