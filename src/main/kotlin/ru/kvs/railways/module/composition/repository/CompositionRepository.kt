package ru.kvs.railways.module.composition.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.composition.Composition

@Repository
interface CompositionRepository: JpaRepository<Composition, Int>
