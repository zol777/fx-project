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
    TradeWebService tradeWebService;

    @Override
    public GetTradeAJAXResponse get() {
        var response = new GetTradeAJAXResponse();
        response.trades = tradeWebService.get().trades.stream().map(this::trade).collect(Collectors.toList());
        return response;
    }

    public GetTradeAJAXResponse.Trade trade(TradeView view) {
        var trade = new GetTradeAJAXResponse.Trade();
        trade.id = view.id;
        trade.userId = view.userId;
        trade.currencyFrom = view.currencyFrom;
        trade.currencyTo = view.currencyTo;
        trade.amountSell = view.amountSell;
        trade.amountBuy = view.amountBuy;
        trade.rate = view.rate;
        trade.timePlaced = view.timePlaced;
        trade.originatingCountry = view.originatingCountry;
        return trade;
    }
}
