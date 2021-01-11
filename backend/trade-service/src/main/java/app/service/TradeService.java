package app.service;

import app.trade.CreateTradeRequest;
import core.framework.web.exception.BadRequestException;

import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author ericchung
 */
public class TradeService {
    private static final Set<String> CURRENCY_CODES = new HashSet<>();
    private static final Set<String> COUNTRY_CODES;

    static {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        for (Currency currency : currencies) {
            CURRENCY_CODES.add(currency.getCurrencyCode());
        }

        COUNTRY_CODES = Set.of(Locale.getISOCountries());
    }

    public void create(CreateTradeRequest request) {
        validateCurrency(request);
        validateCountry(request);
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
}
