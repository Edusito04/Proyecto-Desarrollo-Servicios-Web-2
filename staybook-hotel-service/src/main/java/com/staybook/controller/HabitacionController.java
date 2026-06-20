package com.staybook.controller;

import com.staybook.entity.Habitacion;
import com.staybook.service.HabitacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {
    private final HabitacionService service;

    @GetMapping
    public List<Habitacion> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Habitacion buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habitacion guardar(@Valid @RequestBody Habitacion habitacion) { return service.guardar(habitacion); }

    @PutMapping("/{id}")
    public Habitacion actualizar(@PathVariable Long id, @Valid @RequestBody Habitacion habitacion) { return service.actualizar(id, habitacion); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
