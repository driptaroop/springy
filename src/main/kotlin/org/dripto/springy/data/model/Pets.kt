package org.dripto.springy.data.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.Transient

@Entity
@Inheritance(strategy = JOINED)
abstract class Pet(
        @Id
        val id: UUID,
        val relationshipWithHuman: String = "friendly",
        val petName: String
) {
    protected abstract val type: String
    protected abstract val sound: String
}

@Entity
data class Dog(
        @Transient
        val dogId: UUID = UUID.randomUUID(),
        override val type: String = "CANINE",
        override val sound: String = "woof",
        val preferredPackSize: Int = 2,
        @Transient
        val name: String
) : Pet(
        id = dogId,
        petName = name)

@Entity
data class Cat(
        @Transient
        val catId: UUID = UUID.randomUUID(),
        override val type: String = "FELINE",
        override val sound: String = "meow",
        val food: String = "milk",
        @Transient
        val name: String
) : Pet(
        id = catId,
        petName = name)
