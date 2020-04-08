package src;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TCRunner extends APIRunner {

	private String ticket = null;

	@Override
	/**
	 * Method to initialize the objects required during execution
	 */
	public void initialize() throws Exception {
		addIntoMap("createNewCustomer", "src.CreateCustomerInvoker");
		addIntoMap("createNewAccount", "src.CreateAccountInvoker");
		addIntoMap("createNewSubscriber", "src.CreateSubscriberInvoker");
		addIntoMap("changeSubscriberStatus", "src.SubStatusAPIInvoker");
		addIntoMap("updateSubscriber","src.UpdateSubscriberInvoker");
		addIntoMap("moveSubscriber","src.MoveSubscriberInvoker");
		addIntoMap("renewSubscriber","src.RenewSubscriberInvoker");
		addIntoMap("RunJobs", "src.BatchInvoker");
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	/**
	 * Method to actually call the API
	 */
	public void process(String line) throws Exception {

		if (line != null) {
			String[] inputs = line.split("[|]", -1);
			Class api = (Class) Class.forName(apiMap.get(inputs[0]));
			APIInvoker invoker = (APIInvoker) api.newInstance();
			invoker.setTicket((ticket == null) ? getTicket() : ticket);
			invoker.setUrl(getUrl());
			invoker.createAPIInput(line);
			invoker.invokeAPI();
		}
	}

	@Override
	/**
	 * Method to destroy the objects got created during the execution
	 */
	public void close() throws Exception {
		if (ticket != null) {
			logout(ticket);
		}
	}

	@Override
	public String getUrl() {

		return System.getProperty("URL");

	}

	@Override
	/**
	 * Method for returning ticket using core webservice for UAMS
	 */
	public String getTicket() throws Exception {

		String url = System.getProperty("soapURL");
		URL aURL = new java.net.URL(url);
		HttpURLConnection aConnection = (java.net.HttpURLConnection) aURL.openConnection();
		aConnection.setRequestProperty("SOAPAction", url);
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
			System.out.println(" Login Sucessfull");
			output += thisLineReader;
		}

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
		str += "<ws:in0>" + System.getProperty("userName") + "</ws:in0>";
		str += "<ws:in1>" + System.getProperty("password") + "</ws:in1>";
		str += "<ws:in2>CM</ws:in2>";
		str += "<ws:in3>" + System.getProperty("environment") + "</ws:in3>";
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
		aConnection.setRequestProperty("SOAPAction", url);
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
}
