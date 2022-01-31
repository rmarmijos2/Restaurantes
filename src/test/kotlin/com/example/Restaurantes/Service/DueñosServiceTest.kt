package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Duenos
import com.example.Restaurantes.Repository.DuenosRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

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

    val jsonString = File("./src/test/resources/duenos/crearDuenos.json").readText(Charsets.UTF_8)
    val duenosMock = Gson().fromJson(jsonString, Duenos::class.java)

    @Test
    fun saveDuenos(){
        //Para Actualizar

        Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
        val response = duenosService.save(duenosMock)
        Assertions.assertEquals(response.id, duenosMock.id)
        Assertions.assertEquals(response.nombre, duenosMock.nombre)
        Assertions.assertEquals(response.edad, duenosMock.edad)
        Assertions.assertEquals(response.email, duenosMock.email)
    }

    @Test
    fun saveDuenosFailedDuenos(){
        Assertions.assertThrows(Exception::class.java) {
            duenosMock.apply { nombre= " "}

            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
            duenosService.save(duenosMock)
        }
    }

    @Test
    fun saveDuenosFailedEdad(){
        Assertions.assertThrows(Exception::class.java) {
            duenosMock.apply { edad= 0}

            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
            duenosService.save(duenosMock)
        }
    }

    @Test
    fun saveDuenosFailedEmail(){
        Assertions.assertThrows(Exception::class.java) {
            duenosMock.apply { email= " "}

            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
            duenosService.save(duenosMock)
        }
    }

    //UPDATE

    @Test
    fun updateDuenosIsIdCorrect(){

        Mockito.`when`(duenosRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(returnObject)
        val response = duenosService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.edad, newObject.edad)
        Assertions.assertEquals(response.email, newObject.email)
    }

    @Test
    fun updateDuenosIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(duenosRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(returnObject)
            duenosService.update(newObject)
        }
    }

    @Test
    fun updateDuenosFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            duenosMock.apply { nombre= " "}
            Mockito.`when`(duenosRepository.findById(returnObject.id)).thenReturn(duenosMock)
            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
            duenosService.update(duenosMock)
        }
    }

    @Test
    fun updateDuenosFailedEdad(){
        Assertions.assertThrows(Exception::class.java) {
            duenosMock.apply { edad= 0}
            Mockito.`when`(duenosRepository.findById(returnObject.id)).thenReturn(duenosMock)
            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
            duenosService.update(duenosMock)
        }
    }

    @Test
    fun updateDuenosFailedEmail(){
        Assertions.assertThrows(Exception::class.java) {
            duenosMock.apply { email= " "}
            Mockito.`when`(duenosRepository.findById(returnObject.id)).thenReturn(duenosMock)
            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(duenosMock)
            duenosService.update(duenosMock)
        }
    }

    //DELETE

    @Test
    fun deleteDuenosCorrect(){
        Mockito.`when`(duenosRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(returnObject)
        val response = duenosService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteDuenosIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(duenosRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(duenosRepository.save(Mockito.any(Duenos::class.java))).thenReturn(returnObject)
            val response = duenosService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}