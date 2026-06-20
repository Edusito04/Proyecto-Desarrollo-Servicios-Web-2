package com.staybook.repository;

import com.staybook.entity.Habitacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository
        extends JpaRepository<Habitacion, Long> {
}