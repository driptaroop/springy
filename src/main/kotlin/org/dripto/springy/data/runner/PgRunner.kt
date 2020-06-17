package org.dripto.springy.data.runner

import com.github.javafaker.Faker
import org.dripto.springy.data.model.Cat
import org.dripto.springy.data.model.Dog
import org.dripto.springy.data.repositories.CatRepository
import org.dripto.springy.data.repositories.DogRepository
import org.dripto.springy.data.repositories.PetRepositories
import org.dripto.springy.data.trigger.DataTrigger
import org.dripto.springy.extension.py
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@ConditionalOnBean(DataTrigger::class)
@Profile("pg")
class PgRunner(
        val faker: Faker,
        val petRepositories: PetRepositories,
        val dogRepository: DogRepository,
        val catRepository: CatRepository
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val dog = Dog(name = "misti")
        val cat = Cat(name = "meni")
        petRepositories.save(dog)
        petRepositories.save(cat)
        py(petRepositories.findAll())

        dogRepository.save(Dog(name = faker.dog().name()))
        catRepository.save(Cat(name = faker.cat().name()))
        py(petRepositories.findAll())
        py(dogRepository.findAll())
        py(catRepository.findAll())


    }
}
