import java.net.InetAddress;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import amdocs.csm3g.datatypes.ActivityInfo;
import amdocs.csm3g.datatypes.BusinessEntityIdInfo;
import amdocs.csm3g.datatypes.CustomerTypeInfo;
import amdocs.csm3g.datatypes.LogicalResourceInfo;
import amdocs.csm3g.datatypes.PhysicalResourceInfo;
import amdocs.csm3g.datatypes.SrvAgrInfo;
import amdocs.csm3g.datatypes.SubscriberIdInfo;
import amdocs.csm3g.datatypes.SubscriberTypeInfo;
import amdocs.csm3g.sessions.interfaces.api.SaleEventConv;
import amdocs.csm3g.sessions.interfaces.api.SubscriberServices;
import amdocs.csm3g.sessions.interfaces.home.SaleEventConvHome;
import amdocs.csm3g.sessions.interfaces.home.SubscriberServicesHome;
import amdocs.uams.UamsPasswordCredential;
import amdocs.uams.UamsSystem;
import amdocs.uams.auth.UamsAuthenticationService;
import amdocs.uams.login.UamsLoginContext;
import amdocs.uams.login.direct.DirectLoginService;
import amdocs.uams.login.direct.UamsDirectLoginContext;

public class FETCMClient {

	private static int subscriberNumber;
	
	private static Logger logger = Logger.getLogger(FETCMClient.class);
	public static FETCMClient getInstance()
	{
		return instance;
	}
	private static FETCMClient instance = new FETCMClient();


	public static void main (String args[]) throws Exception
	{
		FETCMClient rThread = new FETCMClient();
		rThread.run();
	}

