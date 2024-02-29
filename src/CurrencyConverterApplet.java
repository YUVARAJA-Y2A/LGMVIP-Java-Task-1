import java.applet.Applet;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverterApplet extends Applet implements ActionListener {
    TextField amountField, resultField;
    Button convertButton;

    public void init() {
        amountField = new TextField(10);
        resultField = new TextField(10);
        resultField.setEditable(false);
        convertButton = new Button("Convert");

        add(new Label("Amount:"));
        add(amountField);
        add(new Label("Result:"));
        add(resultField);
        add(convertButton);

        convertButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            convertCurrency();
        }
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = "USD";  // Replace with actual values from dropdown or input
            String toCurrency = "INR";   // Replace with actual values from dropdown or input

            // Call the Servlet using Ajax
            URL url = new URL("http://localhost:8080/your-web-app/CurrencyConverterServlet" +
                    "?amount=" + amount + "&fromCurrency=" + fromCurrency + "&toCurrency=" + toCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = reader.readLine();

            resultField.setText(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
