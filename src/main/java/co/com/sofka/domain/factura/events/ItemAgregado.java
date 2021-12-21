package co.com.sofka.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.factura.values.*;

public class ItemAgregado extends DomainEvent {
    private final ItemId id;
    private final ProductoId productoId;
    private final Nombre nombre;
    private final Cantidad cantidad;
    private final Precio precio;

    public ItemAgregado(ItemId id, ProductoId productoId, Nombre nombre, Cantidad cantidad, Precio precio) {
        super("sofka.factura.itemagregado");
        this.id = id;
        this.productoId = productoId;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public ItemId getId() {
        return id;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Cantidad getCantidad() {
        return cantidad;
    }

    public Precio getPrecio() {
        return precio;
    }
}
