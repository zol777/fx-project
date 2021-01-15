package app;

import app.service.TradeService;
import app.web.TradeWebServiceImpl;
import core.framework.module.App;
import core.framework.module.SystemModule;

import java.util.concurrent.TimeUnit;

/**
 * @author ericchung
 */
public class TradeServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        loadProperties("app.properties");

        int maxTrade = Integer.parseInt(property("app.trade.max").orElse("500"));
        int maxPollSize = Integer.parseInt(property("app.pollSize.max").orElse("5"));

        bind(new TradeService(maxTrade, maxPollSize));

        http().limitRate().add("create-trade", 100, 1, TimeUnit.MINUTES);

        api().service(TradeWebService.class, bind(TradeWebServiceImpl.class));
    }
}
