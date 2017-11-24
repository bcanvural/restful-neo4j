package com.example.restfulneo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@RestController
class MyServiceImpl {

    @Autowired
    lateinit var personRepo: PersonRepository

    @GET
    @RequestMapping(value = "/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@PathVariable("name") name: String): String {
        return personRepo.findByName(name).name
    }


}