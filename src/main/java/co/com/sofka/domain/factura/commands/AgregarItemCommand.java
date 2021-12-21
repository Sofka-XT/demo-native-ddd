package co.com.sofka.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.factura.values.*;

public class AgregarItemCommand extends Command {
    private final FacturaId facturaId;
    private final ProductoId productoId;
    private final Nombre nombre;
    private final Cantidad cantidad;
    private final Precio precio;

    public AgregarItemCommand(FacturaId facturaId, ProductoId productoId, Nombre nombre, Cantidad cantidad, Precio precio){

        this.facturaId = facturaId;
        this.productoId = productoId;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public FacturaId getFacturaId() {
        return facturaId;
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
