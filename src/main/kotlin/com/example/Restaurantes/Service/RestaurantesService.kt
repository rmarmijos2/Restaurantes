package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Restaurantes
import com.example.Restaurantes.Repository.CategoriasRepository
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
    @Autowired
    lateinit var categoriaRepository: CategoriasRepository


    fun list(): List<Restaurantes> {
        return restaurantesRepository.findAll()
    }

    fun save(@RequestBody restaurantes: Restaurantes): Restaurantes {
    try{

        val response = dueñosRepository.findById(restaurantes.idDueno)
            ?: throw Exception("El ID ${restaurantes.idDueno} en dueños no existe")

        val respons = categoriaRepository.findById(restaurantes.idCategoria)
            ?: throw Exception("El ID ${restaurantes.idCategoria} en categorias no existe")

        if (restaurantes.calificacion!! > 5){
            throw Exception("Valor excedido")
        }
        else {
            return restaurantesRepository.save(restaurantes)
        }
    }
    catch (ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }

    }

    fun update(@RequestBody restaurantes: Restaurantes): Restaurantes{
        try {

            val response = restaurantesRepository.findById(restaurantes.id)
                ?: throw Exception("El ID ${restaurantes.id} en restaurantes no existe")

            val respons = dueñosRepository.findById(restaurantes.idDueno)
                ?: throw Exception("El ID ${restaurantes.idDueno} en la tabla dueños no existe")

            val response1 = categoriaRepository.findById(restaurantes.idCategoria)
                ?: throw Exception("El ID ${restaurantes.idCategoria} en categorias no existe")

            if (restaurantes.calificacion!! > 5){
                throw Exception("Valor Excedido")
            }
            else {
                return restaurantesRepository.save(restaurantes)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun updateNombre (restaurantes: Restaurantes): Restaurantes{
    try {
        if(restaurantes.nombre.equals("")){
            throw Exception("El campo 'nombre' no puede estar vacio")
        }
        val response = restaurantesRepository.findById(restaurantes.id)
            ?: throw Exception("El ID ${restaurantes.id} en restaurantes no existe")
        response.apply {
            this.nombre = restaurantes.nombre
        }
        return restaurantesRepository.save(response)
    }
    catch (ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun delete (id:Long): Boolean{
        restaurantesRepository.deleteById(id)
        return true
    }
}