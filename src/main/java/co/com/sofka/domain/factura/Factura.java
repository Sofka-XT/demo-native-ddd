package co.com.sofka.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.factura.events.FacturaCreada;
import co.com.sofka.domain.factura.events.ItemAgregado;
import co.com.sofka.domain.factura.events.ItemQuitado;
import co.com.sofka.domain.factura.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Factura extends AggregateEvent<FacturaId> {

    protected Fecha fecha;
    protected ClienteId clienteId;
    protected Set<Item> items;
    protected Descuento descuento;

    public Factura(FacturaId facturaId,ClienteId clienteId, Fecha fecha){
        super(facturaId);
        subscribe(new FacturaChange(this));
        appendChange(new FacturaCreada(clienteId, fecha)).apply();
    }

    private Factura(FacturaId facturaId){
        super(facturaId);
        subscribe(new FacturaChange(this));
    }

    public static Factura from(FacturaId facturaId, List<DomainEvent> events) {
        var factura = new Factura(facturaId);
        events.forEach(factura::applyEvent);
        return factura;
    }

    public void agregarItem(ProductoId productoId, Nombre nombre, Cantidad cantidad, Precio precio){
        var id = new ItemId();
        appendChange(new ItemAgregado(id, productoId, nombre, cantidad, precio)).apply();
    }

    public void quitarItem(ItemId itemId){
        appendChange(new ItemQuitado(itemId)).apply();
    }


    public Set<Item> items() {
        return items;
    }

    public ClienteId clienteId() {
        return clienteId;
    }

    public Fecha fecha() {
        return fecha;
    }


}
