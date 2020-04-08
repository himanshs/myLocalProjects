

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import amdocs.bl3g.sessions.interfaces.api.BL9ChargeServices;
import amdocs.bl3g.sessions.interfaces.home.BL9ChargeServicesHome;
import amdocs.csm3g.datatypes.ActivityInfo;
import amdocs.csm3g.datatypes.CustomerTypeInfo;
import amdocs.csm3g.datatypes.EnclosedClientInfo;
import amdocs.csm3g.datatypes.SubscriberIdInfo;
import amdocs.csm3g.sessions.interfaces.home.SaleEventConvHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;

public class BL9GetUnbilledRC {

	//private static final String WL_MACHINE_PORT = "indlin1942:11121";
	//private static final String WL_MACHINE_PORT_EJB = "indlin1942:11121";
	//private static final String URL = "t3://indlin1942:11121";
	
	private static final String WL_MACHINE_PORT = "illin2493:40121";
	private static final String WL_MACHINE_PORT_EJB = "illin2493:40121";
	private static final String URL = "t3://illin2493:40121";
	
	private static final String user = "ABPBatchUser";

	private static final String appId = "BL";

	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1' };

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("JRE=" + System.getProperty("java.version"));
		BL9GetUnbilledRC obj = new BL9GetUnbilledRC();
		InitialContext initCtx = obj.getInitialContext(user, password, appId, WL_MACHINE_PORT);

		obj.invokeSaleEvent(initCtx, args);
	}
		
	private void invokeSaleEvent(InitialContext initCtx, String[] args) {
		
		String subscriberId = "62";
		
		ActivityInfo actInfo = new ActivityInfo();
		actInfo.setActivityReason("CREQ");
		actInfo.setCmDbEntityLockingArrayUnAssigned();
		//actInfo.setEnclosedClientInfoUnAssigned();
		
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		EnclosedClientInfo enclosedClientInfo = new EnclosedClientInfo();
		enclosedClientInfo.setNullAll();
		try {
			enclosedClientInfo.setLogicalDate(SDF.parse("2015/02/20 01:09:46"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		actInfo.setEnclosedClientInfo(enclosedClientInfo);
		
		actInfo.setEnclosedMassActivityInfoUnAssigned();
		actInfo.setLockInfoUnAssigned();
		
		SubscriberIdInfo subscriberIdInfo=new SubscriberIdInfo();
		subscriberIdInfo.setSubscrNumber(Integer.parseInt(subscriberId));
		
		CustomerTypeInfo custInfo = new CustomerTypeInfo();
		custInfo.setCustomerType((byte)'I');
		custInfo.setCustomerSubtype("N");
		
		Object obj;

		BL9ChargeServicesHome serviceHome;
		//EventInfo eventInfo = null;
		try
		{
			obj = initCtx.lookup("amdocsBeans.BL9ChargeServicesBean");
			serviceHome = (BL9ChargeServicesHome) javax.rmi.PortableRemoteObject.narrow(obj, BL9ChargeServicesHome.class);
			BL9ChargeServices saleServices = serviceHome.create();
									
			System.out.println(saleServices.getUnbilledRcCharges(null, null, null, null, null));
			
		}catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
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
			// if the ticket was created proceed
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

