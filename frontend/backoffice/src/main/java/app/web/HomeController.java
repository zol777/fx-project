package app.web;

import core.framework.http.ContentType;
import core.framework.web.Controller;
import core.framework.web.Request;
import core.framework.web.Response;


/**
 * @author ericchung
 */
public class HomeController implements Controller {
    private final byte[] indexPage;

    public HomeController(WebPages pages) {
        indexPage = pages.get("/index.html");
    }

    @Override
    public Response execute(Request request) {
        return Response.bytes(indexPage)
                       .contentType(ContentType.TEXT_HTML);
    }
}
