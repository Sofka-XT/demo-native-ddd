package co.com.sofka.factura;

import co.com.sofka.factura.values.*;
import co.com.sofka.generico.Entity;

import java.util.Objects;

public class Item extends Entity<ItemId> {

    private final ProductoId productoId;
    private Cantidad cantidad;
    private Precio precio;
    private Nombre nombre;

    public Item(ItemId itemId, ProductoId productoId, Nombre nombre, Cantidad cantidad, Precio precio) {
        super(itemId);
        this.productoId = Objects.requireNonNull(productoId);
        this.nombre = Objects.requireNonNull(nombre);
        this.cantidad  = Objects.requireNonNull(cantidad);
        this.precio = Objects.requireNonNull(precio);
    }

    public void agregarCantidad(Cantidad cantidad){
        this.cantidad = Objects.requireNonNull(cantidad);
    }

    public void aplicarDescuento(Descuento descuento){
        Objects.requireNonNull(descuento);
        this.precio = this.precio.aplicarDescuento(descuento);
    }

    public Cantidad cantidad() {
        return cantidad;
    }

    public Precio precio() {
        return precio;
    }

    public Nombre nombre() {
        return nombre;
    }

    public ProductoId productoId() {
        return productoId;
    }
}
