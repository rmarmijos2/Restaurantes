package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Restaurantes
import com.example.Restaurantes.Service.RestaurantesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurantes")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class RestaurantesController {

    @Autowired
    lateinit var restaurantesService: RestaurantesService

    @GetMapping
    fun list(): List<Restaurantes>{
        return restaurantesService.list()
    }

    @PostMapping
    fun save (@RequestBody restaurantes: Restaurantes): Restaurantes{
        return restaurantesService.save(restaurantes)
    }

    @PutMapping
    fun update (@RequestBody restaurantes: Restaurantes): Restaurantes{
        return restaurantesService.update(restaurantes)
    }

    @PatchMapping
    fun updateNombre (@RequestBody restaurantes: Restaurantes): Restaurantes{
        return restaurantesService.updateNombre(restaurantes)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return restaurantesService.delete(id)
    }
}