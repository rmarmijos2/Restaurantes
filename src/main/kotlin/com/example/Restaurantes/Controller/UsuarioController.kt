package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Usuario
import com.example.Restaurantes.Service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class UsuarioController {

    @Autowired
    lateinit var usuarioService: UsuarioService

    @GetMapping
    fun list(): List<Usuario>{
        return usuarioService.list()
    }

    @PostMapping
    fun save (@RequestBody usuario: Usuario): Usuario{
        return usuarioService.save(usuario)
    }

    @PutMapping
    fun update (@RequestBody usuario: Usuario): Usuario{
        return usuarioService.update(usuario)
    }

    @PatchMapping
    fun updateNombre (@RequestBody usuario: Usuario): Usuario{
        return usuarioService.updateNombre(usuario)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return usuarioService.delete(id)
    }
}