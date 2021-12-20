package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Duenos
import com.example.Restaurantes.Service.DuenosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/duenos")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class DuenosController {

    @Autowired
    lateinit var duenosService: DuenosService

    @GetMapping
    fun list(): List<Duenos>{
        return duenosService.list()
    }

    @PostMapping
    fun save (@RequestBody duenos: Duenos): Duenos{
        return duenosService.save(duenos)
    }

    @PutMapping
    fun update (@RequestBody duenos: Duenos): Duenos{
        return duenosService.update(duenos)
    }

    @PatchMapping
    fun updateNombre (@RequestBody duenos: Duenos): Duenos{
        return duenosService.updateNombre(duenos)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return duenosService.delete(id)
    }
}