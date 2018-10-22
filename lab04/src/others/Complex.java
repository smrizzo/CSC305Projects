

package others;

import java.util.Objects;

/**
 * A simple implementation of complex numbers.  Instances are immutable.
 */

public final class Complex {

    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
	this.real = real;
	this.imaginary = imaginary;
    }


    /**
     * Creates a complex number from a string representation, like
     * "1,-5".
     *
     * @throws NumberFormatException if val is not a valid representation
     *				     of a complex number
     */
    public Complex(String val) {
        String[] a = val.split(",");
	if (a.length != 2) {
	    throw new NumberFormatException(val + " is invalid");
	}
	this.real = Double.parseDouble(a[0]);
	this.imaginary = Double.parseDouble(a[1]);
    }

    public double getReal() {
	return real;
    }

    public double getImaginary() {
	return imaginary;
    }

    /**
     * Compute the factorial of the real part rounded to the nearest
     * integer, as determined by Math.round(double).
     *
     * @throws ArithmeticException if the rounded real part is negative
     */
    public Complex factorial() {
	double result = 1.0;
	long count = Math.round(real);
	if (count < 0) {
	    throw new ArithmeticException();
	}
	for (long i = 2; i <= count; i++) {
	    result *= i;
	}
	return new Complex(result, 0.0);
    }

    /**
     * Compute -this
     */
    public Complex negate() {
	return new Complex(-real, -imaginary);
    }

    /**
     * Compute this + arg
     */
    public Complex add(Complex arg) {
	return new Complex(real + arg.real, imaginary + arg.imaginary);
    }

    /**
     * Compute this - arg
     */
    public Complex subtract(Complex arg) {
	return new Complex(real - arg.real, imaginary - arg.imaginary);
    }

    /**
     * Compute this * arg
     */
    public Complex multiply(Complex arg) {
	return new Complex(
		real * arg.real - imaginary * arg.imaginary,
		real * arg.imaginary + imaginary * arg.real
	);
    }

    /**
     * Compute this / arg
     */
    public Complex divide(Complex arg) {
	double a = real;
	double b = imaginary;
	double c = arg.real;
	double d = arg.imaginary;
	double div = c*c + d*d;
	return new Complex((a*c + b*d) / div, (b*c - a*d) / div);
    }

    /**
     * Returns a string representation of this complex number, for
     * debugging.
     */
    @Override
    public String toString() {
	return "" + real + "," + imaginary;
    }

    @Override
    public int hashCode() {
	return Objects.hash(real, imaginary);
    }

    @Override
    public boolean equals(Object other) {
	if (other instanceof Complex) {
	    Complex c = (Complex) other;
	    return c.real == real && c.imaginary == imaginary;
	} else {
	    return false;
	}
    }
}
