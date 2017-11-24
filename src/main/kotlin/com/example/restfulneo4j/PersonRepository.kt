package com.example.restfulneo4j

import org.springframework.data.neo4j.repository.GraphRepository

interface PersonRepository : GraphRepository<Person> {
    fun findByName(name: String): Person
}