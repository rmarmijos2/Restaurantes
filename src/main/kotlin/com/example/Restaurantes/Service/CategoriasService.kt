package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Repository.CategoriasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class CategoriasService {
    @Autowired
    lateinit var categoriasRepository: CategoriasRepository

    val listaTipos= listOf<String>("Mariscos","Cafe","FoodFast")


    fun list(): List<Categorias> {
        return categoriasRepository.findAll()
    }

    fun save(@RequestBody categorias: Categorias): Categorias{
        try {
            categorias.tipo?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'tipo' no puede estar vacio")

            return categoriasRepository.save(categorias)
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(@RequestBody categorias: Categorias): Categorias{
    try {
        val response = categoriasRepository.findById(categorias.id)
            ?: throw Exception("El ID ${categorias.id} en dueños no existe")

        if (!validarCategorias(categorias.tipo!!)){
            throw Exception("El campo 'tipo' no pertenece a la lista")
        }
            return categoriasRepository.save(categorias)
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun updateTipo (categorias: Categorias): Categorias {
    try {
        categorias.tipo?.takeIf { it.trim().isNotEmpty() }
            ?:  throw Exception("El campo 'tipo' no puede estar vacio")

        val response = categoriasRepository.findById(categorias.id)
            ?: throw Exception("El ID ${categorias.id} en dueños no existe")
        response.apply {
            this.tipo = categorias.tipo
        }
        return categoriasRepository.save(response)
    }
    catch (ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }


    fun delete (id:Long?): Boolean{
        try {
            categoriasRepository.findById(id)
                ?: throw Exception("NO existe el ID")
            categoriasRepository.deleteById(id!!)
            return true
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun validarCategorias(tipo: String): Boolean {
        for (i in listaTipos){
            if (tipo == i){
                return true
            }
        }
        return false
    }
}

