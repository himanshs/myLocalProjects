import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import amdocs.csm3g.datatypes.BusinessEntityIdInfo;
import amdocs.csm3g.datatypes.LogicalResourceInfo;
import amdocs.csm3g.datatypes.ParameterInfo;
import amdocs.csm3g.datatypes.PhysicalResourceInfo;
import amdocs.csm3g.datatypes.SrvAgrInfo;
import amdocs.csm3g.datatypes.SubscriberIdInfo;
import amdocs.csm3g.datatypes.SubscriberTypeInfo;
import amdocs.csm3g.sessions.interfaces.api.SubscriberServices;
import amdocs.csm3g.sessions.interfaces.home.SubscriberServicesHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;


public class SubscriberServicesCreatePreActivateSubsctiber {
	  private static String WL_MACHINE_PORT_EJB = "indlin1752:11121";
	  private static final char[] password = { 
	    'U', 'n', 'i', 'x', '1', '1' };

	  public static void main(String[] args)
	  {
	    WL_MACHINE_PORT_EJB = System.getProperty("SEC_SRV_CONN");
	    System.out.println("Weblogic connection :-" + WL_MACHINE_PORT_EJB);
	    System.out.println("JRE=" + System.getProperty("java.version"));
	    SubscriberServicesCreatePreActivateSubsctiber obj = new SubscriberServicesCreatePreActivateSubsctiber();
	    InitialContext initCtx = obj.getInitialContext("ABPBatchUser", password, "CM");
	    obj.invokeSaleEvent(initCtx, args);
	  }

	  private void invokeSaleEvent(InitialContext initCtx, String[] args)
	  {
		  SubscriberTypeInfo subscriberTypeInfo = new SubscriberTypeInfo();
		  SrvAgrInfo pricePlanInfo = new SrvAgrInfo();
		  LogicalResourceInfo  [] logicalResourceInfo = new LogicalResourceInfo[2];
		  PhysicalResourceInfo  [] physicalResourceInfo = new PhysicalResourceInfo[1];
		  ParameterInfo[] initialBalParam = new ParameterInfo[1];
		  
		if (args.length < 1){
			 System.out.println("the input parameters must have 6, \n the only input parameter  as below: \nsubscriberType,pricePlan,msisdn,imsi,iccid,initialBalance");
			 return;
		}
		//T,119427,123454555,12345675555,12345678555,300 
		String inputP = args[0];
		String[] inputParameter = inputP.split(",");
		
	    String subscriberType = inputParameter[0];
	    String pricePlan = inputParameter[1];
	    String msisdn = inputParameter[2];
	    String imsi  = inputParameter[3];
	    String iccid = inputParameter[4];
	    String initBal = inputParameter[5];
	    
	    System.out.println("Subscriber type: " + subscriberType);
	    System.out.println("pricePlan: " + pricePlan);
	    System.out.println("msisdn: " + msisdn);
	    System.out.println("imsi: " + imsi);
	    System.out.println("iccid: " + iccid);
	    System.out.println("initBal: " + initBal);
	    
	    subscriberTypeInfo.setSubscriberType(subscriberType);
	    
		int offerInstanceId = 1;
		pricePlanInfo.setSoc(pricePlan);
		pricePlanInfo.setOfferInstanceId(offerInstanceId);
		
		logicalResourceInfo[0] = new LogicalResourceInfo();
		logicalResourceInfo[0].setValues ( new String[]{msisdn} );	
		
		logicalResourceInfo[1] = new LogicalResourceInfo();
		logicalResourceInfo[1].setValues ( new String[]{imsi} );    	
		
		physicalResourceInfo[0] = new PhysicalResourceInfo();
		physicalResourceInfo[0].setValues(new String[]{iccid});


		if("T".equals(subscriberType)){
			logicalResourceInfo[0].setName("3GMDN");
			logicalResourceInfo[1].setName("3GIMSI");
			physicalResourceInfo[0].setName("3ICCID");
		}else if("L".equals(subscriberType)){
			logicalResourceInfo[0].setName("4GMDN");
			logicalResourceInfo[1].setName("4GIMSI");
			physicalResourceInfo[0].setName("4ICCID");
		}else{
			logicalResourceInfo[0].setName("MSISDN");
			logicalResourceInfo[1].setName("IMSI");
			physicalResourceInfo[0].setName("ICCID");
		}

		initialBalParam[0] = new ParameterInfo();
		initialBalParam[0].setOfferInstanceId(offerInstanceId);
		initialBalParam[0].setName("Initial balance");
		initialBalParam[0].setValues(new String[]{initBal});
		
		BusinessEntityIdInfo subBusinessEntityidInfo = new BusinessEntityIdInfo();
		subBusinessEntityidInfo.setBusinessEntityId(110154);
	    
	 
	    try
	    {
	      Object obj = initCtx.lookup("amdocsBeans.CM1SubscriberServicesHome");
	      SubscriberServicesHome serviceHome = (SubscriberServicesHome)PortableRemoteObject.narrow(obj, SubscriberServicesHome.class);
	      SubscriberServices subscriberServices = serviceHome.create();
	      SubscriberIdInfo subsctiberIdInfo = subscriberServices.createPreActivatedSubscriber(null, subBusinessEntityidInfo, subscriberTypeInfo, null, null, pricePlanInfo, null, initialBalParam, logicalResourceInfo, physicalResourceInfo, null, null, null);

	      
	      System.out.println("the created pre-activated subsctiber: \n" + subsctiberIdInfo);
	    }
	    catch (NamingException e)
	    {
	      e.printStackTrace();
	    }
	    catch (Exception e)
	    {
	      System.out.println(e.getMessage());
	      e.printStackTrace();
	    }
	  }

	  private InitialContext getInitialContext(String user, char[] password, String appId)
	  {
	    Hashtable env = new Hashtable(2);
	    InitialContext initCtx = null;
	    env.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
	    env.put("java.naming.provider.url", "t3://" + WL_MACHINE_PORT_EJB);
	    try
	    {
	      InitialContext ctx = new InitialContext(env);
	      Object obj = ctx.lookup("UamsEjbBasicLogin");
	      UamsEjbBasicLoginHome home = (UamsEjbBasicLoginHome)PortableRemoteObject.narrow(obj, UamsEjbBasicLoginHome.class);
	      UamsEjbBasicLogin remote = (UamsEjbBasicLogin)PortableRemoteObject.narrow(home.create(), UamsEjbBasicLogin.class);
	      /*System.out.println("Input user name=" + user);
	      System.out.println("Input password=" + String.valueOf(password));*/
	      String ticket = remote.login(user, password, appId);
	     /* System.out.println("Output uams ticket=" + ticket);
	      System.out.println("Input appId=" + appId);*/
	      Hashtable ht = new Hashtable();
	      ht.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
	      ht.put("java.naming.provider.url", "t3://" + WL_MACHINE_PORT_EJB);
	      ht.put("java.naming.security.credentials", "");
	      ht.put("java.naming.security.principal", ticket);
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
