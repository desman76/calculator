package ru.pavelMalkov.calculator;

public class CalculatorStarter {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String input = calculator.getInput();
        try {
            calculator.checkInput(input.toUpperCase());
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        calculator.calculate();
    }
}
