/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.world.exchange.master;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.financial.world.exchange.Exchange;
import com.opengamma.id.Identifier;
import com.opengamma.id.IdentifierBundle;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.util.ArgumentChecker;

/**
 * An exchange on which financial products can be traded or settled.
 * <p>
 * Financial products are often traded at a specific location known as an exchange.
 * This class represents details of the exchange, including region and opening hours.
 */
@BeanDefinition
public class ManageableExchange extends DirectBean implements Exchange {

  /**
   * The unique identifier of the exchange.
   */
  @PropertyDefinition
  private UniqueIdentifier _uniqueIdentifier;
  /**
   * The name of the exchange intended for display purposes.
   */
  @PropertyDefinition
  private String _name;
  /**
   * The bundle of identifiers that define the exchange.
   */
  @PropertyDefinition
  private IdentifierBundle _identifiers = IdentifierBundle.EMPTY;
  /**
   * The identifier of the region where the exchange is located.
   */
  @PropertyDefinition
  private Identifier _regionId;
  /**
   * The detailed information about when an exchange is open or closed.
   */
  @PropertyDefinition
  private final List<ManageableExchangeCalendarEntry> _calendarEntries = new ArrayList<ManageableExchangeCalendarEntry>();

  /**
   * Creates an exchange.
   */
  public ManageableExchange() {
  }

  /**
   * Creates an exchange specifying the values of the main fields.
   * 
   * @param identifiers  the bundle of identifiers that define the exchange, not null
   * @param name  the name of the exchange, for display purposes, not null
   * @param regionId  the identifier of the region where the exchange is located, null if not applicable (dark pool, electronic, ...)
   */
  public ManageableExchange(IdentifierBundle identifiers, String name, Identifier regionId) {
    ArgumentChecker.notNull(identifiers, "identifiers");
    ArgumentChecker.notNull(name, "name");
    setIdentifiers(identifiers);
    setName(name);
    setRegionId(regionId);
  }

  /**
   * Returns an independent clone of this exchange.
   * @return the clone, not null
   */
  public ManageableExchange clone() {
    ManageableExchange cloned = new ManageableExchange();
    cloned._uniqueIdentifier = _uniqueIdentifier;
    cloned._name = _name;
    cloned._identifiers = _identifiers;
    cloned._regionId = _regionId;
    cloned._calendarEntries.addAll(_calendarEntries);
    return cloned;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ManageableExchange}.
   * @return the meta-bean, not null
   */
  public static ManageableExchange.Meta meta() {
    return ManageableExchange.Meta.INSTANCE;
  }

  @Override
  public ManageableExchange.Meta metaBean() {
    return ManageableExchange.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -125484198:  // uniqueIdentifier
        return getUniqueIdentifier();
      case 3373707:  // name
        return getName();
      case 1368189162:  // identifiers
        return getIdentifiers();
      case -690339025:  // regionId
        return getRegionId();
      case -658202766:  // calendarEntries
        return getCalendarEntries();
    }
    return super.propertyGet(propertyName);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -125484198:  // uniqueIdentifier
        setUniqueIdentifier((UniqueIdentifier) newValue);
        return;
      case 3373707:  // name
        setName((String) newValue);
        return;
      case 1368189162:  // identifiers
        setIdentifiers((IdentifierBundle) newValue);
        return;
      case -690339025:  // regionId
        setRegionId((Identifier) newValue);
        return;
      case -658202766:  // calendarEntries
        setCalendarEntries((List<ManageableExchangeCalendarEntry>) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the exchange.
   * @return the value of the property
   */
  public UniqueIdentifier getUniqueIdentifier() {
    return _uniqueIdentifier;
  }

  /**
   * Sets the unique identifier of the exchange.
   * @param uniqueIdentifier  the new value of the property
   */
  public void setUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
    this._uniqueIdentifier = uniqueIdentifier;
  }

  /**
   * Gets the the {@code uniqueIdentifier} property.
   * @return the property, not null
   */
  public final Property<UniqueIdentifier> uniqueIdentifier() {
    return metaBean().uniqueIdentifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the exchange intended for display purposes.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name of the exchange intended for display purposes.
   * @param name  the new value of the property
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle of identifiers that define the exchange.
   * @return the value of the property
   */
  public IdentifierBundle getIdentifiers() {
    return _identifiers;
  }

  /**
   * Sets the bundle of identifiers that define the exchange.
   * @param identifiers  the new value of the property
   */
  public void setIdentifiers(IdentifierBundle identifiers) {
    this._identifiers = identifiers;
  }

  /**
   * Gets the the {@code identifiers} property.
   * @return the property, not null
   */
  public final Property<IdentifierBundle> identifiers() {
    return metaBean().identifiers().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the identifier of the region where the exchange is located.
   * @return the value of the property
   */
  public Identifier getRegionId() {
    return _regionId;
  }

  /**
   * Sets the identifier of the region where the exchange is located.
   * @param regionId  the new value of the property
   */
  public void setRegionId(Identifier regionId) {
    this._regionId = regionId;
  }

  /**
   * Gets the the {@code regionId} property.
   * @return the property, not null
   */
  public final Property<Identifier> regionId() {
    return metaBean().regionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the detailed information about when an exchange is open or closed.
   * @return the value of the property
   */
  public List<ManageableExchangeCalendarEntry> getCalendarEntries() {
    return _calendarEntries;
  }

  /**
   * Sets the detailed information about when an exchange is open or closed.
   * @param calendarEntries  the new value of the property
   */
  public void setCalendarEntries(List<ManageableExchangeCalendarEntry> calendarEntries) {
    this._calendarEntries.clear();
    this._calendarEntries.addAll(calendarEntries);
  }

  /**
   * Gets the the {@code calendarEntries} property.
   * @return the property, not null
   */
  public final Property<List<ManageableExchangeCalendarEntry>> calendarEntries() {
    return metaBean().calendarEntries().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ManageableExchange}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueIdentifier} property.
     */
    private final MetaProperty<UniqueIdentifier> _uniqueIdentifier = DirectMetaProperty.ofReadWrite(this, "uniqueIdentifier", UniqueIdentifier.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(this, "name", String.class);
    /**
     * The meta-property for the {@code identifiers} property.
     */
    private final MetaProperty<IdentifierBundle> _identifiers = DirectMetaProperty.ofReadWrite(this, "identifiers", IdentifierBundle.class);
    /**
     * The meta-property for the {@code regionId} property.
     */
    private final MetaProperty<Identifier> _regionId = DirectMetaProperty.ofReadWrite(this, "regionId", Identifier.class);
    /**
     * The meta-property for the {@code calendarEntries} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<ManageableExchangeCalendarEntry>> _calendarEntries = DirectMetaProperty.ofReadWrite(this, "calendarEntries", (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("uniqueIdentifier", _uniqueIdentifier);
      temp.put("name", _name);
      temp.put("identifiers", _identifiers);
      temp.put("regionId", _regionId);
      temp.put("calendarEntries", _calendarEntries);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public ManageableExchange createBean() {
      return new ManageableExchange();
    }

    @Override
    public Class<? extends ManageableExchange> beanType() {
      return ManageableExchange.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueIdentifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueIdentifier> uniqueIdentifier() {
      return _uniqueIdentifier;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code identifiers} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<IdentifierBundle> identifiers() {
      return _identifiers;
    }

    /**
     * The meta-property for the {@code regionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Identifier> regionId() {
      return _regionId;
    }

    /**
     * The meta-property for the {@code calendarEntries} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<ManageableExchangeCalendarEntry>> calendarEntries() {
      return _calendarEntries;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
