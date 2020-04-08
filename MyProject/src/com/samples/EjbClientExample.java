package com.samples;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import amdocs.rpr.datatypes.AttributeInfo;
import amdocs.rpr.datatypes.CustomerIdInfo;
import amdocs.rpr.datatypes.EventIdInfo;
import amdocs.rpr.datatypes.EventInfo;
import amdocs.rpr.datatypes.EventListInfo;
import amdocs.rpr.datatypes.EventTypeInfo;
import amdocs.rpr.datatypes.FlexibleCycleInfo;
import amdocs.rpr.datatypes.PaginationInfo;
import amdocs.rpr.datatypes.SubscriberIdInfo;
import amdocs.rpr.exceptions.RPRException;
import amdocs.rpr.sessions.interfaces.api.EventServices;
import amdocs.rpr.sessions.interfaces.home.EventServicesHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;

public class EjbClientExample
{



	private static final String WL_MACHINE_PORT = "indlin1752:11121";
	private static final String WL_MACHINE_PORT_EJB = "indlin1752:11121";
	private static final String URL = "t3://indlin1752:11121";
	private static final String user = "ABPBatchUser";

	private static final String appId = "CM";

	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1' };

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("JRE=" + System.getProperty("java.version"));
		EjbClientExample obj = new EjbClientExample();
		InitialContext initCtx = obj.getInitialContext(user, password, appId, WL_MACHINE_PORT);

		//obj.getRatedEvents(initCtx);
		obj.getEventDetails(initCtx);

	}
	
	private void getRatedEvents(InitialContext initCtx) {
		Object obj;

		EventServicesHome serviceHome;
		try
		{

			obj = initCtx.lookup("amdocsBeans.RPR1EventServicesHome");
			serviceHome = (EventServicesHome) javax.rmi.PortableRemoteObject.narrow(obj, EventServicesHome.class);
			EventServices eventServices = serviceHome.create();
			
			CustomerIdInfo customerInfo = new CustomerIdInfo();
			customerInfo.setCustomerId(508);
			
			SubscriberIdInfo subscriberInfo = new SubscriberIdInfo();
			subscriberInfo.setSubscriberId(50815);
			
			FlexibleCycleInfo cycInfo = new FlexibleCycleInfo();
			cycInfo.setCycleCode((short)2);
			cycInfo.setCycleYear((short)2014);
			cycInfo.setCycleInstance((short)2);
			
			PaginationInfo pageInfo = new PaginationInfo();
			pageInfo.setPageSize(10);
			pageInfo.setPageNumber(1);
			EventListInfo eventListInfo = eventServices.getRatedEvents(customerInfo, null, subscriberInfo, cycInfo, null, null, null, null,null,pageInfo);
			EventInfo[] eventInfo = eventListInfo.getEventInfo();
			for (int i = 0; i < eventInfo.length; i++) {
				AttributeInfo[] attList = eventInfo[i].getAttributeList();
				for (int j = 0; j < attList.length; j++) {
					System.out.println(attList[i].getAttributeName() + "->" +attList[i].getAttributeValue());
				}
			}
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (RPRException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void getEventDetails(InitialContext initCtx) {
		
		String customerId = "508";
		String subscriberId = "50815";
		long eventID = 46;
		String cycleCode = "2";
		String CycleInstance = "2";
		String cycleYear = "2014";
		
		CustomerIdInfo customerIdInfo=new CustomerIdInfo();
		customerIdInfo.setCustomerId(Integer.parseInt(customerId));
		SubscriberIdInfo subscriberIdInfo=new SubscriberIdInfo();
		subscriberIdInfo.setSubscriberId(Integer.parseInt(subscriberId));
		EventIdInfo eventIdInfo=new EventIdInfo();
		eventIdInfo.setEventId(eventID);
		FlexibleCycleInfo flexibleCycleInfo=new FlexibleCycleInfo();
		flexibleCycleInfo.setCycleCode((short)Integer.parseInt(cycleCode));
		flexibleCycleInfo.setCycleInstance((short)Integer.parseInt(CycleInstance));
		flexibleCycleInfo.setCycleYear((short)Integer.parseInt(cycleYear));
		EventTypeInfo eventTypeInfo=new EventTypeInfo();
		eventTypeInfo.setType(0);
		
		Object obj;

		EventServicesHome serviceHome;
		EventInfo eventInfo = null;
		try
		{
			obj = initCtx.lookup("amdocsBeans.RPR1EventServicesHome");
			serviceHome = (EventServicesHome) javax.rmi.PortableRemoteObject.narrow(obj, EventServicesHome.class);
			EventServices eventServices = serviceHome.create();
			 eventInfo=eventServices.getEventDetails(customerIdInfo, subscriberIdInfo, eventIdInfo, flexibleCycleInfo, eventTypeInfo, null);
		}catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (RPRException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		if (eventInfo!=null) {
			System.out.println("Event Info"+eventInfo);
		}else {
			System.out.println("Nothing found");
		}
	}

	/**
	 * @return
	 */
	private InitialContext getInitialContext(String user, char[] password, String appId, String WL_MACHINE_PORT)
	{
		Hashtable env = new Hashtable(2);
		InitialContext ctx, initCtx = null;

		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, URL);

		try
		{
			ctx = new InitialContext(env);

			Object obj = ctx.lookup(UamsEjbBasicLoginHome.JNDI_NAME);
			UamsEjbBasicLoginHome home = (UamsEjbBasicLoginHome) PortableRemoteObject.narrow(obj, UamsEjbBasicLoginHome.class);

			UamsEjbBasicLogin remote = (UamsEjbBasicLogin) PortableRemoteObject.narrow(home.create(), UamsEjbBasicLogin.class);

			System.out.println("Input user name=" + user);
			System.out.println("Input password=" + String.valueOf(password));

			String ticket = remote.login(user, password, appId);
			System.out.println("Output uams ticket=" + ticket);
			System.out.println("Input appId=" + appId);
			
			//boolean isLogout = remote.logout(ticket);
			//System.out.println("Logout =" + isLogout);
			// if the ticket was created procceed
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
		}
		return initCtx;
	}

}

