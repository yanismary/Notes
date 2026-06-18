package com.neac.userapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Delete from users")
    suspend fun deleteAllUser()

    @Query("Select * from users")
    fun getAllUsers() : Flow<List<User>>

    @Query("Select * from users where first_name like :query or last_name like :query")
    fun searchUser(query : String) : Flow<List<User>>
}













