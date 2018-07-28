package pl.parser.nbp;

enum ExchangeRatePrinter {
    INSTANCE;

    void print(ExchangeRates exchangeRates) {
        System.out.println(exchangeRates);
    }

}
