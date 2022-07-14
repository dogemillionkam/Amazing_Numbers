package numbers;

import java.math.BigInteger;
import java.util.*;

public class Main {
    private static boolean even = false;
    private static boolean odd = false;
    private static boolean buzz = false;
    private static boolean duck = false;
    private static boolean palindromic = false;
    private static boolean gapful = false;
    private static boolean spy = false;

    private static boolean square = false;
    private static boolean sunny = false;

    private static boolean jumping = false;
    private static boolean happy = false;
    private static boolean sad = false;

    private static final HashMap<String, Boolean> checkValues = new HashMap<>();
    private static final String[][] checkValuesThree = {
            {"even", "odd"}, {"sunny", "square"}, {"duck", "spy"}, {"happy", "sad"},
            {"-even", "-odd"}, {"-duck", "-spy"}, {"-happy", "-sad"},
            {"even", "-even"}, {"odd", "-odd"}, {"sunny", "-sunny"}, {"happy", "-happy"},
            {"sad", "-sad"}, {"square", "-square"}, {"duck", "-duck"}, {"buzz", "-buzz"},
            {"palindromic", "-palindromic"}, {"gapful", "-gapful"}, {"spy", "-spy"}, {"jumping", "-jumping"},
    };

    static Scanner scanner = new Scanner(System.in);
    static String input;
    static ArrayList<String> inputArray;

    public static void main(String[] args) {
        instructions();
        takeNewInput();
        welcomeMessage();
    }

    public static void setUpValues(boolean... a) {
        checkValues.put("even", a[0]);
        checkValues.put("odd", a[1]);
        checkValues.put("duck", a[2]);
        checkValues.put("gapful", a[3]);
        checkValues.put("buzz", a[4]);
        checkValues.put("spy", a[5]);
        checkValues.put("palindromic", a[6]);
        checkValues.put("sunny", a[7]);
        checkValues.put("square", a[8]);
        checkValues.put("jumping", a[9]);
        checkValues.put("happy", a[10]);
        checkValues.put("sad", a[11]);
        checkValues.put("-even", a[12]);
        checkValues.put("-odd", a[13]);
        checkValues.put("-duck", a[14]);
        checkValues.put("-gapful", a[15]);
        checkValues.put("-buzz", a[16]);
        checkValues.put("-spy", a[17]);
        checkValues.put("-palindromic", a[18]);
        checkValues.put("-sunny", a[19]);
        checkValues.put("-square", a[20]);
        checkValues.put("-jumping", a[21]);
        checkValues.put("-happy", a[22]);
        checkValues.put("-sad", a[23]);

    }

    public static void welcomeMessage() {
        setUpValues(even, odd, duck, gapful, buzz, spy, palindromic, sunny, square, jumping, happy, sad,
                !even, !odd, !duck, !gapful, !buzz, !spy, !palindromic, !sunny, !square, !jumping, !happy, !sad);
        while (true) {
            int inputArrayLength = inputArray.size(); // size of array

            if (inputArrayLength == 0 || input.equals("") || input.equals(" ")) {
                instructions();
                takeNewInput();
            } else if (Objects.equals(inputArray.get(0), "0")) {
                System.out.println("Goodbye!");
                return;
            } else if (inputArray.get(0).equals("exit") || inputArray.get(0).equals("bye")) {
                firstDigitErrorMessage();
                takeNewInput();
            } else if (inputArrayLength == 1) {
                BigInteger firstDigit = new BigInteger(inputArray.get(0));
                if (firstDigit.compareTo(BigInteger.ONE) < 0) {
                    firstDigitErrorMessage();
                } else {
                    properties(firstDigit);
                }
                takeNewInput();
            } else {
                if (inputArray.get(1).equals("0")) {
                    System.out.println("Goodbye!");
                    return;
                }
                BigInteger firstDigit = new BigInteger(inputArray.get(0));
                BigInteger secondDigit = new BigInteger(inputArray.get(1));

                if (firstDigit.compareTo(BigInteger.ONE) < 0) {
                    firstDigitErrorMessage();
                    takeNewInput();
                } else if (secondDigit.compareTo(BigInteger.ZERO) < 0) {
                    secondDigitErrorMessage();
                    takeNewSecondaryInput();
                } else if (inputArrayLength == 2) {
                    BigInteger stoppingCondition = firstDigit.add(secondDigit);
                    properties(firstDigit, stoppingCondition);
                    takeNewInput();
                } else {
                    moreThanTwoInputs(firstDigit, secondDigit);
                    takeNewInput();
                }
            }
        }
    }


