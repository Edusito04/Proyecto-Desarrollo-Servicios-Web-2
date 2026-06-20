package com.staybook.service;

import com.staybook.entity.Cliente;
import com.staybook.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public List<Cliente> listar() { return repository.findAll(); }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }

    public Cliente guardar(Cliente cliente) { return repository.save(cliente); }

    public Cliente actualizar(Long id, Cliente actualizado) {
        Cliente cliente = buscarPorId(id);
        cliente.setNombre(actualizado.getNombre());
        cliente.setApellido(actualizado.getApellido());
        cliente.setDni(actualizado.getDni());
        cliente.setTelefono(actualizado.getTelefono());
        cliente.setCorreo(actualizado.getCorreo());
        return repository.save(cliente);
    }

    public void eliminar(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }
}
