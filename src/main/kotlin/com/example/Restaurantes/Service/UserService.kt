package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.User
import com.example.Restaurantes.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service

class UserService {
    @Autowired
    lateinit var userRepository: UserRepository


    fun list(): List<User> {
        return userRepository.findAll()
    }

    fun getUser(username: String?): User? {
        try {
            val response = userRepository.findByUsername(username)
                ?: throw Exception("Error, no existe usuario")
            return response
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(user: User): User {
        return userRepository.save(user)
    }
}