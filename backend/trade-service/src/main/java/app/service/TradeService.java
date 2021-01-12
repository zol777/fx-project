package app.service;

import app.domain.Trade;
import app.trade.CreateTradeRequest;
import core.framework.web.exception.BadRequestException;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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

    final BlockingQueue<Trade> trades;

    public TradeService(int maxTrade) {
        trades = new LinkedBlockingQueue<>(maxTrade); // adapt to cloud compute instance memory config
    }

    public void create(CreateTradeRequest request) {
        validateCurrency(request);
        validateCountry(request);

        try {
            trades.put(trade(request));
        } catch (InterruptedException e) {
            throw new Error(e);
        }
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
