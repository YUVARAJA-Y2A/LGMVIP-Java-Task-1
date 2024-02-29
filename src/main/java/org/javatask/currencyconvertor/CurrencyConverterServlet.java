package org.javatask.currencyconvertor;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CurrencyConverterServlet")
public class CurrencyConverterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double amount = Double.parseDouble(request.getParameter("amount"));
        String fromCurrency = request.getParameter("fromCurrency");
        String toCurrency = request.getParameter("toCurrency");

        double result = convertCurrency(amount, fromCurrency, toCurrency);

        response.getWriter().write(String.valueOf(result));
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double conversionRate = ConversionRates.getConversionRate(fromCurrency, toCurrency);
        return amount * conversionRate;
    }
}
