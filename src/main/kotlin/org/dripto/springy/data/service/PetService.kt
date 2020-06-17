package org.dripto.springy.data.service

import org.dripto.springy.data.model.Pet
import org.dripto.springy.data.repositories.PetRepositories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PetService(
        private val petRepositories: PetRepositories
) {
    @Transactional(readOnly = true)
    fun getAllPets() = petRepositories.findAll()

    @Transactional
    fun savePets(vararg pets: Pet) = petRepositories.saveAll(pets.toList())
}
