package ru.netology.nmedia.repository

import ru.netology.nmedia.api.ApiService
import ru.netology.nmedia.dto.User
import ru.netology.nmedia.error.ApiError
import ru.netology.nmedia.error.NetworkError
import ru.netology.nmedia.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: ApiService,
) {
    suspend fun authUser(login: String, password: String): User {
        try {
            val response = api.updateUser(login, password)
//            println(1)
            if (!response.isSuccessful) {
//                println("authorized")
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw Exception()
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }

    suspend fun registrationUser(login: String, password: String, name: String): User {
        try {
            val response = api.registrationUser(login, password, name)
//            println(2)
            if (!response.isSuccessful) {
//                println("register")
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw Exception()
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}