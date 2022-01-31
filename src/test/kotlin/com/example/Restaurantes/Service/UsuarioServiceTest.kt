package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Usuario
import com.example.Restaurantes.Repository.UsuarioRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class UsuarioServiceTest {
    @InjectMocks
    lateinit var usuarioService: UsuarioService

    @Mock
    lateinit var usuarioRepository: UsuarioRepository

    val returnObject: Usuario = Usuario().apply {
        id= 1
        nombre = "Ricky Armijos"
        edad = 20
        email = "rickyarmijos21@outlook.com"
        telefono = 2899623
        genero = "Masculino"
        direccion = "Orquideas"
    }
    val newObject: Usuario = Usuario().apply {
        id= 1
        nombre = "Ricky Armijos"
        edad = 20
        email = "rickyarmijos21@outlook.com"
        telefono = 2899623
        genero = "Masculino"
        direccion = "Orquideas"
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(returnObject)
        val response = usuarioService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.edad, newObject.edad)
        Assertions.assertEquals(response.email, newObject.email)
        Assertions.assertEquals(response.telefono, newObject.telefono)
        Assertions.assertEquals(response.genero, newObject.genero)
        Assertions.assertEquals(response.direccion, newObject.direccion)
    }

    val jsonString = File("./src/test/resources/usuarios/crearUsuarios.json").readText(Charsets.UTF_8)
    val usuariosMock = Gson().fromJson(jsonString, Usuario::class.java)

    @Test
    fun saveUsuario(){
        //Para Actualizar

        Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
        val response = usuarioService.save(usuariosMock)
        Assertions.assertEquals(response.id, usuariosMock.id)
        Assertions.assertEquals(response.nombre, usuariosMock.nombre)
        Assertions.assertEquals(response.edad, usuariosMock.edad)
        Assertions.assertEquals(response.email, usuariosMock.email)
        Assertions.assertEquals(response.telefono, usuariosMock.telefono)
        Assertions.assertEquals(response.genero, usuariosMock.genero)
        Assertions.assertEquals(response.direccion, newObject.direccion)
    }

    @Test
    fun saveUsuarioFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { nombre= " "}

            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.save(usuariosMock)
        }
    }

    @Test
    fun saveUsuarioFailedEdad(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { edad= 0}

            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.save(usuariosMock)
        }
    }

    @Test
    fun saveUsuarioFailedEmail(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { email= " "}

            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.save(usuariosMock)
        }
    }

    @Test
    fun saveUsuarioFailedTelefono(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { telefono= 0}

            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.save(usuariosMock)
        }
    }

    @Test
    fun saveUsuarioFailedGenero(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { genero= " "}

            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.save(usuariosMock)
        }
    }

    @Test
    fun saveUsuarioFailedDireccion(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { direccion= " "}

            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.save(usuariosMock)
        }
    }

    //UPDATE

    @Test
    fun updateUsuarioIsIdCorrect(){

        Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(returnObject)
        val response = usuarioService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.edad, newObject.edad)
        Assertions.assertEquals(response.email, newObject.email)
        Assertions.assertEquals(response.telefono, newObject.telefono)
        Assertions.assertEquals(response.genero, newObject.genero)
        Assertions.assertEquals(response.direccion, newObject.direccion)
    }

    @Test
    fun updateUsuarioIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(returnObject)
            usuarioService.update(newObject)
        }
    }

    @Test
    fun updateUsuarioFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { nombre= " "}
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(usuariosMock)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.update(usuariosMock)
        }
    }

    @Test
    fun updateUsuarioFailedEdad(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { edad= 0}
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(usuariosMock)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.update(usuariosMock)
        }
    }

    @Test
    fun updateUsuarioFailedEmail(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { email= " "}
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(usuariosMock)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.update(usuariosMock)
        }
    }

    @Test
    fun updateUsuarioFailedTelefono(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { telefono= 0}
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(usuariosMock)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.update(usuariosMock)
        }
    }

    @Test
    fun updateUsuarioFailedGenero(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { genero= " "}
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(usuariosMock)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.update(usuariosMock)
        }
    }

    @Test
    fun updateUsuarioFailedDireccion(){
        Assertions.assertThrows(Exception::class.java) {
            usuariosMock.apply { direccion= " "}
            Mockito.`when`(usuarioRepository.findById(returnObject.id)).thenReturn(usuariosMock)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuariosMock)
            usuarioService.update(usuariosMock)
        }
    }

    //DELETE

    @Test
    fun deleteUsuarioCorrect(){
        Mockito.`when`(usuarioRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(returnObject)
        val response = usuarioService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteUsuarioIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(usuarioRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(returnObject)
            val response = usuarioService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }

}