package main;

import main.exceptions.ExitException;
import main.filehandler.CurrenciesXMLParser;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    private static Map<String, Double> currenciesRates;

    private static void prepareData(String filename) {
        CurrenciesXMLParser parser = new CurrenciesXMLParser();
        try {
            currenciesRates = parser.parse(filename);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof IOException) {
                throw new ExitException("File " + filename + " has not been found");
            }
            throw new ExitException("File " + filename + " has unexpected format");
        }
    }

    private static boolean isCurrencyArgValid(String currencyArg) {
        return currenciesRates.containsKey(currencyArg);
    }
    public static void main(String[] args) {
        double eurAmount;
        String filename = args.length > 1 ? args[0] : "currencies.xml";
        System.out.println("Initializing calculator data...");
        prepareData(filename);

        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Give me amount in EUR: ");
        try{
            eurAmount = inputScanner.nextDouble();
        } catch(Exception e) {
            System.err.println("Use ',' as a separator");
            throw new ExitException("Invalid value provided!");
        }
        if(eurAmount < 0) {
            throw new ExitException("Provide positive number as an input!");
        }

        System.out.println("Available currencies: ");
        for(String curr : currenciesRates.keySet())
            System.out.print(curr + " ");
        System.out.println();

        inputScanner.nextLine();
        System.out.print("Choose currency: ");

        String currency = inputScanner.nextLine();
        if(!isCurrencyArgValid(currency)) {
            throw new ExitException("Invalid currency provided!");
        }

        System.out.println("Started computing...");
        double result = eurAmount * currenciesRates.get(currency);
        System.out.println(String.format("%,.4f", eurAmount) + "EUR = " + String.format("%,.4f", result) + currency);
    }

}

