package jms.queue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This example shows how to establish a connection to
 * and receive messages from a JMS queue. The classes in this
 * package operate on the same JMS queue. Run the classes together to
 * witness messages being sent and received, and to browse the queue
 * for messages.  This class is used to receive and remove messages
 * from the queue.
 *
 * @author Copyright (c) 1999-2004 by BEA Systems, Inc. All Rights Reserved.
 */
public class QueueReceive implements MessageListener
{
  // Defines the JNDI context factory.
  public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

  // Defines the JMS connection factory for the queue.
  public final static String JMS_FACTORY="JMSconnectionFactory1";

  // Defines the queue.
//  public final static String QUEUE="EAI2CSP";
  public final static String QUEUE="CSP2JMS";  
//  public final static String QUEUE="RalphQ";

  public final static String T3URL="t3://10.64.213.8:7010";

  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueReceiver qreceiver;
  private Queue queue;
  private boolean quit = false;
  private int i = 0;
  private int j = 1;

 /**
  * Message listener interface.
  * @param msg  message
  */
  public void onMessage(Message msg)
  {
    try {
      
      String msgText;
            
      i = i + 1;
      if (i % 500 == 0) {
    	System.out.print(i + " : ");
    	System.out.println(new Date());
      }
      System.out.println("Old Message ID: " + msg.getJMSCorrelationID());
      System.out.println("New Message ID: " + msg.getJMSMessageID());
      System.out.println("JMS Date: " + msg.getJMSTimestamp());
      System.out.println("Message Type: " + msg.getJMSType());
      if (msg instanceof TextMessage) {
        msgText = ((TextMessage)msg).getText().replaceFirst("UTF-8","BIG5");;
      } else {
        msgText = msg.toString();
      }
      System.out.println("Message Sent from: " + msgText);
//      int startidx= msgText.indexOf("TransactionCode",0 ) + 17;
//	  int endidx = msgText.indexOf("\"",startidx);
//	  String filename = msgText.substring(startidx,endidx);
//      startidx= msgText.indexOf("RoutingId",0 ) + 11;
//      endidx = msgText.indexOf("\"",startidx);
//      String CustID = msgText.substring(startidx,endidx);
//      if(startidx==11){
//      		CustID = "0";
//      }
////	  System.out.println(CustID + "-" + j + "-" + filename);
//	  File outxmlFile;
//
//			if (j < 10){
//			outxmlFile = new File("j:/Queue/Q0000" +j + "_" + filename + "_" + CustID +".xml");
//		}
//		else if (j < 100){
//			outxmlFile = new File("j:/Queue/Q000" + j + "_" + filename + "_" + CustID  + ".xml");
//		}
//		else if (j < 1000){
//			outxmlFile = new File("j:/Queue/Q00" + j + "_" + filename + "_" + CustID  + ".xml");
//		}
//		else if (j < 10000){
//			outxmlFile = new File("j:/Queue/Q0" + j + "_" + filename + "_" + CustID  + ".xml");
//		}
//		else{
//			outxmlFile = new File("j:/Queue/Q" + j + "_" + filename + "_" + CustID  + ".xml");
//		}
//		outxmlFile.createNewFile();
//		FileWriter xmlfw = new FileWriter(outxmlFile);
//		xmlfw.write(msgText);
//		xmlfw.close();
//		j ++;
      if (msgText.equalsIgnoreCase("quit")) {
        synchronized(this) {
          quit = true;
          this.notifyAll(); // Notify main thread to quit
        }
      }
    } catch (JMSException jmse) {
      jmse.printStackTrace();
    } 
  }
  /**
   * Creates all the necessary objects for receiving
   * messages from a JMS queue.
   *
   * @param   ctx	JNDI initial context
   * @param	queueName	name of queue
   * @exception NamingException if operation cannot be performed
   * @exception JMSException if JMS fails to initialize due to internal error
   */
  public void init(Context ctx, String queueName)
    throws NamingException, JMSException
  {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    qreceiver = qsession.createReceiver(queue);
    qreceiver.setMessageListener(this);
    qcon.start();
  }

  /**
   * Closes JMS objects.
   * @exception JMSException if JMS fails to close objects due to internal error
   */
  public void close()throws JMSException
  {
  	System.out.print("Queue Receive Ending ....");
	System.out.println(new Date());
    qreceiver.close();
    qsession.close();
    qcon.close();
  }
/**
  * main() method.
  *
  * @param args  WebLogic Server URL
  * @exception  Exception if execution fails
  */

  public static void main(String[] args) throws Exception {
//    if (args.length != 1) {
//      System.out.println("Usage: java examples.jms.queue.QueueReceive WebLogicURL");
//      return;
//    }
  	System.out.print("Queue Receive Starting ....");
	System.out.println(new Date());
    InitialContext ic = getInitialContext(T3URL);
    QueueReceive qr = new QueueReceive();
    qr.init(ic, QUEUE);

    System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message).");

    // Wait until a "quit" message has been received.
    synchronized(qr) {
      while (! qr.quit) {
        try {
          qr.wait();
        } catch (InterruptedException ie) {}
      }
    }
    qr.close();
  }

  private static InitialContext getInitialContext(String url)
    throws NamingException
  {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, url);
    return new InitialContext(env);
  }

}




