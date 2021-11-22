package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Usuario
import com.example.Restaurantes.Repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class UsuarioService {
    @Autowired
    lateinit var usuarioRepository: UsuarioRepository


    fun list(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    fun save(@RequestBody usuario: Usuario): Usuario{
        if (usuario.nombre.equals("") && usuario.edad!! > 100){
            throw Exception()
        }
        else {
            return usuarioRepository.save(usuario)
        }
    }

    fun update(@RequestBody usuario: Usuario): Usuario {
        if (usuario.nombre.equals("") && usuario.edad!! > 100){
            throw Exception()
        }
        else {
            return usuarioRepository.save(usuario)
        }
    }

    fun updateNombre (usuario: Usuario): Usuario{
        val response = usuarioRepository.findById(usuario.id)
            ?: throw Exception()
        response.apply {
            this.nombre=usuario.nombre
        }
        return usuarioRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        usuarioRepository.deleteById(id)
        return true
    }
}