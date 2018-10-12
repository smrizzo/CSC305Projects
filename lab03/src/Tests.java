import edu.calpoly.testy.Testy;

import java.math.BigDecimal;

import static edu.calpoly.testy.Assert.assertEquals;

public class Tests {

    private static RPNParser parser = new RPNParser();

    private static void testEvaluate(String src, double expected) {
        parser.setMode("double");
        Expression expr = parser.parse(src);
        assertEquals("check " + src, expected, expr.evaluate(), 0.00000001);
    }

    private void testToString(String src, String expected) {
        parser.setMode("double");
        Expression expr = parser.parse(src);
        assertEquals("check $src", expected, expr.toString());
    }

    private static void testAccept(String src, long expected) {
        parser.setMode("long");
        Expression expr = parser.parse(src);
        LongVisitor visitor = new LongVisitor();
        expr.accept(visitor);
        assertEquals("check " + src, expected, visitor.getResult(), 0.00000001);

    }

    private void testLongToString(String src, String expected) {
        parser.setMode("long");
        Expression expr = parser.parse(src);
        assertEquals("check $src", expected, expr.toString());
    }

    private static void testAccept(String src, BigDecimal expected) {
        parser.setMode("decimal");
        Expression expr = parser.parse(src);
        BigDecimalVisitor visitor = new BigDecimalVisitor();
        expr.accept(visitor);
        assertEquals("check " + src, expected, visitor.getResult());

    }

    private void testDecimalToString(String src, String expected) {
        parser.setMode("decimal");
        Expression expr = parser.parse(src);
        assertEquals("check $src", expected, expr.toString());
    }


    public void runTests() {
        Testy.run(
                //Double Testing
                () -> { testEvaluate("2 3 +", 5.0); },
                () -> { testEvaluate("2 3 *", 6.0); },
                () -> { testEvaluate("7 !", 5040.0); },
                () -> { testEvaluate("5 4 -", 1.0);},
                () -> { testEvaluate("18 6 /", 3.0);},
                () -> { testEvaluate("8 10 - negate", 2.0);},


                () -> { testToString("2 3 +", "(2.0 + 3.0)"); },
                () -> { testToString("2 3 *", "(2.0 * 3.0)"); },
                () -> { testToString("5 4 -", "(5.0 - 4.0)"); },
                () -> { testToString("18 6 /", "(18.0 / 6.0)"); },
                () -> { testToString("18 6 / negate", "negate((18.0 / 6.0))"); },
                () -> { testToString("7 !", "(7.0)!"); },

                //Long Testing
                () -> { testAccept("2 3 +", 5);},
                () -> { testAccept("2 3 *", 6);},
                () -> { testAccept("7 !", 5040);},
                () -> { testAccept("5 4 -", 1);},
                () -> { testAccept("18 6 /", 3);},
                () -> { testAccept("18 6 / negate", -3);},

                () -> { testLongToString("2 3 +", "(2 + 3)");},
                () -> { testLongToString("2 3 *", "(2 * 3)");},
                () -> { testLongToString("5 4 -", "(5 - 4)");},
                () -> { testLongToString("18 6 /", "(18 / 6)");},
                () -> { testLongToString("18 6 / negate", "negate((18 / 6))");},
                () -> { testLongToString("7 !", "(7)!");},

                //Decimal Testing
                () -> { testAccept("2 3 +", new BigDecimal(5));},
                () -> { testAccept("2 3 *", new BigDecimal(6));},
                () -> { testAccept("7 !", new BigDecimal(5040));},
                () -> { testAccept("5.0 4.0 -", new BigDecimal(1.0).setScale(1));},
                () -> { testAccept("18 6 /", new BigDecimal(3.000000).setScale(6));},
                () -> { testAccept("18 6 / negate", new BigDecimal(-3.000000).setScale(6));},

                () -> { testDecimalToString("2 3 +", "(2 + 3)");},
                () -> { testDecimalToString("2 3 *", "(2 * 3)");},
                () -> { testDecimalToString("5.0 4.0 -", "(5.0 - 4.0)");},
                () -> { testDecimalToString("18 6 /", "(18 / 6)");},
                () -> { testDecimalToString("18 6 / negate", "negate((18 / 6))");},
                () -> { testDecimalToString("7 !", "(7)!");}


        );
    }
}
