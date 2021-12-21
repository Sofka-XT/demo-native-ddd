package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.factura.Factura;
import co.com.sofka.domain.factura.commands.CrearFacturaCommand;

import java.util.Calendar;

public class CrearFacturaUseCase extends UseCase<RequestCommand<CrearFacturaCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CrearFacturaCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var calendar = Calendar.getInstance();

        calendar.setTime(command.getFecha().value());
        var dayOFWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOFWeek == Calendar.SATURDAY || dayOFWeek == Calendar.SUNDAY){
           throw new BusinessException(command.getFacturaId().value(), "No puede crear la factutas en fines de semana");
        }

        var factura = new Factura(command.getFacturaId(), command.getClienteId(), command.getFecha());

        factura.items().forEach(System.out::println);
        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
