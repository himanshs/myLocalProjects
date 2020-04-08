
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import amdocs.rm3g.exceptions.RMException;
import amdocs.rm3g.interfaces.datatypes.ActivityInfo;
import amdocs.rm3g.interfaces.datatypes.CreateGroupUnifiedResourcesListBulkInfo;
import amdocs.rm3g.interfaces.datatypes.NewPackageInfo;
import amdocs.rm3g.interfaces.datatypes.RMEntityIdInfo;
import amdocs.rm3g.interfaces.datatypes.UnifiedResourceActivityInfo;
import amdocs.rm3g.urm.sessions.interfaces.api.RM1UnifiedResourceManager;
import amdocs.rm3g.urm.sessions.interfaces.home.RM1UnifiedResourceManagerHome;

public class EjbClient
{

	private static final String WL_MACHINE_PORT_EJB = "indhp060:23802";
	private static final String SOAP_URL = "http://indhp060:23802/awsi_uams/services/UAMSLoginService?wsdl";
	private static final String environment = "LE_tru59_PE";
	private static final String user = "batch_tru59";
	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1' };
	//private static final char[] password = new char[] { 'A', 'c', 'f', '7', '%', '2','w','s' };

	private static final String appId = "RM";
	private String ticket = "";

	//Acf7%2ws

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		
		Hashtable env = new Hashtable(2);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://" + WL_MACHINE_PORT_EJB);
		
		EjbClient client = new EjbClient();
		
