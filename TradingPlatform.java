import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TradingPlatform {
    private Map<String, Double> marketData = new HashMap<>();
    private Map<String, Integer> portfolio = new HashMap<>();
    private double accountBalance = 10000.0; // Initial account balance

    public void addStockToMarket(String symbol, double price) {
        marketData.put(symbol, price);
    }

    public void displayMarketData() {
        System.out.println("Market Data:");
        for (String symbol : marketData.keySet()) {
            System.out.println(symbol + ": $" + marketData.get(symbol));
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (String symbol : portfolio.keySet()) {
            System.out.println(symbol + ": " + portfolio.get(symbol) + " shares");
        }
    }

    public void buyStock(String symbol, int quantity) {
        if (marketData.containsKey(symbol)) {
            double price = marketData.get(symbol);
            double totalCost = price * quantity;
            if (totalCost <= accountBalance) {
                accountBalance -= totalCost;
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + symbol);
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Stock symbol not found in market data.");
        }
    }

    public void sellStock(String symbol, int quantity) {
        if (portfolio.containsKey(symbol)) {
            double price = marketData.get(symbol);
            double totalValue = price * quantity;
            if (portfolio.get(symbol) >= quantity) {
                accountBalance += totalValue;
                portfolio.put(symbol, portfolio.get(symbol) - quantity);
                System.out.println("Sold " + quantity + " shares of " + symbol);
            } else {
                System.out.println("Not enough shares in the portfolio.");
            }
        } else {
            System.out.println("Stock symbol not found in portfolio.");
        }
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        platform.addStockToMarket("AAPL", 150.00);
        platform.addStockToMarket("GOOGL", 2800.00);
        platform.addStockToMarket("AMZN", 3400.00);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n1. Display Market Data");
            System.out.println("2. Display Portfolio");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    platform.displayMarketData();
                    break;
                case 2:
                    platform.displayPortfolio();
                    break;
                case 3:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine();
                    System.out.print("Enter quantity to buy: ");
                    int buyQuantity = scanner.nextInt();
                    platform.buyStock(buySymbol, buyQuantity);
                    break;
                case 4:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    platform.sellStock(sellSymbol, sellQuantity);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
