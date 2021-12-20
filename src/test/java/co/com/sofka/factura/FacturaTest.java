package co.com.sofka.factura;

import co.com.sofka.factura.events.ItemAgregado;
import co.com.sofka.factura.values.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;


class FacturaTest {

    @Test
    void crearFactura(){
        var id = new FacturaId();
        var clientId = ClienteId.of("xxxx-xxxx");
        var fecha = new Fecha(new Date());
        var factura = new Factura(id, clientId, fecha);

        assert Objects.nonNull(factura.clienteId());
        assert Objects.nonNull(factura.fecha());
        assert Objects.nonNull(factura.getId());
    }

    @Test
    void agregarItem() {
        var id = new FacturaId();
        var clientId = ClienteId.of("xxxx-xxxx");
        var fecha = new Fecha(new Date());
        var factura = new Factura(id, clientId, fecha);

        factura.agregarItem(
                ProductoId.of("xxxx"),
                new Nombre(),
                new Cantidad(10000D),
                new Precio(300D)
        );

        assert factura.items().size() > 0;
    }

    @Test
    void quitarItem() {
        var id = new FacturaId();
        var clientId = ClienteId.of("xxxx-xxxx");
        var fecha = new Fecha(new Date());
        var factura = new Factura(id, clientId, fecha);

        factura.agregarItem(
                ProductoId.of("xxxx"),
                new Nombre(),
                new Cantidad(10000D),
                new Precio(300D)
        );

        var event =  (ItemAgregado)factura.getChanges().stream().findFirst().orElseThrow();
        factura.quitarItem(event.getId());

        assert factura.items().size() == 0;
    }

    @Test
    void descuentoATodosLosItems() {
    }

    @Test
    void aplicarDescuentoAProducto() {
    }
}