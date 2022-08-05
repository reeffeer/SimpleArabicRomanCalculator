package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        callSimpleCalculator();
    }

    private static void callSimpleCalculator() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter an expression to compute: ");
        String incomingString;
        try {
            incomingString = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Проверка на наличие математического оператора в введенной строке
        checkIfArithmeticExpression(incomingString);
        //Заполняем строковый массив членами введенного выражения, разделенными математическим знаком
        String[] stringArr = incomingString.split("(?<=\\d)[-/*+]");
        int[] arabicArr = new int[stringArr.length];
        //Если выражение содержит римские цифры, конвертируем в арабские
        convert(stringArr);
        //Если в строковом массиве есть числа, то приводим их к числовому типу и добавляем в числовой массив
        fillTheNumericArray(stringArr, arabicArr);
        //Идем по числовому массиву, вычисляем результат в зависимости от знака во входящей строке и выводим на экран
        printResult(incomingString, arabicArr);
    }

    private static void printResult(String incomingString, int[] arabicArr) {
        System.out.println(calculate(incomingString, arabicArr));
    }

    private static int calculate(String incomingString, int[] arabicArr) {
        int result;
        for (int i = 1; i < arabicArr.length; i++) {
            result = incomingString.contains("+") ? arabicArr[i-1] + arabicArr[i] :
                    incomingString.contains("-") ? arabicArr[i-1] - arabicArr[i] :
                            incomingString.contains("*") ? arabicArr[i-1] * arabicArr[i] :
                                    incomingString.contains("/") ? arabicArr[i-1] / arabicArr[i] : 0;
            return result;
        }
        return 0;
    }
    private static void fillTheNumericArray(String[] stringArr, int[] arabicArr) {
        for (int i = 0; i < stringArr.length; i++) {
            if (stringArr[i].matches("\\d++")) {
                arabicArr[i] = Integer.parseInt(stringArr[i]);
            }
        }
    }
    private static void convert(String[] stringArr) {
        for (int i = 0; i < stringArr.length; i++) {
            switch (stringArr[i]) {
                case "I":
                    stringArr[i] = "1";
                    break;
                case "II":
                    stringArr[i] = "2";
                    break;
                case "III":
                    stringArr[i] = "3";
                    break;
                case "IV":
                    stringArr[i] = "4";
                    break;
                case "V":
                    stringArr[i] = "5";
                    break;
                case "VI":
                    stringArr[i] = "6";
                    break;
                case "VII":
                    stringArr[i] = "7";
                    break;
                case "VIII":
                    stringArr[i] = "8";
                    break;
                case "IX":
                    stringArr[i] = "9";
                    break;
                case "X":
                    stringArr[i] = "10";
                    break;
            }
        }
    }
    private static void checkIfArithmeticExpression(String incomingString) {
        Pattern pat = Pattern.compile("[-/*+]");
        Matcher match = pat.matcher(incomingString);
        if (!match.find()) {
            System.out.println("--------ERROR! Enter arithmetic expression.---------");
            System.exit(1);
        } else System.out.println("Result: ");
    }
}
