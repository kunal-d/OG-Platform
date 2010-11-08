/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.id;

import org.fudgemsg.types.FudgeSecondaryType;
import org.fudgemsg.types.SecondaryFieldType;
import org.fudgemsg.types.StringFieldType;

/**
 * Defines a UniqueIdentifier as a Fudge type using a String.
 * <p>
 * A UniqueIdentifier is typically encoded as a sub-message in Fudge with three separate strings.
 * This class allows the objects to be sent as a single formatted string using
 * {@link UniqueIdentifier#toString()} and {@link UniqueIdentifier#parse(String)}.
 * <p>
 * This class is immutable and thread-safe.
 */
public final class UniqueIdentifierFudgeType extends SecondaryFieldType<UniqueIdentifier, String> {

  /**
   * Singleton instance of the type.
   */
  @FudgeSecondaryType
  public static final UniqueIdentifierFudgeType INSTANCE = new UniqueIdentifierFudgeType();

  /**
   * Creates an instance.
   */
  private UniqueIdentifierFudgeType() {
    super(StringFieldType.INSTANCE, UniqueIdentifier.class);
  }

  //-------------------------------------------------------------------------
  @Override
  public String secondaryToPrimary(final UniqueIdentifier identifier) {
    return identifier.toString();
  }

  @Override
  public UniqueIdentifier primaryToSecondary(final String string) {
    return UniqueIdentifier.parse(string);
  }

}
