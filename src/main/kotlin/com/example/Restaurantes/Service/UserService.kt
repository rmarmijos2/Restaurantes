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

    fun calcularMultiplicacion (index: Int, number: Int): Int{
        if (index %2 ==0){
            return number * 2
        }
        else {
            return number
        }
    }

    fun restNine(number: Int): Int{
        if (number in 10..18){
            return number - 9
        } else {
            return number
        }
    }

    fun subtactFromNextTen (number: Int): Int{
        var decena = (number/10) + 1
        var response = (decena*10) - number
        if (response == 10) {
            return 0
        }
            return response
    }

}