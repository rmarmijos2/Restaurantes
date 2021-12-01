package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Restaurantes
import com.example.Restaurantes.Repository.DueñosRepository
import com.example.Restaurantes.Repository.RestaurantesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class RestaurantesService {
    @Autowired
    lateinit var restaurantesRepository: RestaurantesRepository

    @Autowired
    lateinit var dueñosRepository: DueñosRepository


    fun list(): List<Restaurantes> {
        return restaurantesRepository.findAll()
    }

    fun save(@RequestBody restaurantes: Restaurantes): Restaurantes{
    try{
        if (restaurantes.calificacion!! > 5 && restaurantes.dueños_idDueño!! < 0){
            throw Exception()
        }
        else {
            return restaurantesRepository.save(restaurantes)
        }
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_ACCEPTABLE, "Valor excedido", ex)
    }

    }

    fun update(@RequestBody restaurantes: Restaurantes): Restaurantes{
        try {
            val response = restaurantesRepository.findById(restaurantes.id)
                ?: throw Exception()
            if (restaurantes.calificacion!! > 5 && restaurantes.dueños_idDueño!! < 0){
                throw Exception()
            }
            else {
                return restaurantesRepository.save(restaurantes)
            }
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no encontrado", ex)
        }
    }

    fun updateNombre (restaurantes: Restaurantes): Restaurantes{
    try {
        val response = restaurantesRepository.findById(restaurantes.id)
            ?: throw Exception()
        response.apply {
            this.nombre = restaurantes.nombre
        }
        return restaurantesRepository.save(response)
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "ID no encontrado", ex)
    }
    }

    fun delete (id:Long): Boolean{
        restaurantesRepository.deleteById(id)
        return true
    }
}