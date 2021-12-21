package co.com.sofka.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.factura.values.ClienteId;
import co.com.sofka.domain.factura.values.Fecha;

public class FacturaCreada extends DomainEvent {
    private final ClienteId clienteId;
    private final Fecha fecha;

    public FacturaCreada(ClienteId clienteId, Fecha fecha) {
        super("sofka.factura.facturacreada");
        this.clienteId = clienteId;
        this.fecha = fecha;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
