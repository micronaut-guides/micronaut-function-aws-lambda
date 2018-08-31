package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViesVatValidatorFunctionTest {

    @Test
    public void testViesVatValidatorFunction() throws Exception {
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class);

        ViesVatValidatorClient client = server.getApplicationContext().getBean(ViesVatValidatorClient.class);

        VatValidationRequest req = new VatValidationRequest("es", "B99286353");
        assertTrue(client.apply(req).blockingGet().isValid());
        req = new VatValidationRequest("es", "B19280031");
        assertTrue(client.apply(req).blockingGet().isValid());
        req = new VatValidationRequest("es", "XXXXXXXXX");
        assertFalse(client.apply(req).blockingGet().isValid());

        server.stop();
    }
}
