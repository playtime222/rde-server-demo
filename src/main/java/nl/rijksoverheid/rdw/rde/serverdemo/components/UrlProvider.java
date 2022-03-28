package nl.rijksoverheid.rdw.rde.serverdemo.components;

import org.springframework.stereotype.Component;

@Component
public class UrlProvider {

    //TODO Get base url from settings
    private static String _BaseUrl = "http://192.168.178.12:8080";

    public String BuildDocumentEnrollmentUrl(String enrollmentId)
    {
        return _BaseUrl + "/" + enrollmentId;
    }

    public static final String ApiMessage = _BaseUrl + "/api/message/";

    public String getMessageUrl(long id)
    {
        return ApiMessage + id;
    }
}
