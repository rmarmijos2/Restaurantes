package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Duenos
import com.example.Restaurantes.Service.DueñosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/duenos")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DueñosController {

    @Autowired
    lateinit var dueñosService: DueñosService

    @GetMapping
    fun list(): List<Duenos>{
        return dueñosService.list()
    }

    @PostMapping
    fun save (@RequestBody dueños: Duenos): Duenos{
        return dueñosService.save(dueños)
    }

    @PutMapping
    fun update (@RequestBody dueños: Duenos): Duenos{
        return dueñosService.update(dueños)
    }

    @PatchMapping
    fun updateNombre (@RequestBody duenos: Duenos): Duenos{
        return dueñosService.updateNombre(duenos)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return dueñosService.delete(id)
    }
}