package com.example.Restaurantes.Service

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Repository.CategoriasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class CategoriasService {
    @Autowired
    lateinit var categoriasRepository: CategoriasRepository


    fun list(): List<Categorias> {
        return categoriasRepository.findAll()
    }

    fun save(@RequestBody categorias: Categorias): Categorias{
        return categoriasRepository.save(categorias)
    }

    fun update(@RequestBody categorias: Categorias): Categorias{
        return categoriasRepository.save(categorias)
    }

    fun updateTipo (categorias: Categorias): Categorias {
        val response = categoriasRepository.findById(categorias.id)
            ?: throw Exception()
        response.apply {
            this.tipo=categorias.tipo
        }
        return categoriasRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        categoriasRepository.deleteById(id)
        return true
    }
}