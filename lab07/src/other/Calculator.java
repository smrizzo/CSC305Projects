
package other;

/**
 * This is just a dumb little class to hold the numFactors() funciton.
 * Don't modify this function, and please, just use it.  This exercise
 * is about managing threads, not calculating the number of factors of
 * a given number efficiently.
 */
public class Calculator {

    public static int numFactors(long num) {
        int result = 0;
        long max = Math.round(Math.sqrt(num) + 1.0);    // Over-estimate
        for (long divisor = 1; divisor <= max; divisor++) {
            if (num % divisor == 0) {
                result++;
            }
        }
        result++;       // All numbers are divisible by self
        return result;
    }
}

