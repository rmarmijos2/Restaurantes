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
        if (dueños.nombre.equals("") && dueños.email.equals("")) {
            throw Exception()
        } else {
            return dueñosRepository.save(dueños)
        }
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NO_CONTENT, "No existe contenido", ex)
    }
    }

    fun update(@RequestBody dueños: Dueños): Dueños {
        try {
            val response = dueñosRepository.findById(dueños.id)
                ?: throw Exception()
            if (dueños.nombre.equals("") && dueños.email.equals("")){
                throw Exception()
            }
            else {
                return dueñosRepository.save(dueños)
            }
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

    fun updateNombre (dueños: Dueños): Dueños {
    try {
        val response = dueñosRepository.findById(dueños.id)
            ?: throw Exception()
        response.apply {
            this.nombre = dueños.nombre
        }
        return dueñosRepository.save(response)
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "ID no encontrado", ex)
    }
    }

    fun delete (id:Long): Boolean{
        dueñosRepository.deleteById(id)
        return true
    }
}