package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Dueños
import com.example.Restaurantes.Service.DueñosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dueños")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DueñosController {

    @Autowired
    lateinit var dueñosService: DueñosService

    @GetMapping
    fun list(): List<Dueños>{
        return dueñosService.list()
    }

    @PostMapping
    fun save (@RequestBody dueños: Dueños): Dueños{
        return dueñosService.save(dueños)
    }

    @PutMapping
    fun update (@RequestBody dueños: Dueños): Dueños{
        return dueñosService.update(dueños)
    }

    @PatchMapping
    fun updateNombre (@RequestBody dueños: Dueños): Dueños{
        return dueñosService.updateNombre(dueños)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return dueñosService.delete(id)
    }
}