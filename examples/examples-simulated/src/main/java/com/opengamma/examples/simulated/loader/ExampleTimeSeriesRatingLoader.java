/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.examples.simulated.loader;

import static com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesRatingFieldNames.DATA_PROVIDER_NAME;
import static com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesRatingFieldNames.DATA_SOURCE_NAME;
import static com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesRatingFieldNames.DEFAULT_CONFIG_NAME;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.component.tool.AbstractTool;
import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.examples.simulated.historical.SimulatedHistoricalDataGenerator;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigMasterUtils;
import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesRating;
import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesRatingRule;
import com.opengamma.scripts.Scriptable;

/**
 * Example code to create a timeseries rating document
 * <p>
 * It is designed to run against the HSQLDB example database.  
 * It should be possible to run this class with no extra command line parameters.
 */
@Scriptable
public class ExampleTimeSeriesRatingLoader extends AbstractTool<ToolContext> {

  /** Logger. */
  @SuppressWarnings("unused")
  private static final Logger s_logger = LoggerFactory.getLogger(ExampleTimeSeriesRatingLoader.class);

  //-------------------------------------------------------------------------
  /**
   * Main method to run the tool.
   * 
   * @param args  the arguments, unused
   */
  public static void main(String[] args) {  // CSIGNORE
    new ExampleTimeSeriesRatingLoader().initAndRun(args, ToolContext.class);
    System.exit(0);
  }

  //-------------------------------------------------------------------------
  @Override
  protected void doRun() {
    ConfigMaster configMaster = getToolContext().getConfigMaster();    
    List<HistoricalTimeSeriesRatingRule> rules = new ArrayList<HistoricalTimeSeriesRatingRule>();
    rules.add(new HistoricalTimeSeriesRatingRule(DATA_SOURCE_NAME, "BLOOMBERG", 1));
    rules.add(new HistoricalTimeSeriesRatingRule(DATA_SOURCE_NAME, SimulatedHistoricalDataGenerator.OG_DATA_SOURCE, 2));
    rules.add(new HistoricalTimeSeriesRatingRule(DATA_PROVIDER_NAME, "CMPL", 1));
    rules.add(new HistoricalTimeSeriesRatingRule(DATA_PROVIDER_NAME, SimulatedHistoricalDataGenerator.OG_DATA_PROVIDER, 2));
    HistoricalTimeSeriesRating ratingConfig = new HistoricalTimeSeriesRating(rules);
    ConfigItem<HistoricalTimeSeriesRating> config = ConfigItem.of(ratingConfig, DEFAULT_CONFIG_NAME, HistoricalTimeSeriesRating.class);
    ConfigMasterUtils.storeByName(configMaster, config);
  }

}
