package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViesVatValidatorFunctionTest {

    @Test
    public void testFunction() throws Exception {
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class);

        ViesVatValidatorClient client = server.getApplicationContext().getBean(ViesVatValidatorClient.class);

        assertEquals(client.index().blockingGet(), "vies-vat-validator");
        server.stop();
    }
}
