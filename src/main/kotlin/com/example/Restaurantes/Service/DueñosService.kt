package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Duenos
import com.example.Restaurantes.Repository.DueñosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class DueñosService {
    @Autowired
    lateinit var dueñosRepository: DueñosRepository


    fun list(): List<Duenos> {
        return dueñosRepository.findAll()
    }

    fun save(@RequestBody duenos: Duenos): Duenos{
    try {
        duenos.nombre?.takeIf { it.trim().isNotEmpty() } //Falta Validar Email
            ?: throw Exception("Los campos no pueden estar vacios")

            return dueñosRepository.save(duenos)
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun update(@RequestBody duenos: Duenos): Duenos {
        try {
            val response = dueñosRepository.findById(duenos.id)
                ?: throw Exception("El ID ${duenos.id} en dueños no existe")

            duenos.nombre?.takeIf { it.trim().isNotEmpty() } //Falta Validar Email
                ?: throw Exception("Los campos no pueden estar vacios")

            return dueñosRepository.save(duenos)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun updateNombre (duenos: Duenos): Duenos {
     try {
        duenos.nombre?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("El campo 'nombre' no puede estar vacio")

        val response = dueñosRepository.findById(duenos.id)
            ?: throw Exception("El ID ${duenos.id} en dueños no existe")
        response.apply {
            this.nombre = duenos.nombre
        }
        return dueñosRepository.save(response)
    }
    catch (ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun delete (id:Long): Boolean{
        dueñosRepository.deleteById(id)
        return true
    }
}