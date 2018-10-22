
import others.Complex;
import others.RunStrategy;

//
// Modify this file.  The file Main will handle parsing
// the command-line arguments for you
//
// You may write this class in Kotlin if you wish.
public class Program extends Main {

    @Override
    protected void runTests() {
	// Put any unit tests you want to run here
    }

    @Override
    protected RunStrategy<Double> getDoubleStrategy() {

	return new DoubleStrategy();
    }

    @Override
    protected RunStrategy<Complex> getComplexStrategy() {
	// Implement this
	    return new ComplexStrategy();
    }

    @Override
    protected RunStrategy<Long> getLongStrategy() {
	// Implement this
	return new LongStategy();

    }
}
