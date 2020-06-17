package org.dripto.springy.data.runner

import org.dripto.springy.data.model.Cat
import org.dripto.springy.data.model.Dog
import org.dripto.springy.data.service.PetService
import org.dripto.springy.data.trigger.DataTrigger
import org.dripto.springy.extension.pr
import org.dripto.springy.extension.pw
import org.dripto.springy.extension.py
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.*

@Component
@ConditionalOnBean(DataTrigger::class)
class TransactionRunner(
        val petService: PetService
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        pr(petService.getAllPets())

        /**
         * the following will not run due to failed transaction.
         */
        /*
        val id = UUID.randomUUID()
        val dog = Dog(dogId = id, name = "doggo")
        val cat = Cat(catId = id, name = "catto")
        pr(petService.savePets(dog, cat))
         */
    }
}
