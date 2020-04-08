

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

import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;

public class UamsLoginTest {

                public static void main(String[] args) throws NamingException, ClassCastException, RemoteException, CreateException, CredentialExpiredException, FailedLoginException, LoginException {
                                // TODO Auto-generated method stub
                                System.out.println("Started ");
                                String user="amcAdmin";
                                char [] ch={'A','d','m','i','n','1','1'}; 
                                
                                Hashtable<String,String> env = new Hashtable<String,String>(2);
                                
                                Hashtable ht = new Hashtable();

                                env.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
                                //env.put(Context.PROVIDER_URL, "t3://10.234.168.115:22111");
                                env.put(Context.PROVIDER_URL, "t3://indlin3224:40111");
                                //env.put(Context.PROVIDER_URaL, "t3://localhost:7070");
                                InitialContext ctx = new InitialContext(env);
                                System.out.println(UamsEjbBasicLoginHome.JNDI_NAME);
                                
                                Object obj = ctx.lookup(UamsEjbBasicLoginHome.JNDI_NAME);
                                UamsEjbBasicLoginHome home =
                                (UamsEjbBasicLoginHome) PortableRemoteObject.narrow (obj, UamsEjbBasicLoginHome.class);
                                
                                UamsEjbBasicLogin remote= (UamsEjbBasicLogin)PortableRemoteObject.narrow(home.create(), UamsEjbBasicLogin.class);
                                
                                String ticket = remote.login(user,ch,"App");
                                if(!remote.isValid(ticket)){
                                                
                                                System.out.println("invalid ticket "+ticket);
                                }
                                
                                else
                                System.out.println("valid ticket "+ticket);
                }

}
