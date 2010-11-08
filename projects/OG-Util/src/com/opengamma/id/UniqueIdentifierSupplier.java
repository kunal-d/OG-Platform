/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.id;

import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Supplier;
import com.opengamma.util.ArgumentChecker;

/**
 * A supplier of unique identifiers.
 * <p>
 * A unique identifier consists of a scheme, value and version.
 * This class creates unique identifiers for a fixed scheme name, where each
 * value is an incrementing number. The values are created in a thread-safe way.
 * The version will always be null.
 * <p>
 * This class is thread-safe and not externally mutable.
 */
public class UniqueIdentifierSupplier implements Supplier<UniqueIdentifier> {

  /**
   * The scheme.
   */
  private final String _scheme;
  /**
   * The generator of identifiers.
   */
  private final AtomicLong _idCount = new AtomicLong();

  /**
   * Creates an instance specifying the scheme.
   * 
   * @param scheme  the scheme, not empty
   */
  public UniqueIdentifierSupplier(final String scheme) {
    ArgumentChecker.notEmpty(scheme, "scheme");
    _scheme = scheme;
  }

  //-------------------------------------------------------------------------
  /**
   * Generates the next unique identifier.
   * 
   * @return the next unique identifier, not null
   */
  public UniqueIdentifier get() {
    final long id = _idCount.incrementAndGet();
    return UniqueIdentifier.of(_scheme, Long.toString(id));
  }

  /**
   * Generates the next unique identifier prefixing the value.
   * 
   * @param valuePrefix  the prefix for the value, not null
   * @return the next unique identifier, not null
   */
  public UniqueIdentifier getWithValuePrefix(final String valuePrefix) {
    final long id = _idCount.incrementAndGet();
    return UniqueIdentifier.of(_scheme, valuePrefix + Long.toString(id));
  }

  //-------------------------------------------------------------------------
  @Override
  public String toString() {
    return "UniqueIdentifierSupplier[" + _scheme + "]";
  }

}
