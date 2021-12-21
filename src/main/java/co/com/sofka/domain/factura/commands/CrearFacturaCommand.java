package co.com.sofka.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.factura.values.ClienteId;
import co.com.sofka.domain.factura.values.FacturaId;
import co.com.sofka.domain.factura.values.Fecha;

public class CrearFacturaCommand extends Command {
    private final FacturaId facturaId;
    private final ClienteId clienteId;
    private final Fecha fecha;

    public CrearFacturaCommand(FacturaId facturaId, ClienteId clienteId, Fecha fecha){

        this.facturaId = facturaId;
        this.clienteId = clienteId;
        this.fecha = fecha;
    }


    public FacturaId getFacturaId() {
        return facturaId;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
