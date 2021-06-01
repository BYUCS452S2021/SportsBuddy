package Handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class BaseHandler {
    private static final String POST = "post";
    private static final String GET = "get";

    protected boolean isPostRequest(final String requestMethod) {
        return requestMethod.equalsIgnoreCase(POST);
    }
    protected boolean isGetRequest(final String requestMethod) {
        return requestMethod.equalsIgnoreCase(GET);
    }

    protected void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
