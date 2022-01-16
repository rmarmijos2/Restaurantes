package com.example.Restaurantes.Service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {
    @Autowired
    lateinit var userService: UserService

    @Test
    fun calcularMultiplicacionIfIsPair () {
        val response = userService.calcularMultiplicacion(2,4)
        Assertions.assertEquals(8, response)
    }

    @Test
    fun calcularMultiplicacionIfIsNotPair () {
        val response = userService.calcularMultiplicacion(1,4)
        Assertions.assertEquals(4, response)
    }

    @Test
    fun restNine (){
        val response = userService.restNine(19)
        Assertions.assertEquals(19, response)
    }

    @Test
    fun subtactFromNextTen (){
        val response = userService.subtactFromNextTen(0)
        Assertions.assertEquals(0, response)
    }

    @Test
    fun validarCedula (){
        val response = userService.validarCedula("0150102861")
        Assertions.assertEquals(true, response)
    }

}