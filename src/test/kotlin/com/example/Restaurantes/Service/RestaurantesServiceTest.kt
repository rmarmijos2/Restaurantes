package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Model.Duenos
import com.example.Restaurantes.Model.Restaurantes
import com.example.Restaurantes.Repository.CategoriasRepository
import com.example.Restaurantes.Repository.DuenosRepository
import com.example.Restaurantes.Repository.RestaurantesRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import com.google.gson.Gson

@SpringBootTest
class RestaurantesServiceTest {
    @InjectMocks
    lateinit var restaurantesService: RestaurantesService

    @Mock
    lateinit var restaurantesRepository: RestaurantesRepository

    @Mock
    lateinit var duenosRepository: DuenosRepository

    @Mock
    lateinit var categoriasRepository: CategoriasRepository

    val returnObject: Restaurantes = Restaurantes().apply {
        id= 1
        nombre = "Ricky Armijos"
        calificacion = 4
        direccion = "Av. Las Americas"
        idDueno = 1
        idCategoria = 1
    }
    val newObject: Restaurantes = Restaurantes().apply {
        id= 1
        nombre = "Ricky Armijos"
        calificacion = 4
        direccion = "Av. Las Americas"
        idDueno = 1
        idCategoria = 1
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(duenosRepository.findById(restaurantesMock.idDueno)).thenReturn(duenosMock)
        Mockito.`when`(categoriasRepository.findById(restaurantesMock.idCategoria)).thenReturn(categoriasMock)

        Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(returnObject)
        val response = restaurantesService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.calificacion, newObject.calificacion)
        Assertions.assertEquals(response.direccion, newObject.direccion)
        Assertions.assertEquals(response.idDueno, newObject.idDueno)
        Assertions.assertEquals(response.idCategoria, newObject.idCategoria)
    }

    val jsonString = File("./src/test/resources/restaurantes/crearRestaurantes.json").readText(Charsets.UTF_8)
    val restaurantesMock = Gson().fromJson(jsonString, Restaurantes::class.java)

    val jsonString1 = File("./src/test/resources/duenos/crearDuenos.json").readText(Charsets.UTF_8)
    val duenosMock = Gson().fromJson(jsonString1, Duenos::class.java)

    val jsonString2 = File("./src/test/resources/categorias/crearCategorias.json").readText(Charsets.UTF_8)
    val categoriasMock = Gson().fromJson(jsonString2, Categorias::class.java)

    @Test
    fun saveRestaurantes(){
        //Para Actualizar

        Mockito.`when`(duenosRepository.findById(restaurantesMock.idDueno)).thenReturn(duenosMock)
        Mockito.`when`(categoriasRepository.findById(restaurantesMock.idCategoria)).thenReturn(categoriasMock)

        Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
        val response = restaurantesService.save(restaurantesMock)
        Assertions.assertEquals(response.id, restaurantesMock.id)
        Assertions.assertEquals(response.nombre, restaurantesMock.nombre)
        Assertions.assertEquals(response.calificacion, restaurantesMock.calificacion)
        Assertions.assertEquals(response.direccion, restaurantesMock.direccion)
        Assertions.assertEquals(response.idDueno, restaurantesMock.idDueno)
        Assertions.assertEquals(response.idCategoria, restaurantesMock.idCategoria)
    }

    @Test
    fun saveRestaurantesFailedNombres(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { nombre= " "}

            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.save(restaurantesMock)
        }
    }

    @Test
    fun saveRestaurantesFailedCalificacion(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { calificacion= 0}

            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.save(restaurantesMock)
        }
    }

    @Test
    fun saveRestaurantesFailedDireccion(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { direccion= " "}

            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.save(restaurantesMock)
        }
    }

    @Test
    fun saveRestaurantesFailedIdDueno(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { idDueno= 0}

            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.save(restaurantesMock)
        }
    }

    @Test
    fun saveRestaurantesFailedIdCategoria(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { idCategoria= 0}

            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.save(restaurantesMock)
        }
    }

    //UPDATE

    @Test
    fun updateRestaurantesIsIdCorrect(){

        Mockito.`when`(duenosRepository.findById(restaurantesMock.idDueno)).thenReturn(duenosMock)
        Mockito.`when`(categoriasRepository.findById(restaurantesMock.idCategoria)).thenReturn(categoriasMock)
        Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(returnObject)
        val response = restaurantesService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.calificacion, newObject.calificacion)
        Assertions.assertEquals(response.direccion, newObject.direccion)
        Assertions.assertEquals(response.idDueno, newObject.idDueno)
        Assertions.assertEquals(response.idCategoria, newObject.idCategoria)
    }

    @Test
    fun updateRestaurantesIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(duenosRepository.findById(restaurantesMock.idDueno)).thenReturn(duenosMock)
            Mockito.`when`(categoriasRepository.findById(restaurantesMock.idCategoria)).thenReturn(categoriasMock)
            Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(returnObject)
            restaurantesService.update(newObject)
        }
    }

    @Test
    fun updateRestaurantesFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { nombre= " "}
            Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(restaurantesMock)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.update(restaurantesMock)
        }
    }

    @Test
    fun updateRestaurantesFailedCalificacion(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { calificacion= 0}
            Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(restaurantesMock)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.update(restaurantesMock)
        }
    }

    @Test
    fun updateRestaurantesFailedDireccion(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { direccion= " "}
            Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(restaurantesMock)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.update(restaurantesMock)
        }
    }

    @Test
    fun updateRestaurantesFailedIdDueno(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { idDueno= 0}
            Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(restaurantesMock)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.update(restaurantesMock)
        }
    }

    @Test
    fun updateRestaurantesFailedIdCategoria(){
        Assertions.assertThrows(Exception::class.java) {
            restaurantesMock.apply { idCategoria= 0}
            Mockito.`when`(restaurantesRepository.findById(returnObject.id)).thenReturn(restaurantesMock)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(restaurantesMock)
            restaurantesService.update(restaurantesMock)
        }
    }

    //DELETE

    @Test
    fun deleteRestaurantesCorrect(){
        Mockito.`when`(restaurantesRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(returnObject)
        val response = restaurantesService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteRestaurantesIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(restaurantesRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(restaurantesRepository.save(Mockito.any(Restaurantes::class.java))).thenReturn(returnObject)
            val response = restaurantesService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}
