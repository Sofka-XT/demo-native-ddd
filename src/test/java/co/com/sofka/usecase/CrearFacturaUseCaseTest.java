package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.factura.commands.CrearFacturaCommand;
import co.com.sofka.domain.factura.events.FacturaCreada;
import co.com.sofka.domain.factura.values.ClienteId;
import co.com.sofka.domain.factura.values.FacturaId;
import co.com.sofka.domain.factura.values.Fecha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

class CrearFacturaUseCaseTest {

    @Test
    public void crearFactura(){

        //arrange
        FacturaId facturaId = FacturaId.of("xxxxxx");
        ClienteId clienteId = ClienteId.of("yyyy");
        Fecha fecha = new Fecha(new Date());
        var command = new CrearFacturaCommand(facturaId, clienteId, fecha);
        var usecase = new CrearFacturaUseCase();

        //act
        var events = UseCaseHandler.getInstance()
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //asserts
        FacturaCreada event = (FacturaCreada)events.getDomainEvents().get(0);
        Assertions.assertEquals( "xxxxxx", event.aggregateRootId());
        Assertions.assertEquals( "yyyy", event.getClienteId().value());

    }

    @Test
    public void crearFactura_problemaConFecha(){

        //arrange
        FacturaId facturaId = FacturaId.of("xxxx");
        ClienteId clienteId = ClienteId.of("yyyy");
        var calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.DECEMBER, 25);
        Fecha fecha = new Fecha(calendar.getTime());
        var command = new CrearFacturaCommand(facturaId, clienteId, fecha);
        var usecase = new CrearFacturaUseCase();


        //act
        var message = Assertions.assertThrows(BusinessException.class, () -> {
            var events = UseCaseHandler.getInstance()
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        Assertions.assertEquals("No puede crear la factutas en fines de semana", message);



    }

}