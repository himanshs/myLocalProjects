package jms;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JmsQueueSimulator {

	public static void main(String[] args) throws Exception {
		
        String providerUrl = "t3://apt3tstd01:7031";
        String factoryName = "ConnectionFactory";
        String queueName = "EnablerJMSQueue";
        String msgdata = "<XML>Test TRX</XML>";

        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        properties.put(Context.PROVIDER_URL, providerUrl);

        Context context = new InitialContext(properties);
        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(factoryName);

        // Getting the JMSQueueName from where trx will be passed to TRB.        
        Queue queue = (Queue) context.lookup(queueName);

        // Creating JMS queue connection.
        QueueConnection connection = factory.createQueueConnection();

        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        try {
	        //publish trx to JMS queue
	        publishTrxToJMS(connection, session, queue, msgdata, true);
	        
	        //receive trx from JMS queue
	        receiveTrxFromJMS(connection, session, queue);
        }catch(Exception e) {
        	e.printStackTrace();
        	throw e;
        }finally {
        	try {
        		if(connection != null) {
        			connection.close();
        		}
        		if(session != null){
        			session.close();
        		}
        		if(queue != null){
        			queue=null;
        		}
        	}catch(Exception e) {
        		System.out.println(e.getMessage());
        	}
        }        
	}
	
	public static void receiveTrxFromJMS(QueueConnection connection, QueueSession session, Queue queue) throws Exception
	{
			// Connection is being started.
        connection.start();

      // Code for reading from the jms Queue
      QueueReceiver receiver = session.createReceiver(queue);
      System.out.println("connection created");
      
      try {
	      TextMessage msg = (TextMessage)receiver.receive(1);
	      if(msg != null){
	      	String str = msg.getText(); 
	      	System.out.println("Test" +str);
	      }
	    }finally {
	    	try{
	    		if(receiver!=null)
	    			receiver.close();
	    	}catch(JMSException e) {
	    		System.out.println(e.getMessage());
	    	}
	    }
		
	}
	
	public static void publishTrxToJMS(QueueConnection connection, QueueSession session, Queue queue, String jmsMsgData, boolean msgVarification) throws Exception
	{
		// Connection is being started.
		connection.start();
			
		QueueSender sender = session.createSender(queue);
		TextMessage msg = session.createTextMessage();

		try {
			msg.clearBody();
			// msMsg.setStringProperty("DVC_TRX_NO", "0");
			// msMsg.setJMSType("package_received");
			// msMsg.setJMSDestination(q);
			// msMsg.setText("Testing JMS Message via QUEUE SENDER");
			msg.setText(jmsMsgData);
			sender.send(msg);
			
			if (msgVarification)
			{
				QueueBrowser msQBrowser = session.createBrowser(queue);
	
				connection.start();
	
				if (msQBrowser != null)
				{
					Enumeration cursor = msQBrowser.getEnumeration();
					int count = 0;
					System.out.println(count + ". Message sent are:");
					while (cursor.hasMoreElements())
					{
						count++;
						TextMessage msMsg = (TextMessage) cursor.nextElement();
						System.out.println(count + ". " + msMsg.getText());
					}
				}
				msQBrowser.close();
			}
		}finally {
			try{				
				if(sender !=null)
					sender.close();
			}catch (JMSException e) {
				System.out.println(e.getMessage());				
			}
		}

	}

}
