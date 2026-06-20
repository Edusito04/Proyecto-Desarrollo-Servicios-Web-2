package com.staybook;

import com.staybook.entity.EstadoPago;
import com.staybook.entity.MetodoPago;
import com.staybook.entity.Pago;
import com.staybook.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PagoRepositoryTest {
    @Autowired
    private PagoRepository repository;

    @Test
    void debeInsertarListarActualizarYEliminarPago() {
        Pago pago = Pago.builder()
                .reservaId(1L)
                .monto(new BigDecimal("250.00"))
                .metodoPago(MetodoPago.YAPE)
                .estadoPago(EstadoPago.PAGADO)
                .fechaPago(LocalDate.now())
                .observacion("Pago inicial")
                .build();

        Pago guardado = repository.save(pago);
        assertThat(guardado.getId()).isNotNull();

        assertThat(repository.findAll()).hasSize(1);
        assertThat(repository.findByReservaId(1L)).hasSize(1);

        guardado.setMonto(new BigDecimal("300.00"));
        repository.save(guardado);
        assertThat(repository.findById(guardado.getId()).orElseThrow().getMonto())
                .isEqualByComparingTo("300.00");

        repository.delete(guardado);
        assertThat(repository.findAll()).isEmpty();
    }
}
