package jms;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.Hashtable;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSBulkSender {

	public static void main(String[] args) throws Exception{
		
		File file = new File(System.getProperty("filePath"));
		String providerUrl = System.getProperty("url");
		String queueName = System.getProperty("queueName");
		String queueConnFactory = System.getProperty("queueConnFactory");
		String numberOfThreads = System.getProperty("numberOfThreads");
		String sleepTime = System.getProperty("sleepTime");
		String initContextFactory = System.getProperty("initContextFactory");
		
		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.endsWith("xml")) 
					return true;
				else
					return false;
			}
		};
		File[] files = file.listFiles(filter);
		
		if(numberOfThreads == null){
			numberOfThreads = "4";
		}
		
		if(sleepTime == null){
			sleepTime = "100";
		}
		
		if(initContextFactory == null) {
			initContextFactory = "weblogic.jndi.WLInitialContextFactory";
		}
		
		JMSTrxSenderThread[] jmsSender = new JMSTrxSenderThread[Integer.parseInt(numberOfThreads)];

		Calendar calendar = Calendar.getInstance(); 
		long startDate = calendar.getTimeInMillis();
		
		Context context = null;
		
		try {
			Hashtable properties = new Hashtable();
			properties.put(Context.INITIAL_CONTEXT_FACTORY, initContextFactory);
			properties.put(Context.PROVIDER_URL, providerUrl);
			context = new InitialContext(properties);
			
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(queueConnFactory);
			Queue queue = (Queue) context.lookup(queueName);
			
			for (int i = 0; i < jmsSender.length; i++){
				jmsSender[i] = new JMSTrxSenderThread(context, queue, factory, files, jmsSender.length, i, Long.parseLong(sleepTime));
			}
			
			for (int i = 0; i < jmsSender.length; i++){
				jmsSender[i].thread.join();
			}
		}finally {
			if (context != null) {
				context.close();
			}
		}
		
		calendar = Calendar.getInstance(); 
		long endDate = calendar.getTimeInMillis();
		System.out.println("Messages sent successfully in " + ((endDate - startDate)/1000) + " seconds");
	}

}
