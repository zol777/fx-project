package app.trade;

import app.TradeAJAXService;
import app.TradeWebService;
import core.framework.inject.Inject;

import java.util.stream.Collectors;

/**
 * @author ericchung
 */
public class TradeAJAXServiceImpl implements TradeAJAXService {
    @Inject
    TradeWebService service;

    @Override
    public GetTradeAJAXResponse get() {
        var response = new GetTradeAJAXResponse();
        response.trades = service.get().trades.stream().map(this::trade).collect(Collectors.toList());
        return response;
    }

    public GetTradeAJAXResponse.Trade trade(TradeView view) {
        return new GetTradeAJAXResponse.Trade();
    }
}
