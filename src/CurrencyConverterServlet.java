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

        // Mock exchange rates (replace with real-time rates)
        double usdToInrRate = 73.5;
        double eurToInrRate = 88.2;

        double result = convertCurrency(amount, fromCurrency, toCurrency, usdToInrRate, eurToInrRate);

        response.getWriter().write(String.valueOf(result));
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency, double... rates) {
        // Convert to a common currency (INR in this example)
        double inrAmount = amount / rates[getIndex(fromCurrency)];
        // Convert to the target currency
        return inrAmount * rates[getIndex(toCurrency)];
    }

    private int getIndex(String currency) {
        // Mapping currencies to array indices
        switch (currency) {
            case "USD":
                return 0;
            case "EUR":
                return 1;
            // Add more currencies as needed
            default:
                throw new IllegalArgumentException("Invalid currency");
        }
    }
}
