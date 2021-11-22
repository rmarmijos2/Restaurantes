package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Service.CategoriasService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categoria")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class CategoriasController {

    @Autowired
    lateinit var categoriasService: CategoriasService

    @GetMapping
    fun list(): List<Categorias>{
        return categoriasService.list()
    }

    @PostMapping
    fun save (@RequestBody categorias: Categorias): Categorias{
        return categoriasService.save(categorias)
    }

    @PutMapping
    fun update (@RequestBody categorias: Categorias): Categorias {
        return categoriasService.update(categorias)
    }

    @PatchMapping
    fun updateTipo (@RequestBody categorias: Categorias): Categorias {
        return categoriasService.updateTipo(categorias)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return categoriasService.delete(id)
    }
}