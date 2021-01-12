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

        int maxTrade = Integer.parseInt(property("app.trade.max").orElse("100")); // 100 is set for demo purpose
        int maxPollSize = Integer.parseInt(property("app.pollSize.max").orElse("2")); // 2 is set for demo purpose

        bind(new TradeService(maxTrade, maxPollSize));

        api().service(TradeWebService.class, bind(TradeWebServiceImpl.class));
    }
}
