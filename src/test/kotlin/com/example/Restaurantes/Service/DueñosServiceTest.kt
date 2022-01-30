package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Model.Duenos
import com.example.Restaurantes.Repository.CategoriasRepository
import com.example.Restaurantes.Repository.DuenosRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DueñosServiceTest {
    @InjectMocks
    lateinit var duenosService: DuenosService

    @Mock
    lateinit var duenosRepository: DuenosRepository

    val returnObject: Duenos = Duenos().apply {
        id= 1
        nombre = "Ricky Armijos"
        edad = 20
        email = "rickyarmijos21@outlook.com"
    }
    val newObject: Duenos = Duenos().apply {
        id= 1
        nombre = "Ricky Armijos"
        edad = 20
        email = "rickyarmijos21@outlook.com"
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(returnObject)
        val response = duenosService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.edad, newObject.edad)
        Assertions.assertEquals(response.email, newObject.email)
    }
}