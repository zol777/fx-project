package app.service;

import app.domain.Trade;
import app.trade.CreateTradeRequest;
import app.trade.GetTradeResponse;
import app.trade.TradeView;
import core.framework.web.exception.BadRequestException;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author ericchung
 */
public class TradeService {
    private static final Set<String> CURRENCY_CODES;
    private static final Set<String> COUNTRY_CODES;

    static {
        CURRENCY_CODES = Currency.getAvailableCurrencies()
                                 .stream()
                                 .map(Currency::getCurrencyCode)
                                 .collect(Collectors.toUnmodifiableSet());

        COUNTRY_CODES = Set.of(Locale.getISOCountries());
    }

    private final Trades trades;

    public TradeService(int maxTrade, int maxPollSize) { // adapt to cloud compute instance memory config
        trades = new Trades(maxTrade, maxPollSize);
    }

    public void create(CreateTradeRequest request) {
        validateCurrency(request);
        validateCountry(request);

        trades.add(trade(request));
    }

    public GetTradeResponse get() {
        var response = new GetTradeResponse();
        response.trades = trades.get().stream().map(this::view).collect(Collectors.toList());
        return response;
    }

    TradeView view(Trade trade) {
        var view = new TradeView();
        view.id = trade.id;
        view.userId = trade.userId;
        view.currencyFrom = trade.currencyFrom;
        view.currencyTo = trade.currencyTo;
        view.amountSell = trade.amountSell;
        view.amountBuy = trade.amountBuy;
        view.rate = trade.rate;
        view.timePlaced = trade.timePlaced;
        view.originatingCountry = trade.originatingCountry;
        return view;
    }

    void validateCurrency(CreateTradeRequest request) {
        if (!CURRENCY_CODES.contains(request.currencyFrom))
            throw new BadRequestException("unsupported currencyFrom, value=" + request.currencyFrom);
        if (!CURRENCY_CODES.contains(request.currencyTo))
            throw new BadRequestException("unsupported currencyTo, value=" + request.currencyTo);
    }

    void validateCountry(CreateTradeRequest request) {
        if (!COUNTRY_CODES.contains(request.originatingCountry))
            throw new BadRequestException("unsupported originatingCountry, value=" + request.originatingCountry);
    }

    Trade trade(CreateTradeRequest request) {
        var trade = new Trade();
        trade.id = UUID.randomUUID().toString();
        trade.userId = request.userId;
        trade.currencyFrom = request.currencyFrom;
        trade.currencyTo = request.currencyTo;
        trade.amountSell = request.amountSell;
        trade.amountBuy = request.amountBuy;
        trade.rate = request.rate;
        trade.timePlaced = request.timePlaced;
        trade.originatingCountry = request.originatingCountry;
        return trade;
    }
}
