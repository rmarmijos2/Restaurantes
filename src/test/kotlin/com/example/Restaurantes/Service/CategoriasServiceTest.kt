package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Repository.CategoriasRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

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

    val jsonString = File("./src/test/resources/categorias/crearCategorias.json").readText(Charsets.UTF_8)
    val categoriasMock = Gson().fromJson(jsonString, Categorias::class.java)

    @Test
    fun saveCategorias(){
        //Para Actualizar

        Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(categoriasMock)
        val response = categoriasService.save(categoriasMock)
        Assertions.assertEquals(response.id, categoriasMock.id)
        Assertions.assertEquals(response.tipo, categoriasMock.tipo)
    }

    @Test
    fun saveCategoriasFailedCategorias(){
        Assertions.assertThrows(Exception::class.java) {
            categoriasMock.apply { tipo= " "}

            Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(categoriasMock)
            categoriasService.save(categoriasMock)
        }
    }

    //UPDATE

    @Test
    fun updateCategoriasIsIdCorrect(){

        Mockito.`when`(categoriasRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(returnObject)
        val response = categoriasService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.tipo, newObject.tipo)
    }

    @Test
    fun updateCategoriasIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(categoriasRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(returnObject)
            categoriasService.update(newObject)
        }
    }

    @Test
    fun updateCategoriasFailedTipo(){
        Assertions.assertThrows(Exception::class.java) {
            categoriasMock.apply { tipo= " "}
            Mockito.`when`(categoriasRepository.findById(returnObject.id)).thenReturn(categoriasMock)
            Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(categoriasMock)
            categoriasService.update(categoriasMock)
        }
    }

    //DELETE

    @Test
    fun deleteCategoriaCorrect(){
        Mockito.`when`(categoriasRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(returnObject)
        val response = categoriasService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteCategoriaIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(categoriasRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(categoriasRepository.save(Mockito.any(Categorias::class.java))).thenReturn(returnObject)
            val response = categoriasService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}

