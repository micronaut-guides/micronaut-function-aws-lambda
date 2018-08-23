package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvoiceControllerTest {

    private static EmbeddedServer server;
    private static RxHttpClient rxHttpClient;

    @BeforeClass // <1>
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        rxHttpClient = server
                .getApplicationContext()
                .createBean(RxHttpClient.class, server.getURL());
    }

    @AfterClass // <1>
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (rxHttpClient != null) {
            rxHttpClient.stop();
        }
    }

    @Test
    public void testBooksController() {
        List<InvoiceLine> lines = new ArrayList<InvoiceLine>();
        lines.add(new InvoiceLine("1491950358", 2, new BigDecimal(19.99)));
        lines.add(new InvoiceLine("1680502395", 1, new BigDecimal(15)));
        Invoice invoice = new Invoice("B84965375", "es", lines);
        HttpRequest request = HttpRequest.POST("/invoice/vat", invoice);
        BigDecimal rsp = rxHttpClient.toBlocking().retrieve(request, BigDecimal.class);
        BigDecimal expected = new BigDecimal("11.55");
        rsp = rsp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expected, rsp);


        invoice.setVatNumber("B99999999");
        rsp = rxHttpClient.toBlocking().retrieve(request, BigDecimal.class);
        expected = new BigDecimal("0");
        rsp = rsp.setScale(0, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(expected, rsp);
    }
}
