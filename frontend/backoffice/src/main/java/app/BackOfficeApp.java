package app;

import app.web.HomeController;
import app.web.Webpage;
import core.framework.http.HTTPMethod;
import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author ericchung
 */
public class BackOfficeApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));

        site().staticContent("/static");

        var controller = new HomeController(bind(Webpage.class));
        http().route(HTTPMethod.GET, "/", controller);
    }
}
