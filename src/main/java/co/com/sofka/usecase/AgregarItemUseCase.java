package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.factura.Factura;
import co.com.sofka.domain.factura.commands.AgregarItemCommand;

public class AgregarItemUseCase extends UseCase<RequestCommand<AgregarItemCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarItemCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        if(factura.items().size() == 5){
            throw new BusinessException(command.getFacturaId().value(), "No puede tener mas items");
        }
        factura.agregarItem(command.getProductoId(), command.getNombre(), command.getCantidad(), command.getPrecio());

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
