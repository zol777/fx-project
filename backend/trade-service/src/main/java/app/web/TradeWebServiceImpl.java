package app.web;

import app.TradeWebService;
import app.service.TradeService;
import app.trade.CreateTradeRequest;
import app.trade.GetTradeResponse;
import core.framework.inject.Inject;

/**
 * @author ericchung
 */
public class TradeWebServiceImpl implements TradeWebService {
    @Inject
    TradeService service;

    @Override
    public void create(CreateTradeRequest request) {
        service.create(request);
    }

    @Override
    public GetTradeResponse get() {
        return service.get();
    }
}
