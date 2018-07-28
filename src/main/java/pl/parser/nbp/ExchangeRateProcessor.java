package pl.parser.nbp;

import java.util.List;

enum ExchangeRateProcessor {
    INSTANCE;


    public ExchangeRates process(InputData inputData) {
        List<String> dirFileNames = DirFileNameFetcher.INSTANCE.fetch(inputData.getStartData(), inputData.getEndDate());
        List<String> xmlFileNames = XmlFileNamesFetcher.INSTANCE.fetch(dirFileNames, inputData.getStartData(), inputData.getEndDate());
        ExchangeRates exchangeRates = ExchangeRateFetcher.INSTANCE.fetch(xmlFileNames, inputData.getCode());

        ExchangeRatePrinter.INSTANCE.print(exchangeRates);
        return exchangeRates;
    }


}
