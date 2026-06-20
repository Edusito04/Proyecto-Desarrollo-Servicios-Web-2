package com.staybook.repository;

import com.staybook.entity.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository
        extends JpaRepository<Reserva, Long> {
}