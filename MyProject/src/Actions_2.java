
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

import amdocs.csm3g.datatypes.ActivityDateInfo;
import amdocs.csm3g.datatypes.ActivityInfo;
import amdocs.csm3g.datatypes.AddressInfo;
import amdocs.csm3g.datatypes.BusinessEntityIdInfo;
import amdocs.csm3g.datatypes.ChargeDistributionDetailsInfo;
import amdocs.csm3g.datatypes.CustomerIdInfo;
import amdocs.csm3g.datatypes.EventDistributionDetailsInfo;
import amdocs.csm3g.datatypes.ExternalIdInfo;
import amdocs.csm3g.datatypes.GuidingResourceInfo;
import amdocs.csm3g.datatypes.LogicalResourceInfo;
import amdocs.csm3g.datatypes.NameInfo;
import amdocs.csm3g.datatypes.ParameterInfo;
import amdocs.csm3g.datatypes.PayChannelIdInfo;
import amdocs.csm3g.datatypes.PhysicalResourceInfo;
import amdocs.csm3g.datatypes.ResourceInfo;
import amdocs.csm3g.datatypes.SrvAgrInfo;
import amdocs.csm3g.datatypes.SubscriberGeneralInfo;
import amdocs.csm3g.datatypes.SubscriberIdInfo;
import amdocs.csm3g.datatypes.SubscriberIdsInfo;
import amdocs.csm3g.datatypes.SubscriberTypeInfo;
import amdocs.csm3g.datatypes.UnitIdInfo;
import amdocs.csm3g.datatypes.UserGroupResourceInfo;
import amdocs.csm3g.sessions.interfaces.api.SubscriberServices;
import amdocs.csm3g.sessions.interfaces.home.SubscriberServicesHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;



public class Actions_2
{
	
	private static final String WL_MACHINE_PORT = "indlnqw038:10121";
	private static final String WL_MACHINE_PORT_EJB = "indlnqw038:10121";
	private static final String URL = "t3://indlnqw038:10121";
	private static final String user = "ABPBatchUser";

	private static final String appId = "CM";

	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1'};
	
	Hashtable env = new Hashtable(2);
		InitialContext ctx, initCtx = null;
		  //  Object obj;

	SubscriberServicesHome serviceHome;
	
    private InitialContext getInitialContext(String user, char[] password, String appId, String WL_MACHINE_PORT)
	{
		

		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, URL);
		
		
//		lr.start_transaction("CreateAccount_init");

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
		
//		lr.end_transaction("CreateAccount_init",lr.AUTO);
		
		return initCtx;
		
	}
     
	public int init() throws Throwable {

	    
     InitialContext initCtx = getInitialContext(user, password, appId, WL_MACHINE_PORT);
     
   return 0;
		
	}

	
	public int action() throws Throwable {
    	
    	  
      Object obj;
     

		
	 
		try
		{
			
			obj = initCtx.lookup("amdocsBeans.CM1SubscriberServicesHome");
			serviceHome = (SubscriberServicesHome) javax.rmi.PortableRemoteObject.narrow(obj, SubscriberServicesHome.class);
			SubscriberServices subscriberServices=serviceHome.create();
			
						
			
	CustomerIdInfo customerIdInfo=new CustomerIdInfo();
	customerIdInfo.setUnAssignedAll();
	customerIdInfo.setCustomerNoUnAssigned();
	customerIdInfo.setCustomerNo(1);

SubscriberIdInfo predefinedSubscriberIdInfo=new SubscriberIdInfo();

UnitIdInfo predefinedSubscriberUnitIdInfo=new UnitIdInfo();
predefinedSubscriberUnitIdInfo.getChNodeId();

ExternalIdInfo subscriberExternalIdInfo=new ExternalIdInfo();
subscriberExternalIdInfo.getExternalId();

UnitIdInfo unitIdInfo=new UnitIdInfo ();
unitIdInfo.setUnAssignedAll();

SubscriberTypeInfo subscriberTypeInfo=new SubscriberTypeInfo();
subscriberTypeInfo.setSubscriberType("T");

SubscriberGeneralInfo subscriberGeneralInfo=new SubscriberGeneralInfo();

NameInfo[] nameInfo=new NameInfo[1];

AddressInfo[] addressInfo=new AddressInfo[1];

SrvAgrInfo[] offers=new SrvAgrInfo[1];

LogicalResourceInfo[] logicalResourceInfo=new LogicalResourceInfo[1];

PhysicalResourceInfo[] physicalResourceInfo=new PhysicalResourceInfo[1];

ParameterInfo[] parameterInfo=new ParameterInfo[1];

PayChannelIdInfo defaultOCPayChannelInfo1=new PayChannelIdInfo();

PayChannelIdInfo defaultRCPayChannelIdInfo2=new PayChannelIdInfo();

PayChannelIdInfo primaryEventPayChannelIdInfo3=new PayChannelIdInfo();

ChargeDistributionDetailsInfo[] chargeDistributionDetailsInfo=new ChargeDistributionDetailsInfo[1];

EventDistributionDetailsInfo[] eventDistributionDetailsInfo=new EventDistributionDetailsInfo[1];

GuidingResourceInfo[] guidingResourceInfo=new GuidingResourceInfo[1];

UserGroupResourceInfo[] userGroupResourceInfo=new UserGroupResourceInfo[1];

ResourceInfo[] resourceRangeList=new ResourceInfo[1];

BusinessEntityIdInfo businessEntityIdInfo=new BusinessEntityIdInfo();

ActivityInfo activityInfo=new ActivityInfo();
activityInfo.getActivityReason();
activityInfo.getUserText();
activityInfo.getActivityPcn();
activityInfo.getActivityId();

ActivityDateInfo activityDateInfo=new ActivityDateInfo();
activityDateInfo.getActivityDate();



		SubscriberIdsInfo subscriberIdsInfo = subscriberServices.l9CreateAccountBAPCNSubscriber(customerIdInfo, null, null, null, null,subscriberTypeInfo,null,null,null,null,null,null,null,null,null,null,null,
                                                                                        null,null,null,null,null,activityInfo,null);
	
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;	
	}
        
	public int end() throws Throwable {

		return 0;
	}//end of end
	
	 public static void main(String[] args) throws Throwable{
	    	Actions_2 act = new Actions_2();
	    	
	    	act.getInitialContext(user, password, appId, WL_MACHINE_PORT);
	    	act.action();
			
		}
}

