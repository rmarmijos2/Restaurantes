package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Dueños
import com.example.Restaurantes.Repository.DueñosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class DueñosService {
    @Autowired
    lateinit var dueñosRepository: DueñosRepository


    fun list(): List<Dueños> {
        return dueñosRepository.findAll()
    }

    fun save(@RequestBody dueños: Dueños): Dueños{
        return dueñosRepository.save(dueños)
    }

    fun update(@RequestBody dueños: Dueños): Dueños {
        return dueñosRepository.save(dueños)
    }

    fun updateNombre (dueños: Dueños): Dueños {
        val response = dueñosRepository.findById(dueños.id)
            ?: throw Exception()
        response.apply {
            this.nombre=dueños.nombre
        }
        return dueñosRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        dueñosRepository.deleteById(id)
        return true
    }
}