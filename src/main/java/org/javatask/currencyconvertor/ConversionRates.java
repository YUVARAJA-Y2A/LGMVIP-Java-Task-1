package org.javatask.currencyconvertor;

import java.util.HashMap;
import java.util.Map;

public class ConversionRates {
    private static final Map<String, Map<String, Double>> rates = new HashMap<>();

    static {
        initializeConversionRates();
    }

    private static void initializeConversionRates() {
        // From USD
        addConversionRate("USD", "INR", 73.5);
        addConversionRate("USD", "EUR", 0.85);
        addConversionRate("USD", "GBP", 0.73);
        addConversionRate("USD", "JPY", 110.0);

        // From EUR
        addConversionRate("EUR", "INR", 88.2);
        addConversionRate("EUR", "USD", 1.18);
        addConversionRate("EUR", "GBP", 0.86);
        addConversionRate("EUR", "JPY", 130.0);

        // From GBP
        addConversionRate("GBP", "INR", 100.0);
        addConversionRate("GBP", "USD", 1.37);
        addConversionRate("GBP", "EUR", 1.16);
        addConversionRate("GBP", "JPY", 150.0);

        // From JPY
        addConversionRate("JPY", "INR", 0.70);
        addConversionRate("JPY", "USD", 0.0091);
        addConversionRate("JPY", "EUR", 0.0077);
        addConversionRate("JPY", "GBP", 0.0067);

        // From INR
        addConversionRate("INR", "EUR", 0.70);
        addConversionRate("INR", "USD", 0.0091);
        addConversionRate("INR", "GBP", 0.0077);
        addConversionRate("INR", "JPY", 0.0067);

    }

    private static void addConversionRate(String fromCurrency, String toCurrency, double rate) {
        rates.computeIfAbsent(fromCurrency, k -> new HashMap<>()).put(toCurrency, rate);
    }

    public static double getConversionRate(String fromCurrency, String toCurrency) {
        if (!rates.containsKey(fromCurrency) || !rates.get(fromCurrency).containsKey(toCurrency)) {
            throw new IllegalArgumentException("Conversion rate not available");
        }
        return rates.get(fromCurrency).get(toCurrency);
    }
}
