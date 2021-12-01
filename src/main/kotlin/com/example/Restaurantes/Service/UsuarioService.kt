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
        if (usuario.nombre.equals("") && usuario.edad!! > 100){
            throw Exception()
        }
        else {
            return usuarioRepository.save(usuario)
        }
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "Error en usuario", ex)
    }
    }

    fun update(@RequestBody usuario: Usuario): Usuario {
        try {
            val response = usuarioRepository.findById(usuario.id)
                ?: throw Exception()
            if (usuario.nombre.equals("") && usuario.edad!! > 100){
                throw Exception()
            }
            else {
                return usuarioRepository.save(usuario)
            }
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

    fun updateNombre (usuario: Usuario): Usuario{
    try {
        val response = usuarioRepository.findById(usuario.id)
            ?: throw Exception()
        response.apply {
            this.nombre = usuario.nombre
        }
        return usuarioRepository.save(response)
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "ID no encontrado", ex)
    }
    }

    fun delete (id:Long): Boolean{
        usuarioRepository.deleteById(id)
        return true
    }
}