package org.example;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        //check some condition, return boolean;
        Predicate<Integer> isPositive = x -> x > 0;
        System.out.println(isPositive.test(5));

        //consume two object<T> and apply binary operation return object<T>
        BinaryOperator<Integer> multiply = (x, y) -> x*y;
        System.out.println(multiply.apply(3,5));

        UnaryOperator<Integer> square = x -> x*x;
        System.out.println(square.apply(5));

        //transition function from object<T> to object<R>
        Function<Integer, String> convert = x -> String.valueOf(x) + "$";
        System.out.println(convert.apply(5));

        Consumer<Integer> printer = x -> System.out.printf("%d dollars \n", x);
        printer.accept(500);


    }
}