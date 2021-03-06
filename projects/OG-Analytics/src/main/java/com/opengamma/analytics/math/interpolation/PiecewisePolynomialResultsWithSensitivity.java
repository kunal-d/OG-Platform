/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation;

import org.apache.commons.lang.NotImplementedException;

import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.util.ArgumentChecker;

/**
 * Result of interpolation by piecewise polynomial containing
 * knots: Positions of knots
 * coefMatrix: Coefficient matrix whose i-th row vector is { a_n, a_{n-1}, ...} for the i-th interval, where a_n, a_{n-1},... are coefficients of f(x) = a_n (x-x_i)^n + a_{n-1} (x-x_i)^{n-1} + ....
 * In multidimensional cases, coefficients for the i-th interval of the j-th spline is in (j*(i-1) + i) -th row vector.
 * nIntervals: Number of intervals, which should be (Number of knots) - 1
 * order: Number of coefficients in polynomial, which is equal to (polynomial degree) + 1
 * dim: Number of splines
 * which are in the super class, and 
 * _coeffSense Node sensitivity of the coefficients _coeffSense[i].getData()[j][k] is \frac{\partial a^i_{n-j}}{\partial y_k}
 */
public class PiecewisePolynomialResultsWithSensitivity extends PiecewisePolynomialResult {

  private final DoubleMatrix2D[] _coeffSense;

  /**
   * 
   * @param knots  
   * @param coefMatrix 
   * @param order 
   * @param dim 
   * @param coeffSense the sensitivity of the coefficients to the nodes (y-values)
   */
  public PiecewisePolynomialResultsWithSensitivity(DoubleMatrix1D knots, DoubleMatrix2D coefMatrix, int order, int dim, final DoubleMatrix2D[] coeffSense) {
    super(knots, coefMatrix, order, dim);
    if (dim != 1) {
      throw new NotImplementedException();
    }
    ArgumentChecker.noNulls(coeffSense, "null coeffSense"); // coefficient
    _coeffSense = coeffSense;
  }

  /**
   * Access _coeffSense
   * @return _coeffSense
   */
  public DoubleMatrix2D[] getCoefficientSensitivityAll() {
    return _coeffSense;
  }

  /**
   * Access _coeffSense for the i-th interval
   * @param interval 
   * @return _coeffSense for the i-th interval
   */
  public DoubleMatrix2D getCoefficientSensitivity(final int interval) {
    return _coeffSense[interval];
  }
}
