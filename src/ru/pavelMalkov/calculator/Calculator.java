package ru.pavelMalkov.calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculator {
    private TypeNumber typeNumber;
    private String[] values;
    private int value1;
    private int value2;
    private char operation;
    private int result;

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение: ");
        return scanner.nextLine();
    }

    public void checkInput(String s) throws RuntimeException {
        String arabPattern = "\\d0?[/*\\-+]\\d0?";
        String romePattern = "[IVX]+[/*\\-+][IVX]+";
        Pattern aPattern = Pattern.compile(arabPattern);
        Pattern rPattern = Pattern.compile(romePattern);
        if (aPattern.matcher(s).matches()) {
            setTypeNumber(TypeNumber.ARABIC);
        } else if (rPattern.matcher(s).matches()) {
            setTypeNumber(TypeNumber.ROMAN);
        } else throw new RuntimeException("Неправильный формат числа");
        splitInput(s);
    }

    private void splitInput(String s) {
        this.values = s.split("[/*\\-+]");
        setOperation(s.charAt(values[0].length()));
    }

    public void calculate() {
        switch (typeNumber) {
            case ARABIC:
                setValue1(Integer.parseInt(values[0]));
                setValue2(Integer.parseInt(values[1]));
                calculateNumbers();
                showResult(String.valueOf(result));
                break;
            case ROMAN:
                try {
                    setValue1(convertToArabic(values[0]));
                    setValue2(convertToArabic(values[1]));
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                    System.exit(0);
                }

                calculateNumbers();
                String res = convertToRoman(getResult());
                showResult(res);
                break;
            default:
                System.out.println("Что-то пошло не так...");
        }
    }

    private void showResult(String res) {
        System.out.println(res);
    }

    private void calculateNumbers() {
        switch (operation) {
            case '+':
                setResult(value1 + value2);
                break;
            case '-':
                setResult(value1 - value2);
                break;
            case '*':
                setResult(value1 * value2);
                break;
            case '/':
                if (value2 == 0) {
                    System.err.println("на ноль делить нельзя!");
                    System.exit(0);
                }
                setResult(value1 / value2);
                break;
            default:
                System.out.println("Что-то пошло не так...");
        }
    }

    private int convertToArabic(String value) throws RuntimeException {
        switch (value) {
            case "I":
                return RomanNumeral.I.getInt();
            case "II":
                return RomanNumeral.II.getInt();
            case "III":
                return RomanNumeral.III.getInt();
            case "IV":
                return RomanNumeral.IV.getInt();
            case "V":
                return RomanNumeral.V.getInt();
            case "VI":
                return RomanNumeral.VI.getInt();
            case "VII":
                return RomanNumeral.VII.getInt();
            case "VIII":
                return RomanNumeral.VIII.getInt();
            case "IX":
                return RomanNumeral.IX.getInt();
            case "X":
                return RomanNumeral.X.getInt();
            default:
                throw new RuntimeException("Значение не должно быть больше 10");
        }
    }

    private String convertToRoman(int val) {
        int max = 0;
        String s = "";
        StringBuilder stringBuilder = new StringBuilder();
        final RomanNumeral[] values = RomanNumeral.values();
        if (val < 0){
            val *= -1;
            stringBuilder.append("-");
        }
        while (val > 0) {
            for (RomanNumeral value : values) {
                if (val >= value.getInt()) {
                    max = value.getInt();
                    s = value.name();
                }
            }
            stringBuilder.append(s);
            val -= max;
        }
        return stringBuilder.toString();
    }

    public TypeNumber getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(TypeNumber typeNumber) {
        this.typeNumber = typeNumber;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
