package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author ericchung
 */
public class TradeServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
    }
}
