package com.staybook.service;

import com.staybook.entity.Cliente;
import com.staybook.entity.Habitacion;
import com.staybook.entity.Reserva;
import com.staybook.repository.ClienteRepository;
import com.staybook.repository.HabitacionRepository;
import com.staybook.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository repository;
    private final ClienteRepository clienteRepository;
    private final HabitacionRepository habitacionRepository;

    public List<Reserva> listar() { return repository.findAll(); }

    public Reserva buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
    }

    public Reserva guardar(Reserva reserva) {
        validarFechas(reserva);
        Cliente cliente = clienteRepository.findById(reserva.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Habitacion habitacion = habitacionRepository.findById(reserva.getHabitacion().getId())
                .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));
        reserva.setCliente(cliente);
        reserva.setHabitacion(habitacion);
        return repository.save(reserva);
    }

    public Reserva actualizar(Long id, Reserva actualizada) {
        Reserva reserva = buscarPorId(id);
        validarFechas(actualizada);
        Cliente cliente = clienteRepository.findById(actualizada.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Habitacion habitacion = habitacionRepository.findById(actualizada.getHabitacion().getId())
                .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));
        reserva.setCliente(cliente);
        reserva.setHabitacion(habitacion);
        reserva.setFechaEntrada(actualizada.getFechaEntrada());
        reserva.setFechaSalida(actualizada.getFechaSalida());
        return repository.save(reserva);
    }

    public void eliminar(Long id) {
        Reserva reserva = buscarPorId(id);
        repository.delete(reserva);
    }

    private void validarFechas(Reserva reserva) {
        if (reserva.getFechaEntrada() == null || reserva.getFechaSalida() == null) {
            throw new IllegalArgumentException("Las fechas de la reserva son obligatorias");
        }
        if (!reserva.getFechaSalida().isAfter(reserva.getFechaEntrada())) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
    }
}
