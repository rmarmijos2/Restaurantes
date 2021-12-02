package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Dueños
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


    fun list(): List<Dueños> {
        return dueñosRepository.findAll()
    }

    fun save(@RequestBody dueños: Dueños): Dueños{
    try {
        if (dueños.nombre.equals("") || dueños.email.equals("")) {
            throw Exception("Los campos no pueden estar vacios")
        } else {
            return dueñosRepository.save(dueños)
        }
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun update(@RequestBody dueños: Dueños): Dueños {
        try {
            val response = dueñosRepository.findById(dueños.id)
                ?: throw Exception("El ID ${dueños.id} en dueños no existe")

            if (dueños.nombre.equals("") || dueños.email.equals("")){
                throw Exception("Los campos no pueden estar vacios")
            }
            else {
                return dueñosRepository.save(dueños)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun updateNombre (dueños: Dueños): Dueños {
    try {
        if(dueños.nombre.equals("")){
            throw Exception("El campo 'nombre' no puede estar vacio")
        }
        val response = dueñosRepository.findById(dueños.id)
            ?: throw Exception("El ID ${dueños.id} en dueños no existe")
        response.apply {
            this.nombre = dueños.nombre
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