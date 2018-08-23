package example.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.Client;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;

import javax.inject.Singleton;
import java.util.concurrent.Future;

@Singleton
public class VatService {
    private static final String PATH = "/taxation_customs/vies/services/checkVatService";
    private static final String VALID_XML_OPEN_TAG = "<valid>";
    private static final String VALID_XML_CLOSE_TAG = "</valid>";

    protected final RxHttpClient client;

    public VatService(@Client("http://ec.europa.eu") RxHttpClient client) {
        this.client = client;
    }

    public Future<Boolean> validateVat(String memberStateCode, String vatNumberCode) {
        String soapEnvelope = soapEnvelope(memberStateCode, vatNumberCode);
        HttpRequest request = HttpRequest.POST("http://ec.europa.eu"+PATH, soapEnvelope)
                .contentType("application/soap+xml");

        Flowable<String> response = client.retrieve(request);
        return response.map(str -> parseResponseToBoolean(str) )
                .toFuture();
    }

    private Boolean parseResponseToBoolean(String response) {
        if (!response.contains(VALID_XML_OPEN_TAG) || !response.contains(VALID_XML_CLOSE_TAG)) {
            return false;
        }
        int beginIndex = response.indexOf(VALID_XML_OPEN_TAG) + VALID_XML_OPEN_TAG.length();
        String validResponse = response.substring(beginIndex, response.indexOf(VALID_XML_CLOSE_TAG));
        return Boolean.valueOf(validResponse);
    }

    private String soapEnvelope(String memberStateCode, String vatNumberCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sb.append("<soapenv:Header/>");
        sb.append("<soapenv:Body xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sb.append("<urn:checkVat xmlns:urn=\"urn:ec.europa.eu:taxud:vies:services:checkVat:types\">");
        sb.append("<urn:countryCode>");
        sb.append(memberStateCode);
        sb.append("</urn:countryCode>");
        sb.append("<urn:vatNumber>");
        sb.append(vatNumberCode);
        sb.append("</urn:vatNumber>");
        sb.append("</urn:checkVat>");
        sb.append("</soapenv:Body>");
        sb.append("</soapenv:Envelope>");
        return sb.toString();
    }
}