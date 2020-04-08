

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

import amdocs.csm3g.datatypes.ActivityInfo;
import amdocs.csm3g.datatypes.CustomerIdInfo;
import amdocs.csm3g.datatypes.CustomerTypeInfo;
import amdocs.csm3g.datatypes.PayChannelIdInfo;
import amdocs.csm3g.datatypes.SubscriberIdInfo;
import amdocs.csm3g.sessions.interfaces.api.SaleEventConv;
import amdocs.csm3g.sessions.interfaces.home.SaleEventConvHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;

public class SaleEventConvCustId {

	/*private static final String WL_MACHINE_PORT = "indlin1752:21121";
	private static final String WL_MACHINE_PORT_EJB = "indlin1752:21121";
	private static final String URL = "t3://indlin1752:21121";*/
	private static final String user = "ABPBatchUser";
	
	private static final String WL_MACHINE_PORT = "indlnqw090:11601";
	private static final String WL_MACHINE_PORT_EJB = "indlnqw090:11601";
	private static final String URL = "t3://indlnqw090:11601";

	private static final String appId = "CM";

	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1' };

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("JRE=" + System.getProperty("java.version"));
		SaleEventConvCustId obj = new SaleEventConvCustId();
		InitialContext initCtx = obj.getInitialContext(user, password, appId, WL_MACHINE_PORT);

		obj.invokeSaleEvent(initCtx, args);
	}
		
	private void invokeSaleEvent(InitialContext initCtx, String[] args) {
		
		String customerId = "752";
		String subscriberId = "234";
		String pcnId = "753";
		
		CustomerIdInfo customerIdInfo=new CustomerIdInfo();
		customerIdInfo.setCustomerNo(Integer.parseInt(customerId));
		
		SubscriberIdInfo subscriberIdInfo=new SubscriberIdInfo();
		subscriberIdInfo.setSubscrNumber(Integer.parseInt(subscriberId));
		
		PayChannelIdInfo pcnIdInfo=new PayChannelIdInfo();
		pcnIdInfo.setPayChannelId(Integer.parseInt(pcnId));
		
		ActivityInfo actInfo = new ActivityInfo();
		actInfo.setActivityReason("CREQ");
		actInfo.setCmDbEntityLockingArrayUnAssigned();
		actInfo.setEnclosedClientInfoUnAssigned();
		actInfo.setEnclosedMassActivityInfoUnAssigned();
		actInfo.setLockInfoUnAssigned();
		
		Object obj;

		SaleEventConvHome serviceHome;
		//EventInfo eventInfo = null;
		try
		{
			obj = initCtx.lookup("amdocsBeans.CM1SaleEventConvHome");
			serviceHome = (SaleEventConvHome) javax.rmi.PortableRemoteObject.narrow(obj, SaleEventConvHome.class);
			SaleEventConv saleServices = serviceHome.create();
			
			saleServices.setSubscriberIdentifier(subscriberIdInfo);
			saleServices.setCustomerIdInfo(customerIdInfo);
			saleServices.setPrimaryEventDistribution(pcnIdInfo);
			
			System.out.println(saleServices.activateSubscriber(actInfo));
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
			// if the ticket was created procceed
			Hashtable ht = new Hashtable();

			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ht.put(Context.PROVIDER_URL, "t3://" + WL_MACHINE_PORT_EJB);
			ht.put(Context.SECURITY_CREDENTIALS, "");
			ht.put(Context.SECURITY_PRINCIPAL, ticket);
			System.out.println("*****"+Thread.currentThread().activeCount());
			initCtx = new InitialContext(ht);
			System.out.println("*****"+Thread.currentThread().activeCount());
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

