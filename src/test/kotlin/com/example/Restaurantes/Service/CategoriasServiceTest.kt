package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Repository.CategoriasRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CategoriasServiceTest {
    @InjectMocks
    lateinit var categoriasService: CategoriasService

    @Mock
    lateinit var categoriasRepository: CategoriasRepository

    val returnObject: Categorias = Categorias().apply {
        id= 1
        tipo="Mariscos"
    }
    val newObject: Categorias = Categorias().apply {
        id= 1
        tipo="Mariscos"
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(returnObject)
        val response = categoriasService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.tipo, newObject.tipo)
    }

}