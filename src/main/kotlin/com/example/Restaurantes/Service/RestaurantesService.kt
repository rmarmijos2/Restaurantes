package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Restaurantes
import com.example.Restaurantes.Repository.RestaurantesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class RestaurantesService {
    @Autowired
    lateinit var restaurantesRepository: RestaurantesRepository


    fun list(): List<Restaurantes> {
        return restaurantesRepository.findAll()
    }

    fun save(@RequestBody restaurantes: Restaurantes): Restaurantes{
        return restaurantesRepository.save(restaurantes)
    }

    fun update(@RequestBody restaurantes: Restaurantes): Restaurantes{
        return restaurantesRepository.save(restaurantes)
    }

    fun updateNombre (restaurantes: Restaurantes): Restaurantes{
        val response = restaurantesRepository.findById(restaurantes.id)
            ?: throw Exception()
        response.apply {
            this.nombre=restaurantes.nombre
        }
        return restaurantesRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        restaurantesRepository.deleteById(id)
        return true
    }
}