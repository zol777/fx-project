package app.trade;

import app.TradeAJAXService;
import app.TradeWebService;
import core.framework.inject.Inject;
import core.framework.util.ASCII;
import core.framework.util.Strings;

import java.util.stream.Collectors;

/**
 * @author ericchung
 */
public class TradeAJAXServiceImpl implements TradeAJAXService {
    private final static String COUNTRY_FLAG_IMAGE_STYLE = "flat";
    private final static String COUNTRY_FLAG_IMAGE_SIZE = "16";

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
        trade.countryFlagImageURL = countryFlagImageURL(view.originatingCountry);
        return trade;
    }

    String countryFlagImageURL(String countryCode) { // refer to https://www.countryflags.io/
        return Strings.format("https://www.countryflags.io/{}/{}/{}.png", ASCII.toLowerCase(countryCode), COUNTRY_FLAG_IMAGE_STYLE, COUNTRY_FLAG_IMAGE_SIZE);
    }
}
