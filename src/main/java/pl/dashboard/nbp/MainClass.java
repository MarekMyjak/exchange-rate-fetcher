package pl.dashboard.nbp;


class MainClass {

    private static final String WRONG_NUMBER_OF_ARGUMENT_ERROR_MESSAGE = "Wrong number of arguments, expected 1.";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(WRONG_NUMBER_OF_ARGUMENT_ERROR_MESSAGE);
            return;
        }
        String date = args[0];

        ExchangeRateProcessor.INSTANCE.process(date);
    }



}
