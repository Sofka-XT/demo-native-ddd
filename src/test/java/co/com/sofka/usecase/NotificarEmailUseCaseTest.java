package co.com.sofka.usecase;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.factura.events.FacturaCreada;
import co.com.sofka.domain.factura.values.ClienteId;
import co.com.sofka.domain.factura.values.FacturaId;
import co.com.sofka.domain.factura.values.Fecha;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificarEmailUseCaseTest {
    @Mock
    SendEmailService sendEmailService;

    @Test
    void enviarCorreoACliente(){
        ClienteId clienteId = ClienteId.of("ssss");
        FacturaId facturaId = FacturaId.of("fffff");
        Fecha fecha = new Fecha(new Date());
        var event = new FacturaCreada( clienteId,  fecha);
        event.setAggregateRootId(facturaId.value());
        var usecase = new NotificarEmailUseCase();
        when(sendEmailService.send(
                facturaId,
                "support@factura-sofka.com.co",
                "Nueva factura con el cliente: "+clienteId.value()
        )).thenReturn(true);
        ServiceBuilder builder = new ServiceBuilder();
        builder.addService(sendEmailService);
        usecase.addServiceBuilder(builder);

         UseCaseHandler.getInstance()
                .syncExecutor(usecase, new TriggeredEvent<>(event))
                .orElseThrow();

        verify(sendEmailService).send(facturaId,
                "support@factura-sofka.com.co",
                "Nueva factura con el cliente: "+clienteId.value());

    }
}