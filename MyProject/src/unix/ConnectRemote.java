package unix;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ConnectRemote {
	private static String USERNAME ="tkswrk25"; // username for remote host
	private static String PASSWORD ="Unix_11"; // password of the remote host
	private static String host = "indhp018"; // remote host address
	private static int port=22;
	
	public static void main(String[] args) {
		ConnectRemote server = new ConnectRemote();
		server.executeCommand("");
	}
	
	public void executeCommand(String scriptFileName)
	 {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(USERNAME, host, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(PASSWORD);
			session.connect();
			
			//create the excution channel over the session
			ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
			
			// Gets an InputStream for this channel.
			InputStream in = channelExec.getInputStream();
			
			// Set the command that you want to execute
			channelExec.setCommand("rm HimSud.tmp");
			
			// Execute the command
			channelExec.connect();
			
			// Read the output from the input stream we set above

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;

			//Read each line from the buffered reader and add it to result list

			// You can also simple print the result here

			while ((line = reader.readLine()) != null)
			{
			    System.out.println(line);
			}
			
			//retrieve the exit status of the remote command corresponding to this channel                     
            int exitStatus = channelExec.getExitStatus();                                                     
			
            //Safely disconnect channel and disconnect session. If not done then it may cause resource leak   
			channelExec.disconnect();                                                                         
			session.disconnect();                                                                             
			if(exitStatus < 0){                                                                               
				System.out.println("Done, but exit status not set!");                                                                                                                                      
			}else if(exitStatus > 0){                                                                          
				System.out.println("Done, but with error!");                                                                                                                                               
			}else{                                                                                             
				System.out.println("Done!");                                                                                               
			}                                                                                                  

		}catch(Exception e){
			System.err.println("Error: " + e);
		}

	 }

}
