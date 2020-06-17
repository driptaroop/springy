package org.dripto.springy.data.repositories

import org.dripto.springy.data.model.Cat
import org.dripto.springy.data.model.Dog
import org.dripto.springy.data.model.Pet
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PetRepositories: CrudRepository<Pet, UUID>

interface DogRepository: CrudRepository<Dog, UUID>
interface CatRepository: CrudRepository<Cat, UUID>
