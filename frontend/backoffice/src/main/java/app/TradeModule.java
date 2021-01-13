package app;

import app.trade.TradeAJAXServiceImpl;
import core.framework.module.Module;

/**
 * @author ericchung
 */
public class TradeModule extends Module {
    @Override
    protected void initialize() {
        api().client(TradeWebService.class, requiredProperty("app.tradeServiceURL"));
        api().service(TradeAJAXService.class, bind(TradeAJAXServiceImpl.class));
    }
}
