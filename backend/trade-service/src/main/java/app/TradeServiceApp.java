package app;

import app.service.TradeService;
import app.web.TradeWebServiceImpl;
import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author ericchung
 */
public class TradeServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        loadProperties("app.properties");

        int maxTrade = Integer.parseInt(property("app.maxTrade").orElse("100")); // 100 is set for demo purpose
        bind(new TradeService(maxTrade));

        api().service(TradeWebService.class, bind(TradeWebServiceImpl.class));
    }
}
