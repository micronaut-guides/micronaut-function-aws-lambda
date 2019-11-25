package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest // <1>
public class InvoiceControllerTest {

    @Inject
    @Client("/")
    RxHttpClient rxHttpClient; // <2>

    @Inject
    EmbeddedServer server; // <3>

    @Test
    public void testBooksController() {

        VatValidator bean = server.getApplicationContext().getBean(VatValidator.class);
        assertTrue(bean instanceof VatValidatorMock); // <4>

        List<InvoiceLine> lines = new ArrayList<InvoiceLine>();
        lines.add(new InvoiceLine("1491950358", 2, new BigDecimal(19.99)));
        lines.add(new InvoiceLine("1680502395", 1, new BigDecimal(15)));
        Invoice invoice = new Invoice("B84965375", "es", lines);
        HttpRequest request = HttpRequest.POST("/invoice/vat", invoice);
        Taxes rsp = rxHttpClient.toBlocking().retrieve(request, Taxes.class);
        BigDecimal expected = new BigDecimal("11.55");
        assertEquals(expected, rsp.getVat());

        invoice.setVatNumber("B99999999");
        rsp = rxHttpClient.toBlocking().retrieve(request, Taxes.class);
        expected = new BigDecimal("0.00");
        assertEquals(expected, rsp.getVat());
    }
}
