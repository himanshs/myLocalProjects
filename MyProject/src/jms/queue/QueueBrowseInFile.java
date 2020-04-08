package jms.queue;
/**
 * Use JMS under Weblogic 8.1 server:
 *   Browse what message contained in the queue,
 *   Print all necessary information on the screen.
 * Make sure the following setting:
 *   JNDI_FACTORY - JNDI Factory name
 *   JMS_FACTORY  - JMS Factory name
 *   QUEUE        - JMS Queue name
 * Edit from JMS example (BEA8.1), Programmer: Deriek 26 July 2004
 */

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;

/**
 * This example shows how to establish a connection to a JMS
 * queue and browse (but not dequeue) the queued messages. The classes in this
 * package operate on the same JMS queue. Run the classes together to
 * observe messages being sent and received, and to browse the queue
 * for messages. This class is used to browse, but not remove, messages
 * in the queue.
 * <p>
 *
 * @author Copyright (c) 1999-2004 by BEA Systems, Inc. All Rights Reserved.
 */

public class QueueBrowseInFile
{
  // Defines the JNDI context factory.
  public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

  // Defines the JMS connection factory for the queue.
  public final static String JMS_FACTORY="JMSconnectionFactory1";
  // public final static String JMS_FACTORY="weblogic.examples.jms.QueueConnectionFactory";

  // Defines the queue.
  public final static String QUEUE="CSP2JMS";
//	public final static String QUEUE="RalphQ";
  // public final static String QUEUE="weblogic.examples.jms.exampleQueue";

  // Define the t3 url and port.
//	public final static String T3URL="t3://10.64.202.2:16002";

	public final static String T3URL="t3://10.64.18.79:7010";	// KGEx

	
// 	public final static String T3URL="t3://10.64.209.3:16038";	// test10	
//	public final static String T3URL="t3://10.64.209.3:16034";	// test06
//		public final static String T3URL="t3://10.64.209.3:16002";	// test02
//	public final static String T3URL="t3://10.64.209.2:16030"; //SIT02
//    public final static String T3URL="t3://10.64.209.2:16010"; //SIT03 
  // public final static String T3URL="t3://feteai2:7001";


  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueBrowser qbrowser;
  private Queue queue;

  /**
   * Creates all the necessary objects for receiving
   * messages from a JMS queue.
   *
   * @param   ctx 	JNDI initial context
   * @param   queueName	 	name of queue
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
    qbrowser = qsession.createBrowser(queue);
    qcon.start();
  }

  /**
   * Displays the current contents of the queue.
   *
   * @exception JMSException if JMS fails to display messages on the queue due to internal error
   */
  public void displayQueue() throws JMSException, IOException {
    Enumeration e = qbrowser.getEnumeration();
    Message m = null;
    File outputFile = new File("dump/message_results.txt");
    outputFile.createNewFile();
    int i = 0;
    int j = 1;

    if (! e.hasMoreElements()) {
      System.out.println("There are no messages on this queue.");
    } else {
      System.out.println("Queued JMS Messages: ");
      FileWriter fw = new FileWriter(outputFile);
      PrintWriter fwn = new PrintWriter(fw);
      while (e.hasMoreElements()) {
        i++;
        m = (Message) e.nextElement();
        System.out.println("");
        if (i % 100 == 0) {
        	System.out.println(new Date());
        }
        System.out.println("Message ID " + m.getJMSMessageID() +
                           "\n delivered " + new Date(m.getJMSTimestamp()) +
                           " to " + m.getJMSDestination());
/*
        System.out.print("\tExpires        ");

        if (m.getJMSExpiration() > 0) {
          System.out.println( new Date( m.getJMSExpiration()));
        }
        else {
          System.out.println("never");
        }

        System.out.println("\tPriority       " + m.getJMSPriority());
        System.out.println("\tMode           " + (
                      m.getJMSDeliveryMode() == DeliveryMode.PERSISTENT ?
                                       "PERSISTENT" : "NON_PERSISTENT"));
        System.out.println("\tCorrelation ID " + m.getJMSCorrelationID());
        System.out.println("\tReply to       " + m.getJMSReplyTo());
        System.out.println("\tMessage type   " + m.getJMSType());
*/
        if (m instanceof TextMessage) {
          try {
          	String StrMessage =((TextMessage) m).getText().replaceFirst("UTF-8","BIG5");
          	int startidx= StrMessage.indexOf("TransactionCode",0 ) + 17;
			int endidx = StrMessage.indexOf("\"",startidx);
			String filename = StrMessage.substring(startidx,endidx);
          	startidx= StrMessage.indexOf("RoutingId",0 ) + 11;
          	endidx = StrMessage.indexOf("\"",startidx);
          	String CustID = StrMessage.substring(startidx,endidx);
          	if(startidx==11){
          		CustID = "0";
          	}
			System.out.println(CustID + "-" + j + "-" + filename);
			File outxmlFile;

 			if (j < 10){
				outxmlFile = new File("j:/Queue/" + filename+ "_Q0000"+ j + "_" + CustID +".xml");
			}
			else if (j < 100){
				outxmlFile = new File("j:/Queue/" + filename+ "_Q000" + j + "_" + CustID  + ".xml");
			}
			else if (j < 1000){
				outxmlFile = new File("j:/Queue/" + filename+ "_Q00" + j + "_" + CustID  + ".xml");
			}
			else if (j < 10000){
				outxmlFile = new File("j:/Queue/" + filename+ "_Q0" + j + "_" + CustID  + ".xml");
			}
			else{
				outxmlFile = new File("j:/Queue/" + filename+ "_Q" + j + "_" + CustID  + ".xml");
			}
			
			outxmlFile.createNewFile();
			FileWriter xmlfw = new FileWriter(outxmlFile);
			PrintWriter xmlfwn = new PrintWriter(xmlfw);
//			fw.write(new String(((TextMessage) m).getText().getBytes("ISO-8859-1"),"UTF-8"));			
			fw.write(StrMessage);
//	ISO-8859-1 , BIG5 , UTF-8 			
//			fw.write(new String(((TextMessage) m).getText()));
            fwn.println();
// .replaceAll("UTF-8","BIG5")
//	   xmlfw.write(new String(((TextMessage) m).getText().getBytes("ISO-8859-1"),"UTF-8"));
			xmlfw.write(StrMessage);
//			fw.write(new String(((TextMessage) m).getText()));
			xmlfwn.println();
			xmlfw.close();
			xmlfwn.close();
            j++;
          }
          catch (JMSException ex) {
            ex.printStackTrace();
          }
          catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
          }
        }
      }
    fw.close();
    fwn.close();
    System.out.println("There are " + Integer.toString(i) + " messages in the queue of "+ m.getJMSDestination());
    }
  }

  /**
   * Closes JMS objects.
   *
   * @exception JMSException if JMS fails to close objects due to internal error
   */
  public void close() throws JMSException {
    qbrowser.close();
    qsession.close();
    qcon.close();
  }

  /**
   * main() method.
   *
   * @param  args WebLogic Server URL
   * @exception Exception if execution fails
   */

  public static void main(String[] args) throws Exception  {
//    if (args.length != 1) {
//      System.out.println("Usage: java examples.jms.queue.QueueBrowse WebLogicURL");
//      return;
//    }
    InitialContext ic = getInitialContext(T3URL);
    QueueBrowseInFile qb = new QueueBrowseInFile();
    qb.init(ic, QUEUE);
    qb.displayQueue();
    qb.close();
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




