package co.com.sofka.domain.factura;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.factura.events.FacturaCreada;
import co.com.sofka.domain.factura.events.ItemAgregado;
import co.com.sofka.domain.factura.events.ItemQuitado;

import java.util.HashSet;

public class FacturaChange extends EventChange {
    public FacturaChange(Factura factura) {
        apply((FacturaCreada event) -> {
            factura.fecha = event.getFecha();
            factura.items = new HashSet<>();
            factura.clienteId = event.getClienteId();
        });

        apply((ItemAgregado event) -> {
            factura.items.add(new Item(
                    event.getId(),
                    event.getProductoId(),
                    event.getNombre(),
                    event.getCantidad(),
                    event.getPrecio()
            ));
        });

        apply((ItemQuitado event) -> {
            var itemId = event.getItemId();
            factura.items.removeIf(item -> item.identity().equals(itemId));
        });
    }
}
