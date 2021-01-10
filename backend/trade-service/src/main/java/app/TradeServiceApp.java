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

        bind(TradeService.class);
        api().service(TradeWebService.class, bind(TradeWebServiceImpl.class));
    }
}
