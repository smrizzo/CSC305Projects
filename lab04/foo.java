public class foo {
private static double factorial(double n) {
        long count = Math.round(n);
        double result = 1.0;
        if (count < 0) {
            throw new ArithmeticException();
        }
        for (long i = 2; i <= count; i++) {
            result *= i;
        }
        return result;
    }

    public static double value4 = (7.0)!;
    public static double value0 = (5.0) * (-((4.0) - (factorial((3.0) / ((2.0) + (1.0))))));
    public static double value1 = 42.0;
    public static double value2 = (1.0) / (0.0);
    public static double value3 = 99.0;
    public static void main(String[] args) {
        System.out.println(value0);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
    }
}