		client.invokeGroupUnifiedResourcesList();
		
	}
	
	public void invokeGroupUnifiedResourcesList() {

		RM1UnifiedResourceManagerHome rmUnifiedResourceManagerHome;
		try
		{
			InitialContext initCtx = getinitialContext(user, password);
			
			Object obj = initCtx.lookup("amdocsBeans.RM1UnifiedResourceManagerHome");
			rmUnifiedResourceManagerHome = (RM1UnifiedResourceManagerHome) javax.rmi.PortableRemoteObject.narrow(obj, RM1UnifiedResourceManagerHome.class);
			RM1UnifiedResourceManager rmUnifiedResourceManager = rmUnifiedResourceManagerHome.create();

			CreateGroupUnifiedResourcesListBulkInfo groupUnifiedResourcesListBulkInfo = new CreateGroupUnifiedResourcesListBulkInfo();
			groupUnifiedResourcesListBulkInfo.setProcessMode("STOP_INVALID_INVOCATION");
			groupUnifiedResourcesListBulkInfo.setTotalGroups(1);
			
			NewPackageInfo[] packageInfo = new NewPackageInfo[1];
			packageInfo[0] = new NewPackageInfo();
			UnifiedResourceActivityInfo[] resourceActivityInfo =  new UnifiedResourceActivityInfo[2];
			ActivityInfo aActivity = new ActivityInfo();
			aActivity.setActivityDateUnAssigned();
			aActivity.setActivityName("PAIR");
			
			RMEntityIdInfo msisdnResource = new RMEntityIdInfo();
			msisdnResource.setPoolIdUnAssigned();
			msisdnResource.setType("MSISDN");
			msisdnResource.setValue("08872348772");
			
			RMEntityIdInfo simResource = new RMEntityIdInfo();
			simResource.setPoolIdUnAssigned();
			simResource.setType("SIM");
			simResource.setValue("8758652587587932");
			
			RMEntityIdInfo packResource = new RMEntityIdInfo();
			packResource.setPoolIdUnAssigned();
			packResource.setType("STARTER_PACK");
			packResource.setValue("8758652587587932");
			
			resourceActivityInfo[0] = new UnifiedResourceActivityInfo();
			resourceActivityInfo[0].setActivity(aActivity);
			resourceActivityInfo[0].setUnifiedResource(msisdnResource);	
			resourceActivityInfo[0].setActivityAttributesUnAssigned();
			resourceActivityInfo[0].setActivityParametersUnAssigned();
			
			resourceActivityInfo[1] = new UnifiedResourceActivityInfo();
			resourceActivityInfo[1].setActivity(aActivity);
			resourceActivityInfo[1].setUnifiedResource(simResource);
			resourceActivityInfo[1].setActivityAttributesUnAssigned();
			resourceActivityInfo[1].setActivityParametersUnAssigned();
			
			UnifiedResourceActivityInfo packResActivityInfo = new UnifiedResourceActivityInfo();
			packResActivityInfo.setActivity(aActivity);
			packResActivityInfo.setActivityAttributesUnAssigned();
			packResActivityInfo.setActivityParametersUnAssigned();
			packResActivityInfo.setUnifiedResource(packResource);
			
			packageInfo[0].setComponents(resourceActivityInfo);
			packageInfo[0].setPackageInfo(packResActivityInfo);
			
			groupUnifiedResourcesListBulkInfo.setGroupList(packageInfo);
			
			rmUnifiedResourceManager.groupUnifiedResourcesList(null, groupUnifiedResourcesListBulkInfo);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		catch (CreateException e)
		{
			e.printStackTrace();
		} 
		catch (RMException e)
		{
			e.printStackTrace();
		}finally {
			try {
				close();
			}catch(Exception e) {
				
			}
		}
	}

	/**
	 * @return
	 */
	private InitialContext getinitialContext(String user, char[] password)
	{
		InitialContext initCtx = null;
		try
		{
			getTicket();
			
			Hashtable ht = new Hashtable();

			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ht.put(Context.PROVIDER_URL, "t3://" + WL_MACHINE_PORT_EJB);
			ht.put(Context.SECURITY_CREDENTIALS, "");
			ht.put(Context.SECURITY_PRINCIPAL, ticket);
			initCtx = new InitialContext(ht);

		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (ClassCastException e)
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		catch (CreateException e)
		{
			e.printStackTrace();
		}
		catch (CredentialExpiredException e)
		{
			e.printStackTrace();
		}
		catch (FailedLoginException e)
		{
			e.printStackTrace();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return initCtx;
	}
	
	public String getTicket() throws Exception {

		URL aURL = new java.net.URL(SOAP_URL);
		HttpURLConnection aConnection = (java.net.HttpURLConnection) aURL.openConnection();
		aConnection.setRequestProperty("SOAPAction", SOAP_URL);
		aConnection.setRequestMethod("POST");
		aConnection.setDoOutput(true);
		aConnection.setDoInput(true);

		aConnection.setAllowUserInteraction(false);
		String sXML = createUAMSLoginXML();

		OutputStreamWriter streamToAuthorize = new java.io.OutputStreamWriter(aConnection.getOutputStream());
		streamToAuthorize.write(sXML.toString());
		streamToAuthorize.flush();
		streamToAuthorize.close();
		InputStreamReader resultStream;
		String thisLineReader;
		BufferedReader aReader;
		if (aConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("Error Occured" + aConnection.getErrorStream().read());
			resultStream = new java.io.InputStreamReader(aConnection.getErrorStream());
			aReader = new java.io.BufferedReader(resultStream);
			while ((thisLineReader = aReader.readLine()) != null) {
				System.out.println(" areader is " + thisLineReader);
			}
			System.exit(1);
		}
		resultStream = new java.io.InputStreamReader(aConnection.getInputStream());
		aReader = new java.io.BufferedReader(resultStream);
		String output = "";

		while ((thisLineReader = aReader.readLine()) != null) {
			output += thisLineReader;
		}
		
		//System.out.println(output);

		DocumentBuilderFactory docBuilderFactory = null;
		DocumentBuilder docBuilder = null;

		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setIgnoringElementContentWhitespace(true);
		docBuilderFactory.setIgnoringComments(true);
		docBuilder = docBuilderFactory.newDocumentBuilder();
		ByteArrayInputStream bais = new ByteArrayInputStream(output.getBytes());
		Document document = docBuilder.parse(bais);
		Element elLocation = (Element) document.getElementsByTagName("ns3:ticket").item(0);

		if (elLocation != null) {
			ticket = elLocation.getTextContent();
		}
		System.out.println("Ticket obtained is " + ticket);
		return ticket;
	}

	private String createUAMSLoginXML() {
		String str = "";

		str = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " + "xmlns:ws=\"http://ws.ws.client.uamsimpl.amdocs\">";
		str += "<soapenv:Header/>";
		str += "<soapenv:Body>";
		str += "<ws:loginCall>";
		str += "<ws:in0>" + user + "</ws:in0>";
		str += "<ws:in1>" + String.valueOf(password) + "</ws:in1>";
		str += "<ws:in2>"+appId+"</ws:in2>";
		str += "<ws:in3>" + environment + "</ws:in3>";
		str += "</ws:loginCall>";
		str += "</soapenv:Body>";
		str += "</soapenv:Envelope>";

		return str;
	}

	/**
	 * Method for logging out the ticket using core webservice for UAMS.
	 * 
	 * @param ticket
	 * @throws Exception
	 */

	private void logout(String ticket) throws Exception {
		String url = System.getProperty("soapURL");
		URL aURL = new java.net.URL(url);
		HttpURLConnection aConnection = (java.net.HttpURLConnection) aURL.openConnection();
		aConnection.setRequestProperty("SOAPAction", SOAP_URL);
		aConnection.setRequestMethod("POST");
		aConnection.setDoOutput(true);
		aConnection.setDoInput(true);

		aConnection.setAllowUserInteraction(false);
		String sXML = createUAMSLogoutXML(ticket);

		OutputStreamWriter streamToAuthorize = new java.io.OutputStreamWriter(aConnection.getOutputStream());
		streamToAuthorize.write(sXML.toString());
		streamToAuthorize.flush();
		streamToAuthorize.close();
		InputStreamReader resultStream;
		String thisLineReader;
		BufferedReader aReader;
		if (aConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("Error Occured" + aConnection.getErrorStream().read());
			resultStream = new java.io.InputStreamReader(aConnection.getErrorStream());
			aReader = new java.io.BufferedReader(resultStream);
			while ((thisLineReader = aReader.readLine()) != null) {
				System.out.println(" Error in logging out " + thisLineReader);
			}
			System.exit(1);
		}
		resultStream = new java.io.InputStreamReader(aConnection.getInputStream());
		aReader = new java.io.BufferedReader(resultStream);
		if ((thisLineReader = aReader.readLine()) != null) {
			System.out.println("Logged Out");
		}
	}

	private String createUAMSLogoutXML(String ticket) {
		String str = "";
		System.out.println("Ticket to logout is " + ticket);
		// System.out.println(ticket);
		CharSequence seq1 = "EXT<".subSequence(0, 4);
		CharSequence seq2 = "EXT&lt;".subSequence(0, 7);
		ticket = ticket.replace(seq1, seq2);
		str += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.ws.client.uamsimpl.amdocs\">";
		str += "<soapenv:Header/>";
		str += "<soapenv:Body>";
		str += "<ws:logoutCall>";
		str += "<ws:in0>" + ticket + "</ws:in0>";
		str += "</ws:logoutCall>";
		str += "</soapenv:Body>";
		str += "</soapenv:Envelope>";
		return str;
	}
	
	public void close() throws Exception {
		if (ticket != null) {
			logout(ticket);
		}
	}

}
