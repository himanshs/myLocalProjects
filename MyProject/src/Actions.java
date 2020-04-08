
import java.rmi.RemoteException;
import java.util.Date;
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
import amdocs.csm3g.datatypes.CreditInfo;
import amdocs.csm3g.datatypes.CustomerBillingCycleInfo;
import amdocs.csm3g.datatypes.CustomerBillingInfo;
import amdocs.csm3g.datatypes.CustomerGeneralInfo;
import amdocs.csm3g.datatypes.CustomerIdInfo;
import amdocs.csm3g.datatypes.CustomerIdsInfo;
import amdocs.csm3g.datatypes.CustomerTypeInfo;
import amdocs.csm3g.datatypes.ExternalIdInfo;
import amdocs.csm3g.datatypes.NameInfo;
import amdocs.csm3g.datatypes.UnitIdInfo;
import amdocs.csm3g.sessions.interfaces.api.CustomerServices;
import amdocs.csm3g.sessions.interfaces.home.CustomerServicesHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;



public class Actions
{
	
	private static final String WL_MACHINE_PORT = "indlnqw038:10121";
	private static final String WL_MACHINE_PORT_EJB = "indlnqw038:10121";
	private static final String URL = "t3://indlnqw038:10121";
	private static final String user = "ABPBatchUser";

	private static final String appId = "CM";

	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1'};
	
	Hashtable env = new Hashtable(2);
	InitialContext ctx, initCtx = null;
	Object obj;

    CustomerServicesHome serviceHome;
    
    public static void main(String[] args) throws Throwable{
    	Actions act = new Actions();
    	
    	act.getInitialContext(user, password, appId, WL_MACHINE_PORT);
    	act.action();
		
	}
	
    private InitialContext getInitialContext(String user, char[] password, String appId, String WL_MACHINE_PORT)
	{
		

		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, URL);
		
		
		//lr.start_transaction("FET_CreateCustomer_init");

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
		
		//lr.end_transaction("FET_CreateCustomer_init",lr.AUTO);
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
			
			//obj = initCtx.lookup("amdocsBeans.RPR1PerformanceIndicatorServicesHome");
			obj = initCtx.lookup("amdocsBeans.CM1CustomerServicesHome");
			serviceHome = (CustomerServicesHome) javax.rmi.PortableRemoteObject.narrow(obj, CustomerServicesHome.class);
			CustomerServices customerServices=serviceHome.create();
			
			

			CustomerIdInfo customerIdInfo=new CustomerIdInfo();
			customerIdInfo.setUnAssignedAll();
			customerIdInfo.setCustomerNoUnAssigned();
			customerIdInfo.setCustomerNo(123456789);
			
			UnitIdInfo unitIdInfo=new UnitIdInfo ();
			unitIdInfo.setUnAssignedAll();
			unitIdInfo.setChNodeId(1234);
			
			CustomerTypeInfo customerTypeInfo=new CustomerTypeInfo ();
			//customerTypeInfo.customerType();
			customerTypeInfo.setCustomerType((byte)'R');
			
			ActivityInfo activityInfo=new ActivityInfo();
			activityInfo.setActivityReason("CREQ");
			//activityInfo.setActivityId(13245);
			
			ActivityDateInfo activityDateInfo=new ActivityDateInfo();
			activityDateInfo.setActivityDate(new Date());
			activityDateInfo.setUnAssignedAll();
			
			
			ExternalIdInfo paramExternalIdInfo=new ExternalIdInfo();
			NameInfo[] paramArrayOfNameInfo=new NameInfo[1];
			AddressInfo[] paramArrayOfAddressInfo=new AddressInfo[1];
			CustomerBillingCycleInfo paramCustomerBillingCycleInfo=new CustomerBillingCycleInfo();
			paramCustomerBillingCycleInfo.setBillCycleNo((short)1);
			CustomerBillingInfo paramCustomerBillingInfo=new CustomerBillingInfo();
			CustomerGeneralInfo paramCustomerGeneralInfo=new CustomerGeneralInfo();
			CreditInfo paramCreditInfo=new CreditInfo();
			BusinessEntityIdInfo paramBusinessEntityIdInfo=new BusinessEntityIdInfo();

//lr.start_transaction("FET_CreateCustomer");
			
			

			//customerIdInfo = CustomerServices.createNewCustomer(CustomerNo,chNodeId,customerType,userText,activityId,activityDate
			
			//customerIdInfo= CustomerServices.createNewCustomer(null,customerIdInfo,unitIdInfo,null,null,null,null,null,null,null,customerTypeInfo,activityInfo,activityDateInfo);
			/*customerIdInfo= CustomerServices.createNewCustomer(paramExternalIdInfo,customerIdInfo,unitIdInfo,customerTypeInfo,paramArrayOfNameInfo,paramArrayOfAddressInfo,
			                                                 paramCustomerBillingCycleInfo,paramCustomerBillingInfo,paramCustomerGeneralInfo,paramCreditInfo,paramBusinessEntityIdInfo,activityInfo,activityDateInfo);*/
			
			CustomerIdsInfo customerIdsInfo= customerServices.createNewCustomer(null,null,null,customerTypeInfo,null,null,
					paramCustomerBillingCycleInfo,null,null,null,null,activityInfo,null);
			
			//lr.end_transaction("FET_CreateCustomer",lr.AUTO);
			//PerformanceIndicatorsInfo performanceIndicatorInfo = performanceIndicatorServices.getRatedPerformanceIndicators(customerInfo, agreementIdInfo, cycInfo, paramPICategoryInfo, paramExternalStructuresInfo,paramAdditionalQueryParameters,pageInfo);//pageInfo
			System.out.println(customerIdsInfo);
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
}
