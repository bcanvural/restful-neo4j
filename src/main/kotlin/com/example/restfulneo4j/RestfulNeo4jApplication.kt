package com.example.restfulneo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories

@SpringBootApplication
@EnableNeo4jRepositories
class RestfulNeo4jApplication {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Bean
    fun populate(): CommandLineRunner {
        return CommandLineRunner {
            personRepository.deleteAll()
            var greg = Person("Greg")
            var roy = Person("Roy")
            val craig = Person("Craig")
            val team = listOf(greg, roy, craig)

            println("Before linking up with Neo4j...")

            team.stream().forEach { person -> println("\t" + person.toString()) }

            personRepository.save(greg)
            personRepository.save(roy)
            personRepository.save(craig)

            greg = personRepository.findByName(greg.name)
            greg.worksWith(roy)
            greg.worksWith(craig)
            personRepository.save(greg)

            roy = personRepository.findByName(roy.name)
            roy.worksWith(craig)
            // We already know that roy works with greg
            personRepository.save(roy)

            // We already know craig works with roy and greg

            println("Lookup each person by name...")
            team.stream().forEach { person ->
                println(
                        "\t" + personRepository.findByName(person.name).name)
            }
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(RestfulNeo4jApplication::class.java, *args)
}
