package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Usuario
import com.example.Restaurantes.Repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class UsuarioService {
    @Autowired
    lateinit var usuarioRepository: UsuarioRepository


    fun list(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    fun save(@RequestBody usuario: Usuario): Usuario{
    try{
        usuario.nombre?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo 'nombre' no puede estar vacio")

        usuario.email?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo 'email' no puede estar vacio")

        usuario.genero?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo 'genero' no puede estar vacio")

        usuario.direccion?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo 'direccion' no puede estar vacio")

        if (usuario.edad!! <= 0){
            throw Exception("Error, Edad Erronea")
        }
        if (usuario.telefono!! <= 0){
            throw Exception("Error, Telefono Erronea")
        }
        else {
            return usuarioRepository.save(usuario)
        }
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun update(@RequestBody usuario: Usuario): Usuario {
        try {
            val response = usuarioRepository.findById(usuario.id)
                ?: throw Exception("El ID ${usuario.id} en usuarios no existe")

            usuario.nombre?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'nombre' no puede estar vacio")

            usuario.email?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'email' no puede estar vacio")

            usuario.genero?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'genero' no puede estar vacio")

            usuario.direccion?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'direccion' no puede estar vacio")

            if (usuario.edad!! <= 0){
                throw Exception("Error, Edad Erronea")
            }
            if (usuario.telefono!! <= 0){
                throw Exception("Error, Telefono Erronea")
            }
            else {
                return usuarioRepository.save(usuario)
            }
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun updateNombre (usuario: Usuario): Usuario{
    try {
        usuario.nombre?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo 'nombre' no puede estar vacio")

        val response = usuarioRepository.findById(usuario.id)
            ?: throw Exception("El ID ${usuario.id} en usuarios no existe")
        response.apply {
            this.nombre = usuario.nombre
        }
        return usuarioRepository.save(response)
    }
    catch (ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun delete (id:Long?): Boolean{
        try {
            usuarioRepository.findById(id)
                ?: throw Exception("NO existe el ID")
            usuarioRepository.deleteById(id!!)
            return true
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

}