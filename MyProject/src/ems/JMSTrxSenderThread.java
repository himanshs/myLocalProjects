package ems;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;

public class JMSTrxSenderThread implements Runnable{

	Context context;
	Queue queue;
	QueueConnectionFactory queueFactory;
	
	File[] files;
	int numberOfthread;
	int threadNumber;
	public Thread thread;
	private long sleepTime;

	public JMSTrxSenderThread(Context context, Queue queue, QueueConnectionFactory queueFactory, File[] files, int numberOfThread, int threadNumber, long sleepTime) {
		this.context = context;
		this.queue = queue;
		this.queueFactory = queueFactory;
		this.files = files;
		this.numberOfthread = numberOfThread;
		this.threadNumber = threadNumber;
		this.sleepTime=sleepTime;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * @param args
	 */

	public void run()
	{
		try{
			send();
		} catch(Exception e){
			System.out.println(threadNumber);
			e.printStackTrace();
		}
	}
	
	
	protected void send() throws Exception {
		QueueSession session =  null;
		QueueConnection connection = null;
		try {
			 connection = queueFactory.createQueueConnection();
			 session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			QueueSender sender = session.createSender(queue);
			int count = 0;
			Calendar calendar = Calendar.getInstance(); 
			long startDate = calendar.getTimeInMillis();
			for (int i = threadNumber; i < files.length; i+=numberOfthread) {
				String xml = "";
	
				BufferedReader reader = new BufferedReader(new FileReader(files[i]));
				String line = "";
				while ((line = reader.readLine()) != null) {
					xml += line;
				}
				
				reader.close();
				
				if (!"".equals(xml)) {
					TextMessage msg = session.createTextMessage();
					msg.setText(xml);
					sender.send(msg);
				}
				count++;
				Thread.sleep(this.sleepTime);
			}
			
			calendar = Calendar.getInstance(); 
			long endDate = calendar.getTimeInMillis();
			System.out.println(count + " Messages sent successfully in " + ((endDate - startDate)) + " milliseconds by thread " + threadNumber);
		}finally {
			if(session != null)
				session.close();
			if(connection != null)
				connection.close();
			
		
		}
	}

}
