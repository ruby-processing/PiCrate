package monkstone.complex;

import java.util.Objects;

/**
 * The purpose of this class is to to make Complex operations more efficient
 * than JRuby RubyComplex, by having a simpler interface, only modest
 * improvements were obtained (but this is better than nothing on RaspberryPI).
 */
public final class JComplex {

    private final double re;   // the real part
    private final double im;   // the imaginary part
    private final static JComplex ZERO = new JComplex(0, 0);
    private final static JComplex NAN = new JComplex(Double.NaN, Double.NaN);

    /**
     * create a new object with the given real and imaginary parts
     *
     * @param real
     * @param imag
     */
    public JComplex(double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     * @return a string representation of the invoking Complex object
     */
    @Override
    public String toString() {
        return "JComplex(" + re + ", " + im + "i)";
    }

    /**
     *
     * @return abs/modulus/magnitude
     */
    public final double abs() {
        return Math.hypot(re, im);
    }

    /**
     *
     * @return square of abs/modulus/magnitude
     */
    public final double abs2() {
        return re * re + im * im;
    }

    /**
     *
     * @return angle/phase/argument, normalized to be between -pi and pi
     */
    public final double phase() {
        return Math.atan2(im, re);
    }

    /**
     *
     * @param b
     * @return a new Complex object whose value is (this + b)
     */
    public final JComplex add(JComplex b) {
        JComplex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new JComplex(real, imag);
    }

    /**
     *
     * @param scalar
     * @return a new Complex object whose value is (this + scalar)
     */
    public final JComplex add(double scalar) {
        return new JComplex(re + scalar, im);
    }

    public final boolean zero() {
        return this.equals(ZERO);
    }

    /**
     *
     * @param b
     * @return a new Complex object whose value is (this - b)
     */
    public final JComplex sub(JComplex b) {
        JComplex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new JComplex(real, imag);
    }

    /**
     *
     * @param scalar
     * @return a new Complex object whose value is (this - scalar)
     */
    public final JComplex sub(double scalar) {
        return new JComplex(re - scalar, im);
    }

    /**
     *
     * @param b
     * @return a new Complex object whose value is (this * b)
     */
    public final JComplex mul(JComplex b) {
        JComplex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new JComplex(real, imag);
    }

    /**
     * Also known as scale
     *
     * @param b
     * @return a new Complex object whose value is (this * b)
     */
    public final JComplex mul(double b) {
        return new JComplex(re * b, im * b);
    }

    /**
     *
     * @return a new Complex object whose value is the conjugate of this
     */
    public final JComplex conjugate() {
        return new JComplex(re, -im);
    }

    /**
     *
     * @return a new Complex object whose value is the reciprocal of this
     */
    private JComplex reciprocal() {
        double scale = re * re + im * im; // self dot product
        return new JComplex(re / scale, -im / scale);
    }

    /**
     *
     * @param other
     * @return this^other
     */
    public final JComplex pow(JComplex other) {
        if (this.zero()) {
            if (other.zero()) {
                return ZERO;
            }
            return NAN;
        }
        return (this).log().mul(other).exp();
    }

    /**
     *
     * @param scalar
     * @return this^scalar
     */
    public final JComplex pow(double scalar) {
        if (this.zero()) {
            if (scalar == 0) {
                return ZERO;
            }
            return NAN;
        }
        return (this).log().mul(scalar).exp();
    }

    /**
     *
     * @return log
     */
    private JComplex log() {
        return new JComplex(Math.log(abs()), Math.atan2(im, re));
    }

    /**
     *
     * @return real part
     */
    public final double re() {
        return re;
    }

    /**
     *
     * @return imaginary part
     */
    public final double im() {
        return im;
    }

    /**
     *
     * @param b
     * @return a / b
     */
    public final JComplex div(JComplex b) {
        JComplex a = this;
        return a.mul(b.reciprocal());
    }

    /**
     *
     * @param b
     * @return a / b
     */
    public final JComplex div(double b) {
        if (b == 0) {
            return NAN;
        }
        return new JComplex(re / b, im / b);
    }

    /**
     *
     * @return a new Complex object whose value is the complex exponential of
     * this
     */
    public final JComplex exp() {
        return new JComplex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    @Override
    public final int hashCode() {
        return Objects.hash(re, im);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JComplex other = (JComplex) obj;
        if (Double.doubleToLongBits(this.re) != Double.doubleToLongBits(other.re)) {
            return false;
        }
        return Double.doubleToLongBits(this.im) == Double.doubleToLongBits(other.im);
    }
}
