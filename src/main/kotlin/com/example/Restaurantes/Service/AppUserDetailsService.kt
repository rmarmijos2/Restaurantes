package com.example.Restaurantes.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userService: UserService

    override fun loadUserByUsername(username: String?): UserDetails {
        val response = userService.getUser(username)
        return User(response?.username,"{noop}"+response?.password, ArrayList())
    }

}