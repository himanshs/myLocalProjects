import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import amdocs.csm3g.datatypes.ActivityInfo;
import amdocs.csm3g.datatypes.CustomerBillingCycleInfo;
import amdocs.csm3g.datatypes.CustomerTypeInfo;
import amdocs.csm3g.datatypes.EnclosedClientInfo;
import amdocs.csm3g.datatypes.SubscriberIdInfo;
import amdocs.csm3g.sessions.interfaces.api.SaleEventConv;
import amdocs.csm3g.sessions.interfaces.home.SaleEventConvHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;

public class SaleEventConvCustType
{

            public SaleEventConvCustType()
            {
            }

            public static void main(String args[])
            {
/*  41*/        WL_MACHINE_PORT_EJB = System.getProperty("SEC_SRV_CONN");
/*  43*/        System.out.println((new StringBuilder("Weblogic connection :-")).append(WL_MACHINE_PORT_EJB).toString());
/*  44*/        System.out.println((new StringBuilder("JRE=")).append(System.getProperty("java.version")).toString());
/*  45*/        SaleEventConvCustType obj = new SaleEventConvCustType();
/*  46*/        InitialContext initCtx = obj.getInitialContext("ABPBatchUser", password, "CM");
/*  48*/        obj.invokeSaleEvent(initCtx, args);
            }

            private void invokeSaleEvent(InitialContext initCtx, String args[])
            {
/*  55*/        String subscriberId = args[0];
/*  56*/        System.out.println((new StringBuilder("Subscriber Id")).append(subscriberId).toString());
/*  58*/        SubscriberIdInfo subscriberIdInfo = new SubscriberIdInfo();
/*  59*/        subscriberIdInfo.setSubscrNumber(Integer.parseInt(subscriberId));
/*  61*/        CustomerTypeInfo custInfo = new CustomerTypeInfo();
/*  62*/        custInfo.setCustomerType((byte)'R');
/*  63*/        custInfo.setCustomerSubtype("I");
/*  65*/        CustomerBillingCycleInfo customerIdInfo = new CustomerBillingCycleInfo();
/*  69*/        ActivityInfo actInfo = new ActivityInfo();
/*  70*/        actInfo.setActivityReason("CREQ");
/*  71*/        actInfo.setCmDbEntityLockingArrayUnAssigned();
/*  72*/        actInfo.setEnclosedClientInfoUnAssigned();
/*  73*/        actInfo.setEnclosedMassActivityInfoUnAssigned();
/*  74*/        actInfo.setLockInfoUnAssigned();
				
				SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				EnclosedClientInfo enclosedClientInfo = new EnclosedClientInfo();
				enclosedClientInfo.setNullAll();
				try {
					enclosedClientInfo.setLogicalDate(SDF.parse("2015/01/01 00:00:01"));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
/*  82*/        try
                {
/*  82*/            Object obj = initCtx.lookup("amdocsBeans.CM1SaleEventConvHome");
/*  83*/            SaleEventConvHome serviceHome = (SaleEventConvHome)PortableRemoteObject.narrow(obj, SaleEventConvHome.class);
/*  84*/            SaleEventConv saleServices = serviceHome.create();
					saleServices.setEnclosedClientInfo(enclosedClientInfo);
/*  86*/            saleServices.setSubscriberIdentifier(subscriberIdInfo);
/*  87*/            saleServices.setCustomerTypeInfo(custInfo);
/*  89*/            System.out.println(saleServices.activateSubscriber(actInfo));

                }
/*  91*/        catch(NamingException e)
                {
/*  93*/            e.printStackTrace();
                }
/*  95*/        catch(Exception e)
                {
/*  97*/            System.out.println(e.getMessage());
/*  98*/            e.printStackTrace();
                }
            }

            private InitialContext getInitialContext(String user, char password[], String appId)
            {
/* 107*/        Hashtable env = new Hashtable(2);
/* 108*/        InitialContext initCtx = null;
/* 110*/        env.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
/* 111*/        env.put("java.naming.provider.url", (new StringBuilder("t3://")).append(WL_MACHINE_PORT_EJB).toString());
/* 115*/        try
                {
/* 115*/            InitialContext ctx = new InitialContext(env);
/* 117*/            Object obj = ctx.lookup("UamsEjbBasicLogin");
/* 118*/            UamsEjbBasicLoginHome home = (UamsEjbBasicLoginHome)PortableRemoteObject.narrow(obj, UamsEjbBasicLoginHome.class);
/* 120*/            UamsEjbBasicLogin remote = (UamsEjbBasicLogin)PortableRemoteObject.narrow(home.create(), UamsEjbBasicLogin.class);
/* 122*/            System.out.println((new StringBuilder("Input user name=")).append(user).toString());
/* 123*/            System.out.println((new StringBuilder("Input password=")).append(String.valueOf(password)).toString());
/* 125*/            String ticket = remote.login(user, password, appId);
/* 126*/            System.out.println((new StringBuilder("Output uams ticket=")).append(ticket).toString());
/* 127*/            System.out.println((new StringBuilder("Input appId=")).append(appId).toString());
/* 132*/            Hashtable ht = new Hashtable();
/* 134*/            ht.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
/* 135*/            ht.put("java.naming.provider.url", (new StringBuilder("t3://")).append(WL_MACHINE_PORT_EJB).toString());
/* 136*/            ht.put("java.naming.security.credentials", "");
/* 137*/            ht.put("java.naming.security.principal", ticket);
/* 138*/            initCtx = new InitialContext(ht);
                }
/* 141*/        catch(NamingException e)
                {
/* 143*/            e.printStackTrace();
                }
/* 145*/        catch(ClassCastException e)
                {
/* 147*/            e.printStackTrace();
                }
/* 149*/        catch(RemoteException e)
                {
/* 151*/            e.printStackTrace();
                }
/* 153*/        catch(CreateException e)
                {
/* 155*/            e.printStackTrace();
                }
/* 157*/        catch(CredentialExpiredException e)
                {
/* 159*/            e.printStackTrace();
                }
/* 161*/        catch(FailedLoginException e)
                {
/* 163*/            e.printStackTrace();
                }
/* 165*/        catch(LoginException e)
                {
/* 167*/            e.printStackTrace();
                }
/* 169*/        return initCtx;
            }

            private static String WL_MACHINE_PORT_EJB ;
            private static final String user = "ABPBatchUser";
            private static final String appId = "CM";
            private static final char password[] = {
/*  34*/        'U', 'n', 'i', 'x', '1', '1'
            };

}