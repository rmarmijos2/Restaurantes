package com.example.Restaurantes.Controller

import com.example.Restaurantes.Model.Categorias
import com.example.Restaurantes.Model.User
import com.example.Restaurantes.Service.CategoriasService
import com.example.Restaurantes.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun save (@RequestBody user: User): User {
        return userService.save(user)
    }

}