	protected void run() throws Exception
	{

		System.out.println("Date = " + new Date());
		Context ctx = null;

		try
		{
//			ctx = getInitialContext_PROD("indlin1752", "11121", "fet32", "Unix11", "LE_fet32_PE", "RPL"); // FET32
//			ctx = getInitialContext_PROD("indlin1752", "22121", "fet43", "Unix11", "LE_fet43_PE", "RPL"); // FET43
//			ctx = getInitialContext_PROD("indlin1767", "11121", "fet34", "Unix11", "LE_fet34_PE", "RPL"); // FET34
			ctx = getInitialContext_PROD("indlin1942", "11121", "ABPBatchUser", "Unix11", "LE_fet51_PE", "RPL"); // FET43

			
			subscriberNumber = 29;
			
//			createPreActiveSubscriber(ctx);	
			saleEvent(ctx);
//			cancelSubscriber(ctx);
			
			System.out.println("ALL DONE !!!!!!!");
			System.exit(1);
		}
		catch(Exception e)
		{
//			System.out.println("Error in main method............." + e);
//			System.out.println(e.getMessage());
			//System.out.println(((CMException)e).getMessageRepository().);
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	public static void createPreActiveSubscriber(Context ctx) throws Exception
	{
		
		String JNDI_NAME ="amdocsBeans.CM1SubscriberServicesHome";
		SubscriberServicesHome mSubscriberServicesHome = null;
		SubscriberServices mSubscriberServices = null;
		Object rcg_obj = null;

    	rcg_obj = ctx.lookup(JNDI_NAME);
    	mSubscriberServicesHome = (SubscriberServicesHome) javax.rmi.PortableRemoteObject.narrow(rcg_obj, SubscriberServicesHome.class);
    	mSubscriberServices = mSubscriberServicesHome.create();	 				
		
		BusinessEntityIdInfo businessEntityIdInfo = new BusinessEntityIdInfo();
		businessEntityIdInfo.setBusinessEntityId(110154);
		SubscriberTypeInfo subscriberTypeInfo = new SubscriberTypeInfo();
		subscriberTypeInfo.setSubscriberType("G");

		SrvAgrInfo srvAgrInfo = new SrvAgrInfo();
		srvAgrInfo.setSoc("117564");
		srvAgrInfo.setRelatedOffers(null);
		
		LogicalResourceInfo[] logicalResourceInfo = new LogicalResourceInfo[2];
		logicalResourceInfo[0] = new LogicalResourceInfo();
		logicalResourceInfo[0].setName("MSISDN");
		logicalResourceInfo[0].setValues(new String[] {"5399889999"});
		
		logicalResourceInfo[1] = new LogicalResourceInfo();
		logicalResourceInfo[1].setName("IMSI");
		logicalResourceInfo[1].setValues(new String[] {"53995889999"});
		
		PhysicalResourceInfo[] physicalResourceInfo = new PhysicalResourceInfo[1];
		physicalResourceInfo[0] = new PhysicalResourceInfo();
		physicalResourceInfo[0].setName("ICCID");
		physicalResourceInfo[0].setValues(new String[] {"5399588229999"});
		
		SubscriberIdInfo subscriberIdInfo = mSubscriberServices.createPreActivatedSubscriber(businessEntityIdInfo, subscriberTypeInfo, null, null, srvAgrInfo, null, null, logicalResourceInfo, physicalResourceInfo, null, null);
		subscriberNumber = subscriberIdInfo.getSubscrNumber();
		
		System.out.println("subscriber_no="+subscriberIdInfo.getSubscrNumber());
	}
	
	public static void saleEvent(Context ctx) throws Exception{				
		
		String JNDI_NAME ="amdocsBeans.CM1SaleEventConvHome";
		SaleEventConvHome mSaleEventConvHome = null;
		SaleEventConv mSaleEventConv = null;
		Object rcg_obj = null;
	
		rcg_obj = ctx.lookup(JNDI_NAME);
		mSaleEventConvHome = (SaleEventConvHome) javax.rmi.PortableRemoteObject.narrow(rcg_obj, SaleEventConvHome.class);
		mSaleEventConv = mSaleEventConvHome.create();	 
		
				
		SubscriberIdInfo subscriberIdInfo = new SubscriberIdInfo();
		subscriberIdInfo.setSubscrNumber(subscriberNumber);			
		mSaleEventConv.setSubscriberIdentifier(subscriberIdInfo);
		
		System.out.println("Subscriber type :  " + mSaleEventConv.getSubscriberTypeInfo());
		
		CustomerTypeInfo customerTypeInfo = new CustomerTypeInfo();
		
		customerTypeInfo.setCustomerType((byte)'R');
		customerTypeInfo.setCustomerSubtype("I");	
		mSaleEventConv.setCustomerTypeInfo(customerTypeInfo);
		
		ActivityInfo activityInfo = new ActivityInfo();
		activityInfo.setNullAll();
		activityInfo.setActivityReason("CREQ");
		
		mSaleEventConv.activateSubscriber(activityInfo);
		
		System.out.println("customer id :  " + mSaleEventConv.getCustomerIdInfo());						

	}

	public static void cancelSubscriber(Context ctx) throws Exception {

		String JNDI_NAME ="amdocsBeans.CM1SubscriberServicesHome";
		SubscriberServicesHome mSubscriberServicesHome = null;
		SubscriberServices mSubscriberServices = null;
		Object rcg_obj = null;

    	rcg_obj = ctx.lookup(JNDI_NAME);
    	mSubscriberServicesHome = (SubscriberServicesHome) javax.rmi.PortableRemoteObject.narrow(rcg_obj, SubscriberServicesHome.class);
    	mSubscriberServices = mSubscriberServicesHome.create();	 
    	
		ActivityInfo activityInfo = new ActivityInfo();
		activityInfo.setNullAll();
		activityInfo.setActivityReason("CREQ");
		SubscriberIdInfo subscriberIdInfo = new SubscriberIdInfo();
		subscriberIdInfo.setSubscrNumber(subscriberNumber);
		mSubscriberServices.cancelSubscriber(subscriberIdInfo, null, null, activityInfo);
	}
	
	public static void suspendSubscriber(Context ctx) throws Exception {

		String JNDI_NAME ="amdocsBeans.CM1SubscriberServicesHome";
		SubscriberServicesHome mSubscriberServicesHome = null;
		SubscriberServices mSubscriberServices = null;
		Object rcg_obj = null;

    	rcg_obj = ctx.lookup(JNDI_NAME);
    	mSubscriberServicesHome = (SubscriberServicesHome) javax.rmi.PortableRemoteObject.narrow(rcg_obj, SubscriberServicesHome.class);
    	mSubscriberServices = mSubscriberServicesHome.create();	 
    	
		ActivityInfo activityInfo = new ActivityInfo();
		activityInfo.setNullAll();
		activityInfo.setActivityReason("RPLSUS"); //RPLSUS CREQ
		SubscriberIdInfo subscriberIdInfo = new SubscriberIdInfo();
		subscriberIdInfo.setSubscrNumber(subscriberNumber);
		mSubscriberServices.suspendSubscriber(subscriberIdInfo, null, activityInfo);
	}
	
	public Context getInitialContext_PROD(String WeblogicHost, String WeblogicBePort, String UamsUser,
			String UamsPassword, String UamsLogicalEnv, String applicationName) throws Exception
	{
		String url = "t3://" + WeblogicHost + ":" + WeblogicBePort;
//        String url1 = "t3://xtcrpl02:18011";

		String ticket = "";
		try
		{
			//ticket = getTicket("poc3","Unix11","LE_poc3_PE","RPL","hpx1604:18033");

			ticket = getTicket(UamsUser,UamsPassword, UamsLogicalEnv, applicationName, WeblogicHost + ":" + WeblogicBePort);
//			ticket = getTicket(RPLDriverConstants.WL_USER_ID,RPLDriverConstants.WL_PASSWD,RPLDriverConstants.LOGICAL_ENV,"RPL",RPLDriverConstants.UNIX_HOST + ":" + RPLDriverConstants.WL_SEC_PORT);
			//ticket = getTicket("batch_xlc96",RPLDriverConstants.WL_PASSWD,RPLDriverConstants.LOGICAL_ENV,"RPL",RPLDriverConstants.UNIX_HOST + ":" + RPLDriverConstants.WL_SEC_PORT);


			//ticket = getTicket("batch_xlc17",RPLDriverConstants.WL_PASSWD,"LE_xlc" + RPLDriverConstants.ENV_NO + "_PE","RPL",RPLDriverConstants.UNIX_HOST + ":" + RPLDriverConstants.WL_SEC_PORT);
			//ticket = getTicket("xlc96","Unix11","LE_xlc" + RPLDriverConstants.ENV_NO + "_PE","RPL","snvi019:" + RPLDriverConstants.WL_SEC_PORT);

			try
			{
				Hashtable h = new Hashtable();
				h.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
				h.put(Context.PROVIDER_URL, url);
//                h.put(Context.PROVIDER_URL, url1);
				h.put(Context.SECURITY_PRINCIPAL, ticket);
				h.put(Context.SECURITY_CREDENTIALS, "");
				return new InitialContext(h);
			}
			catch (NamingException ne)
			{
				System.out.println("Unable to get connection to weblogicserver at " + url);
				ne.printStackTrace();
				throw ne;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public static String getTicket(String aUserName, String aPassword, String aLogicalEnvironment,
			String aApplicationID, String aSecurityURL) throws Exception
	{
		DirectLoginService tDirectLoginSvc = null;

		System.setProperty("SEC_SRV_CONN", aSecurityURL);
		System.setProperty("amdocs.uams.config.resource", "res/gen/client");
		//System.setProperty("amdocs.uams.config.resource", "res/acm/appsrv");
		//System.setProperty("amdocs.security.root","/xlcuser8/xlc/aimsys/xlcwrk96/UAMS");
		//System.setProperty("amdocs.uams.startup.password","none");


		Hashtable inData = new Hashtable(2);
		inData.put("appId", aApplicationID);
		inData.put("environment", aLogicalEnvironment);
		String accessHost = null;
		try
		{
			accessHost = InetAddress.getLocalHost().getHostName();
		}
		catch (Exception x)
		{
			//x.printStackTrace();
			throw x;
		}
		try
		{
			tDirectLoginSvc = (DirectLoginService)UamsSystem.getService(null, UamsSystem.LN_UAMS_DIRECT_LOGIN);
		}
		catch (Exception x)
		{
			//x.printStackTrace();
			throw x;
		}
		UamsDirectLoginContext uamsLoginCtx = tDirectLoginSvc.login(null, aUserName, new UamsPasswordCredential(aPassword),
				null, 0L, accessHost, accessHost, inData);

		/*
		 * COMPLETE = 1 :: CONTINUE_NEEDED = 2 :: FAILURE = 3 :: REJECTED = 4
		 */

		if (uamsLoginCtx.getStatus() == UamsLoginContext.CONTINUE_NEEDED)
		{
			//if((uamsLoginCtx.getStatus() & UamsUserInfo.CREDENTIAL_EXPIRED) != 0)
			long flags = UamsAuthenticationService.SET_CREDENTIALS;
			String newPassword = "";

			if(aPassword.equals("Unix11"))
				newPassword = "Unix!11";
			else if(aPassword.equals("Unix!11"))
				newPassword = "Unix11";

			Hashtable indata = new Hashtable();
			indata.put(UamsAuthenticationService.NEW_CREDENTIAL_ENTRY_KEY, new UamsPasswordCredential(newPassword));
			// call the login service again

			System.out.println("UAMS Password Expired, So trying to change the password");

			uamsLoginCtx = tDirectLoginSvc.login(uamsLoginCtx, aUserName, new UamsPasswordCredential(aPassword),
					null, flags, accessHost, accessHost, indata);

			System.out.println("UAMS Status = " + uamsLoginCtx.getStatus()
					+ " :: User Status = " + uamsLoginCtx.getUserStatus()
					+ " :: Ticket = " + uamsLoginCtx.getTicket()
					+ " :: Message = " + uamsLoginCtx.getErrorMessage());
			return uamsLoginCtx.getTicket();
		}
		else if (uamsLoginCtx.getStatus() == UamsLoginContext.COMPLETE)
		{
			String ticket = new String(uamsLoginCtx.getTicket());
			System.out.println("Login succesful: TICKET=" + uamsLoginCtx.getTicket());
			return ticket;
		}
		else
		{
			System.out.println("UAMS Status = " + uamsLoginCtx.getStatus()
					+ " :: User Status = " + uamsLoginCtx.getUserStatus()
					+ " :: Ticket = " + uamsLoginCtx.getTicket()
					+ " :: Login Failed :: Error Msg = " + uamsLoginCtx.getErrorMessage());
			throw new Exception("UAMS Login Failed");
		}
	}
	
}