    private static void moreThanTwoInputs(BigInteger firstDigit, BigInteger secondDigit) {
        boolean tempBool0 = false;
        for (int i = 0; i <= checkValuesThree.length - 1; i++) {
            if (inputArray.contains(checkValuesThree[i][0]) && inputArray.contains(checkValuesThree[i][1])) {
                mutuallyExclusiveErrorMessage(checkValuesThree[i][0], checkValuesThree[i][1]);
                tempBool0 = true;
            }
        }

        boolean tempBool1 = false;
        for (int i = 2; i <= inputArray.size() - 1; i++) {
            if (!checkValues.containsKey(inputArray.get(i).toLowerCase())) {
                tempBool1 = true;
                break;
            }
        }

        if (tempBool0) {
        } else if (tempBool1) {
            fourthDigitErrorMessage();
        } else {
            ArrayList<String> truncatedArray = new ArrayList<>(inputArray);
            truncatedArray.remove(0);
            truncatedArray.remove(0);
            properties(firstDigit, secondDigit, truncatedArray);
        }
    }

    public static void instructions() {
        System.out.println("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.

                Enter a request:""");
    }

    public static void firstDigitErrorMessage() {
        System.out.println("""

                The first parameter should be a natural number or zero.

                Enter a request:""");
    }

    public static void secondDigitErrorMessage() {
        System.out.println("""

                The second parameter should be a natural number.

                Enter a request:""");
    }


    public static void fourthDigitErrorMessage() {
        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 2; i <= inputArray.size() - 1; i++) {
            if (!checkValues.containsKey(inputArray.get(i).toLowerCase())) {
                tempArray.add(inputArray.get(i).toUpperCase());
            }
        }
        if (tempArray.size() == 1) {
            System.out.println("The property " + tempArray + " is wrong. Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD, HAPPY, SAD]\nEnter a request:");
        } else {
            System.out.println("The properties " + tempArray + " are wrong. Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD, HAPPY, SAD]\nEnter a request:");
        }
    }

    public static void mutuallyExclusiveErrorMessage(String value, String valueTwo) {
        System.out.printf("""
                The request contains mutually exclusive properties: [%s, %s]
                There are no numbers with these properties.
                                
                Enter a request:""", value.toUpperCase(), valueTwo.toUpperCase());
    }

    public static void takeNewInput() {
        input = scanner.nextLine().toLowerCase();
        inputArray = new ArrayList<>(Arrays.asList(input.split(" ")));
//        moreThanTwoInputs(inputArray.size());
    }

    public static void takeNewSecondaryInput() {
        inputArray.set(1, scanner.next());
    }


    public static void checkIfIsHappyNumber(BigInteger input) {
        Set<BigInteger> set = new HashSet<>();
        BigInteger tempInput = input;
        while (tempInput.compareTo(BigInteger.valueOf(1)) > 0 && !set.contains(tempInput)) {
            set.add(tempInput);
            tempInput = BigInteger.valueOf(String.valueOf(tempInput).chars()
                    .map(c -> c - '0')
                    .reduce(0, (sum, i) -> sum + i * i));
        }
        if (tempInput.compareTo(BigInteger.valueOf(1)) == 0) {
            happy = true;
        } else {
            sad = true;
        }
    }


    public static void checkIfIsSunnyOrSquareNumber(BigInteger input) {
        BigInteger[] remainderForSquare = input.add(BigInteger.ONE).sqrtAndRemainder();
        square = input.sqrtAndRemainder()[1].equals(BigInteger.ZERO);
        sunny = Objects.equals(remainderForSquare[1], BigInteger.ZERO);
    }

    public static void checkIfIsASpyNumber(BigInteger input) {
        BigInteger product = BigInteger.valueOf(1);
        BigInteger sum = BigInteger.valueOf(0);
        BigInteger tempVal = input;
        BigInteger rem;

        while (tempVal.compareTo(BigInteger.ZERO) > 0) {
            rem = tempVal.remainder(BigInteger.valueOf(10));
            product = product.multiply(rem);
            sum = sum.add(rem);
            tempVal = tempVal.divide(BigInteger.valueOf(10));
        }
        spy = Objects.equals(product, sum);
    }

    public static void checkIfIsABuzzNumber(BigInteger input) {
        int endsWithSevenInt = input.remainder(BigInteger.valueOf(10)).compareTo(BigInteger.valueOf(7));
        boolean endsWithSeven = endsWithSevenInt == 0;
        boolean remainderIsZero = input.remainder(BigInteger.valueOf(7)).equals(BigInteger.valueOf(0));
        buzz = endsWithSeven || remainderIsZero;
    }

    public static void checkIfIsAGapfulNumber(BigInteger input) {
        int lengthOfInput = input.toString().length();
        BigInteger firstDigit = BigInteger.valueOf(Integer.parseInt(String.valueOf(String.valueOf(input).charAt(0))));
        BigInteger lastDigit = BigInteger.valueOf(Integer.parseInt(String.valueOf(String.valueOf(input).charAt(input.toString().length() - 1))));
        BigInteger combine = new BigInteger(firstDigit + "" + lastDigit);
        boolean remainder = input.remainder(combine).compareTo(BigInteger.valueOf(0)) == 0;
        gapful = lengthOfInput >= 3 && remainder;
    }

    public static void checkIfIsADuckNumber(BigInteger input) {
        String inputString = String.valueOf(input);
        duck = inputString.indexOf("0", 1) != -1;
    }

    public static void checkIfIsAPalindromicNumber(BigInteger input) {
        String inputString = String.valueOf(input);
        String reverseString = new StringBuilder(inputString).reverse().toString();
        palindromic = inputString.equals(reverseString);

    }

    public static void checkOddOrEven(BigInteger input) {
        int equals = input.remainder(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0));
        if (equals == 0) {
            even = true;
        } else {
            odd = true;
        }
    }

    public static void checkIfIsAJumpingNumber(BigInteger input) {
        if (input.compareTo(BigInteger.valueOf(9)) <= 0) {
            jumping = true;
        } else {
            ArrayList<String> newList = new ArrayList<>(Arrays.asList(input.toString().split("")));
            for (int i = 0; i < newList.size() - 1; i++) {
                if (Math.abs(Integer.parseInt(newList.get(i + 1)) - Integer.parseInt(newList.get(i))) != 1) {
                    jumping = false;
                    break;
                } else {
                    jumping = true;
                }
            }
        }

    }

    public static void reset() {
        even = false;
        odd = false;
        buzz = false;
        duck = false;
        palindromic = false;
        spy = false;
        gapful = false;
        sunny = false;
        square = false;
        jumping = false;
        happy = false;
        sad = false;
    }

    public static void properties(BigInteger input) {
        functionRunner(input);
        System.out.printf("""
                Properties of %s
                        even: %b
                         odd: %b
                        buzz: %b
                        duck: %b
                        spy: %b
                palindromic: %b
                      gapful: %b
                       sunny: %b
                      square: %b
                        happy: %b
                          sad: %b
                     jumping: %b\040
                """, input, even, odd, buzz, duck, spy, palindromic, gapful, sunny, square, happy, sad, jumping);
        reset();
        System.out.println("Enter a request:");
    }

    public static void functionRunner(BigInteger input) {
        checkIfIsABuzzNumber(input);
        checkOddOrEven(input);
        checkIfIsADuckNumber(input);
        checkIfIsAPalindromicNumber(input);
        checkIfIsASpyNumber(input);
        checkIfIsAGapfulNumber(input);
        checkIfIsSunnyOrSquareNumber(input);
        checkIfIsAJumpingNumber(input);
        checkIfIsHappyNumber(input);
        setUpValues(even, odd, duck, gapful, buzz, spy, palindromic, sunny, square, jumping, happy, sad,
                !even, !odd, !duck, !gapful, !buzz, !spy, !palindromic, !sunny, !square, !jumping, !happy, !sad);
    }

    public static void properties(BigInteger input, BigInteger stoppingCondition) {
        for (BigInteger i = input; i.compareTo(stoppingCondition) < 0; i = i.add(BigInteger.ONE)) {
            functionRunner(i);
            createANewNumberString(i);
            reset();
        }
        System.out.println("Enter a request:");
    }

    public static void createANewNumberString(BigInteger i) {
        StringBuilder newString = new StringBuilder();
        newString.append(i).append(" is ");
        if (even) newString.append("even").append(", ");
        if (odd) newString.append("odd").append(", ");
        if (buzz) newString.append("buzz").append(", ");
        if (duck) newString.append("duck").append(", ");
        if (spy) newString.append("spy").append(", ");
        if (palindromic) newString.append("palindromic").append(", ");
        if (gapful) newString.append("gapful").append(", ");
        if (sunny) newString.append("sunny").append(", ");
        if (square) newString.append("square").append(", ");
        if (jumping) newString.append("jumping").append(", ");
        if (happy) newString.append("happy").append(", ");
        if (sad) newString.append("sad").append(", ");
        newString.deleteCharAt(newString.length() - 2);
        System.out.println(newString);
    }

    public static void properties(BigInteger input, BigInteger stoppingCondition, ArrayList<String> truncatedArray) {
        BigInteger counter = BigInteger.valueOf(0);
        BigInteger i = input;
        boolean tempBooleanForPrint = false;
        while (!counter.equals(stoppingCondition)) {
            functionRunner(i);
            for (int j = 0; j <= truncatedArray.size() - 1; j++) {
                tempBooleanForPrint = checkValues.get(truncatedArray.get(j).toLowerCase());
                if (!tempBooleanForPrint) {
                    break;
                }
            }
            if (tempBooleanForPrint) {
                createANewNumberString(i);
                counter = counter.add(BigInteger.ONE);
            }
            reset();
            i = i.add(BigInteger.ONE);
        }
        System.out.println("Enter a request:");
    }
}
