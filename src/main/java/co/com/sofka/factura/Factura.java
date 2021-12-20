package co.com.sofka.factura;

import co.com.sofka.factura.events.ItemAgregado;
import co.com.sofka.factura.values.*;
import co.com.sofka.generico.AggregateRoot;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Factura extends AggregateRoot<FacturaId> {

    private final Fecha fecha;
    private final ClienteId clienteId;

    private Set<Item> items;
    private Descuento descuento;

    public Factura(FacturaId facturaId,ClienteId clienteId, Fecha fecha){
        super(facturaId);
        this.fecha = Objects.requireNonNull(fecha);
        this.clienteId = Objects.requireNonNull(clienteId, "El cliente id es requerido");
        this.items = new HashSet<>();
        this.applyChange(new FacturaCreada(clienteId, fecha));

    }

    public void agregarItem(ProductoId productoId, Nombre nombre, Cantidad cantidad, Precio precio){
        var id = new ItemId();
        items.add(new Item(id, productoId, nombre, cantidad, precio));
        this.applyChange(new ItemAgregado(id, productoId, nombre, cantidad, precio));
    }

    public void quitarItem(ItemId itemId){
        items.removeIf(item -> item.getId().equals(itemId));
        this.applyChange(new ItemQuitado(itemId));
    }

    public void descuentoATodosLosItems(Descuento descuento){
        this.descuento = descuento;
        items.forEach(item ->{
            item.aplicarDescuento(descuento);
            this.applyChange(new DescuentoAplicadoParaItem(item.getId(), descuento));
        });
    }


    public void aplicarDescuentoAProducto(ProductoId productoId,Descuento descuento){
        items.stream()
                .filter(item -> item.productoId().equals(productoId))
                .forEach(item -> item.aplicarDescuento(descuento));
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
