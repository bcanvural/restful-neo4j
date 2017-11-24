package com.example.restfulneo4j

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship

import java.util.*

@NodeEntity
class Person() {

    @GraphId
    var id: Long? = null

    @Property(name = "name")
    lateinit var name: String

    constructor(name: String) : this() {
        this.name = name
    }

    @Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
    private var teammates: HashSet<Person>? = null
        get() {
            if (field == null) field = HashSet()
            return field
        }


    fun worksWith(person: Person) {
        teammates?.add(person)
    }


}