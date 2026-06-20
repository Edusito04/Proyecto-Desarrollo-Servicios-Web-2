package com.staybook.service;

import com.staybook.entity.EstadoPago;
import com.staybook.entity.Pago;
import com.staybook.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository repository;

    public List<Pago> listar() { return repository.findAll(); }

    public Pago buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));
    }

    public List<Pago> listarPorReserva(Long reservaId) {
        if (reservaId == null || reservaId <= 0) {
            throw new IllegalArgumentException("El id de la reserva es obligatorio");
        }
        return repository.findByReservaId(reservaId);
    }

    public Pago guardar(Pago pago) {
        prepararPago(pago);
        return repository.save(pago);
    }

    public Pago actualizar(Long id, Pago actualizado) {
        Pago pago = buscarPorId(id);
        prepararPago(actualizado);
        pago.setReservaId(actualizado.getReservaId());
        pago.setMonto(actualizado.getMonto());
        pago.setMetodoPago(actualizado.getMetodoPago());
        pago.setEstadoPago(actualizado.getEstadoPago());
        pago.setFechaPago(actualizado.getFechaPago());
        pago.setObservacion(actualizado.getObservacion());
        return repository.save(pago);
    }

    public void eliminar(Long id) {
        Pago pago = buscarPorId(id);
        repository.delete(pago);
    }

    private void prepararPago(Pago pago) {
        if (pago.getReservaId() == null || pago.getReservaId() <= 0) {
            throw new IllegalArgumentException("El id de la reserva es obligatorio");
        }
        if (pago.getEstadoPago() == null) {
            pago.setEstadoPago(EstadoPago.PENDIENTE);
        }
        if (pago.getFechaPago() == null) {
            pago.setFechaPago(LocalDate.now());
        }
    }
}
