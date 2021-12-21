package co.com.sofka.usecase;

import co.com.sofka.domain.factura.values.FacturaId;

public interface SendEmailService {
    boolean send(FacturaId facturaId, String email, String body);
}
