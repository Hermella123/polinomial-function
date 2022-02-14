public class Polynomial {
    private double[] coef;   // coefficients p(x) = sum { coef[i] * x^i }
    private int degree;   // degree of polynomial (-1 for the zero polynomial)
    static final double EPSILON = 0.001;
    /**
     * Initializes a new polynomial a x^b
     */
    public Polynomial(double a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("exponent cannot be negative: " + b);
        }
        coef = new double[b+1];
        coef[b] = a;
        reduce();
    }


    // pre-compute the degree of the polynomial, in case of leading zero coefficients
    // (that is, the length of the array need not relate to the degree of the polynomial)
    private void reduce() {
        degree = -1;
        for (int i = coef.length - 1; i >= 0; i--) {
            if (coef[i] != 0) {
                degree = i;
                return;
            }
        }
    }
    public int degree() {
        return degree;
    }

    /**
     * Returns the sum of this polynomial and the specified polynomial.
     */
    public Polynomial plus(Polynomial that) {
        Polynomial poly = new Polynomial(0, Math.max(this.degree, that.degree));
        for (int i = 0; i <= this.degree; i++) poly.coef[i] += this.coef[i];
        for (int i = 0; i <= that.degree; i++) poly.coef[i] += that.coef[i];
        poly.reduce();
        return poly;
    }

    /**
     * Returns the result of root of this polynomial.
     */
    public double root(Polynomial pol, double x)
    {
        double h = pol.evaluate(x) / evaluate(x);
        while (Math.abs(h) >= EPSILON)
        {
            h = pol.evaluate(x) / evaluate(x);

            // x(i+1) = x(i) - f(x) / f'(x)
            x = x - h;
        }
        return  Math.round ((x * 100.0) / 100.0);
    }

    /**
     * Returns the result of differentiating this polynomial.
     */
    public Polynomial differentiate() {
        if (degree == 0) return new Polynomial(0, 0);
        Polynomial poly = new Polynomial(0, degree - 1);
        poly.degree = degree - 1;
        for (int i = 0; i < degree; i++)
            poly.coef[i] = (i + 1) * coef[i + 1];
        return poly;
    }

    /**
     * Returns the result of evaluating this polynomial at the point x.
     */
    public double evaluate(double x) {
        double p = 0;
        for (int i = degree; i >= 0; i--)
            p = coef[i] + (x * p);
        return p;
    }

    @Override
    public String toString() {
        if      (degree == -1) return "0";
        else if (degree ==  0) return "" + coef[0];
        else if (degree ==  1)
        {
            if (coef[0] == 0) return coef[1] + "x";
            else if (coef[0]  > 0) return coef[1] + "x" + " + " + (coef[0]);
            else if (coef[0]  < 0) return coef[1] + "x" + " - " + (-coef[0]);
            return coef[1] + "x + " + coef[0];
        }
        String s = coef[degree] + "x^" + degree;
        for (int i = degree - 1; i >= 0; i--) {
            if      (coef[i] == 0) continue;
            else if (coef[i]  > 0) s = s + " + " + (coef[i]);
            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

}
