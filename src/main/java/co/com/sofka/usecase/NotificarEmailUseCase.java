package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.factura.events.FacturaCreada;
import co.com.sofka.domain.factura.values.FacturaId;

import java.util.List;

public class NotificarEmailUseCase extends UseCase<TriggeredEvent<FacturaCreada>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<FacturaCreada> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();

        var service = getService(SendEmailService.class).orElseThrow();
        boolean isValid = service.send(
                FacturaId.of(event.aggregateRootId()),
                "support@factura-sofka.com.co",//TODO: sacar el email de cliente por medio de un servicio de dominio
                "Nueva factura con el cliente: "+event.getClienteId().value()
        );
        if(!isValid){
            throw new BusinessException(event.aggregateRootId(), "No se pudo enviar el correo");
        }
        emit().onResponse(new ResponseEvents(List.of()));
    }



}
