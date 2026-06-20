package com.staybook.service;

import com.staybook.entity.Habitacion;
import com.staybook.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionService {
    private final HabitacionRepository repository;

    public List<Habitacion> listar() { return repository.findAll(); }

    public Habitacion buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada"));
    }

    public Habitacion guardar(Habitacion habitacion) { return repository.save(habitacion); }

    public Habitacion actualizar(Long id, Habitacion actualizada) {
        Habitacion habitacion = buscarPorId(id);
        habitacion.setNumero(actualizada.getNumero());
        habitacion.setTipo(actualizada.getTipo());
        habitacion.setPrecio(actualizada.getPrecio());
        habitacion.setDisponible(actualizada.getDisponible());
        return repository.save(habitacion);
    }

    public void eliminar(Long id) {
        Habitacion habitacion = buscarPorId(id);
        repository.delete(habitacion);
    }
}
