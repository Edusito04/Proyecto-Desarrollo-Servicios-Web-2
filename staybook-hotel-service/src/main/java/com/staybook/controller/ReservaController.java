package com.staybook.controller;

import com.staybook.entity.Reserva;
import com.staybook.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService service;

    @GetMapping
    public List<Reserva> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Reserva buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva guardar(@Valid @RequestBody Reserva reserva) { return service.guardar(reserva); }

    @PutMapping("/{id}")
    public Reserva actualizar(@PathVariable Long id, @Valid @RequestBody Reserva reserva) { return service.actualizar(id, reserva); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
