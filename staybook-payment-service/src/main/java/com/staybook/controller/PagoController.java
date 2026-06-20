package com.staybook.controller;

import com.staybook.entity.Pago;
import com.staybook.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService service;

    @GetMapping
    public List<Pago> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Pago buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/reserva/{reservaId}")
    public List<Pago> listarPorReserva(@PathVariable Long reservaId) { return service.listarPorReserva(reservaId); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pago guardar(@Valid @RequestBody Pago pago) { return service.guardar(pago); }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Long id, @Valid @RequestBody Pago pago) { return service.actualizar(id, pago); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
