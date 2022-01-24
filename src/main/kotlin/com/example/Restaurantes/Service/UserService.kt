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


    fun save(@RequestBody user: User): User {
        try {
            user.username?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'username' no puede estar vacio")

            user.password?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'password' no puede estar vacio")

            user.cedula?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'cedula' no puede estar vacio")

            if (!this.validarCedula(user.cedula!!)){
                throw Exception("Cédula Incorrecta")
            }

            return userRepository.save(user)
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
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
        val decena = (number/10) + 1
        val response = (decena*10) - number
        if (response == 10) {
            return 0
        }
            return response
    }

    fun validarCedula (cedula: String): Boolean{
        if (cedula.length != 10) {
            return false
        }
        val dosDigitos = cedula.substring(0,2)
        val num1 = cedula.substring(0,1)
        val num2 = cedula.substring(1,2)
        val num3 = cedula.substring(2,3)
        val num4 = cedula.substring(3,4)
        val num5 = cedula.substring(4,5)
        val num6 = cedula.substring(5,6)
        val num7 = cedula.substring(6,7)
        val num8 = cedula.substring(7,8)
        val num9 = cedula.substring(8,9)
        val num10 = cedula.substring(9,10)

        if (dosDigitos.toInt() < 1 || dosDigitos.toInt() > 24){
            return false
        }
        if (num3.toInt() > 6){
            return false
        }
        val pares = num2.toInt() + num4.toInt() + num6.toInt() + num8.toInt()

        val numImpar = num1.toInt() * 2
        if (numImpar > 9){
            numImpar - 9
        }
        var numImpar1 = num3.toInt() * 2
        if (numImpar1 > 9){
            numImpar1 -= 9
        }
        var numImpar2 = num5.toInt() * 2
        if (numImpar2 > 9){
            numImpar2 -= 9
        }
        var numImpar3 = num7.toInt() * 2
        if (numImpar3 > 9){
            numImpar3 -= 9
        }
        var numImpar4 = num9.toInt() * 2
        if (numImpar4 > 9){
            numImpar4 -= 9
        }
        val impares = numImpar + numImpar1 + numImpar2 + numImpar3 + numImpar4
        val sumaTotal = pares + impares

        val decena = (sumaTotal/10) + 1
        var digitoValidador = (decena*10) - sumaTotal
        if (digitoValidador == 10) {
            digitoValidador = 0
        }
        if (digitoValidador != num10.toInt()){
            return false
        }
        return true
    }

}

