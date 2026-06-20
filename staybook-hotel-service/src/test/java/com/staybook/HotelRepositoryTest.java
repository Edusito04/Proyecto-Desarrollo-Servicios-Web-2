package com.staybook;

import com.staybook.entity.Cliente;
import com.staybook.entity.Habitacion;
import com.staybook.entity.Reserva;
import com.staybook.repository.ClienteRepository;
import com.staybook.repository.HabitacionRepository;
import com.staybook.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HotelRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    @Test
    void debeInsertarListarActualizarYEliminarCliente() {
        Cliente cliente = Cliente.builder()
                .nombre("Sebastian")
                .apellido("Blas")
                .dni("12345678")
                .telefono("999999999")
                .correo("sebastian@example.com")
                .build();
        Cliente guardado = clienteRepository.save(cliente);
        assertThat(guardado.getId()).isNotNull();
        assertThat(clienteRepository.findAll()).hasSize(1);

        guardado.setTelefono("988888888");
        assertThat(clienteRepository.save(guardado).getTelefono()).isEqualTo("988888888");

        clienteRepository.delete(guardado);
        assertThat(clienteRepository.findAll()).isEmpty();
    }

    @Test
    void debeInsertarListarActualizarYEliminarHabitacionYReserva() {
        Cliente cliente = clienteRepository.save(Cliente.builder()
                .nombre("Ana")
                .apellido("Lopez")
                .dni("87654321")
                .telefono("977777777")
                .correo("ana@example.com")
                .build());
        Habitacion habitacion = habitacionRepository.save(Habitacion.builder()
                .numero("101")
                .tipo("Matrimonial")
                .precio(180.0)
                .disponible(true)
                .build());

        Reserva reserva = reservaRepository.save(Reserva.builder()
                .cliente(cliente)
                .habitacion(habitacion)
                .fechaEntrada(LocalDate.now())
                .fechaSalida(LocalDate.now().plusDays(2))
                .build());
        assertThat(reservaRepository.findAll()).hasSize(1);

        reserva.setFechaSalida(LocalDate.now().plusDays(3));
        assertThat(reservaRepository.save(reserva).getFechaSalida()).isEqualTo(LocalDate.now().plusDays(3));

        reservaRepository.delete(reserva);
        assertThat(reservaRepository.findAll()).isEmpty();
    }
}
