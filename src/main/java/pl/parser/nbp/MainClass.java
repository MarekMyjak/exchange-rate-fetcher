package pl.parser.nbp;

class MainClass {

    private static final String WRONG_NUMBER_OF_ARGUMENT_ERROR_MESSAGE = "Wrong number of arguments, expected %d.";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(String.format(WRONG_NUMBER_OF_ARGUMENT_ERROR_MESSAGE, EXPECTED_NUMBER_OF_ARGUMENTS));
            return;
        }
        InputData inputData = new InputData(args[0], args[1], args[2]);
        ExchangeRateProcessor.INSTANCE.process(inputData);
    }
}