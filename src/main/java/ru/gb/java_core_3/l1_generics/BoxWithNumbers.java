package ru.gb.java_core_3.l1_generics;

public class BoxWithNumbers<N extends Number> {
    private N[] numbers;

    public BoxWithNumbers(N... numbers) {
        this.numbers = numbers;
    }

//    public boolean equalsByAvg(BoxWithNumbers<? extends Number> another) {
    public boolean equalsByAvg(BoxWithNumbers<?> another) {
        return Math.abs(avg() - another.avg()) < 0.00001;
    }

    public double avg() {
        return sum() / numbers.length;
    }

    public double sum() {
        var sum = 0.0;
        for (N number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    public N[] getNumbers() {
        return numbers;
    }

    public void setNumbers(N[] numbers) {
        this.numbers = numbers;
    }
}
