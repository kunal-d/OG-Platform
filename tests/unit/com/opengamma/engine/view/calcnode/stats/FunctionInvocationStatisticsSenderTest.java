/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.engine.view.calcnode.stats;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.fudgemsg.FudgeContext;
import org.fudgemsg.FudgeFieldContainer;
import org.fudgemsg.FudgeMsgEnvelope;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.transport.FudgeMessageSender;

/**
 * Tests the function statistics sender.
 */
public class FunctionInvocationStatisticsSenderTest {
  
  private static final Logger s_logger = LoggerFactory.getLogger(FunctionInvocationStatisticsSenderTest.class);
  
  private FunctionCost _cost = new FunctionCost ();
  
  @Test
  public void testBasicBehaviour () {
    final AtomicInteger messages = new AtomicInteger ();
    final FunctionInvocationStatisticsSender sender = new FunctionInvocationStatisticsSender ();
    final FunctionInvocationStatisticsReceiver receiver = new FunctionInvocationStatisticsReceiver (_cost);
    sender.setExecutorService(Executors.newCachedThreadPool ());
    sender.setFudgeMessageSender(new FudgeMessageSender () {

      @Override
      public FudgeContext getFudgeContext() {
        return FudgeContext.GLOBAL_DEFAULT;
      }

      @Override
      public void send(final FudgeFieldContainer message) {
        messages.incrementAndGet ();
        s_logger.debug ("Received {}", message);
        receiver.messageReceived(getFudgeContext (), new FudgeMsgEnvelope (message));
      }
      
    });
    sender.setUpdatePeriod(1);
    for (int i = 0; i < 100; i++) {
      sender.functionInvoked("A", "1", 1, 2.0, 3.0, 4.0);
      sender.functionInvoked("A", "2", 1, 2.0, 3.0, 4.0);
      sender.functionInvoked("A", "1", 1, 2.0, 3.0, 4.0);
      try {
        Thread.sleep (20);
      } catch (InterruptedException e) {
      }
    }
    sender.functionInvoked ("A", "3", 300, 300 * 4.0, 300 * 5.0, 300 * 6.0);
    sender.flush ();
    assertEquals (3, messages.get ());
    assertEquals (2.0, _cost.getFunctionCost("A", "1").getInvocationCost (), 1e-5);
    assertEquals (3.0, _cost.getFunctionCost("A", "1").getDataInputCost (), 1e-5);
    assertEquals (4.0, _cost.getFunctionCost("A", "1").getDataOutputCost (), 1e-5);
    assertEquals (2.0, _cost.getFunctionCost("A", "2").getInvocationCost (), 1e-5);
    assertEquals (3.0, _cost.getFunctionCost("A", "2").getDataInputCost (), 1e-5);
    assertEquals (4.0, _cost.getFunctionCost("A", "2").getDataOutputCost (), 1e-5);
    assertEquals (4.0, _cost.getFunctionCost("A", "3").getInvocationCost (), 1e-5);
    assertEquals (5.0, _cost.getFunctionCost("A", "3").getDataInputCost (), 1e-5);
    assertEquals (6.0, _cost.getFunctionCost("A", "3").getDataOutputCost (), 1e-5);
  }
  
}