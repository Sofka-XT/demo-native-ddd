package co.com.sofka.factura.commands;

import co.com.sofka.factura.values.Descuento;
import co.com.sofka.factura.values.FacturaId;

public class DescontarATodosLosItems {
    private final Descuento descuento;
    private final FacturaId facturaId;


    public DescontarATodosLosItems(Descuento descuento, FacturaId facturaId) {
        this.descuento = descuento;
        this.facturaId = facturaId;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public Descuento getDescuento() {
        return descuento;
    }

}
