package app;

import app.web.HomeController;
import app.web.WebPages;
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
        loadProperties("app.properties");

        configureHomePage();

        load(new TradeModule());
    }

    private void configureHomePage() {
        site().staticContent("/static");

        var controller = new HomeController(bind(WebPages.class));
        http().route(HTTPMethod.GET, "/", controller);
    }
}
