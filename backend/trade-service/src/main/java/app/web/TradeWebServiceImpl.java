package app.web;

import app.TradeWebService;
import app.service.TradeService;
import app.trade.CreateTradeRequest;
import app.trade.GetTradeResponse;
import core.framework.inject.Inject;
import core.framework.web.rate.LimitRate;

/**
 * @author ericchung
 */
public class TradeWebServiceImpl implements TradeWebService {
    @Inject
    TradeService service;

    @LimitRate("create-trade") // fail fast instead of timeout internally because long polling may block the queue
    @Override
    public void create(CreateTradeRequest request) {
        service.create(request);
    }

    @Override
    public GetTradeResponse get() {
        return service.get();
    }
}
