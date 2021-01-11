package app.service;

import app.trade.CreateTradeRequest;
import core.framework.web.exception.BadRequestException;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ericchung
 */
public class TradeService {
    private static final Set<String> AVAILABLE_CURRENCY_CODES;
    private static final Set<String> AVAILABLE_COUNTRY_CODES;

    static {
        AVAILABLE_CURRENCY_CODES = Currency.getAvailableCurrencies()
                                           .stream()
                                           .map(Currency::getCurrencyCode)
                                           .collect(Collectors.toUnmodifiableSet());

        AVAILABLE_COUNTRY_CODES = Set.of(Locale.getISOCountries());
    }

    public void create(CreateTradeRequest request) {
        validateCurrency(request);
        validateCountry(request);
    }

    void validateCurrency(CreateTradeRequest request) {
        if (!AVAILABLE_CURRENCY_CODES.contains(request.currencyFrom))
            throw new BadRequestException("unsupported currencyFrom, value=" + request.currencyFrom);
        if (!AVAILABLE_CURRENCY_CODES.contains(request.currencyTo))
            throw new BadRequestException("unsupported currencyTo, value=" + request.currencyTo);
    }

    void validateCountry(CreateTradeRequest request) {
        if (!AVAILABLE_COUNTRY_CODES.contains(request.originatingCountry))
            throw new BadRequestException("unsupported originatingCountry, value=" + request.originatingCountry);
    }
}
