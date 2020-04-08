
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import amdocs.acmarch.exceptions.ACMException;
import amdocs.rpm.datatypes.BankRechargeMethodInfo;
import amdocs.rpm.datatypes.BucketBalanceInfo;
import amdocs.rpm.datatypes.BucketIdentInfo;
import amdocs.rpm.datatypes.CMChannelInfo;
import amdocs.rpm.datatypes.CRMChannelInfo;
import amdocs.rpm.datatypes.CashRechargeMethodInfo;
import amdocs.rpm.datatypes.CreditCardRechargeMethodInfo;
import amdocs.rpm.datatypes.DatesRangeInfo;
import amdocs.rpm.datatypes.IncreaseRechargeMethodInfo;
import amdocs.rpm.datatypes.PcnInfo;
import amdocs.rpm.datatypes.RPL3AdjustMethodInfo;
import amdocs.rpm.datatypes.RPL3WEBChannelInfo;
import amdocs.rpm.datatypes.RcgErrorHeader;
import amdocs.rpm.datatypes.RechargeHeader;
import amdocs.rpm.datatypes.RechargeResult;
import amdocs.rpm.datatypes.ReduceRechargeMethodInfo;
import amdocs.rpm.datatypes.SetBalExpDateByDateRechargeMethodInfo;
import amdocs.rpm.datatypes.SetBalExpDateByDaysRechargeMethodInfo;
import amdocs.rpm.datatypes.SubscriberIdentInfo;
import amdocs.rpm.datatypes.SubscriberPrimaryResourceInfo;
import amdocs.rpm.datatypes.VoucherInfo;
import amdocs.rpm.datatypes.VoucherRechargeMethodInfo;
import amdocs.rpm.domains.DOMAINrpl1paysrtp;
import amdocs.rpm.domains.DOMAINyesnoind;
import amdocs.rpm.sessions.interfaces.api.RPL1BucketServices;
import amdocs.rpm.sessions.interfaces.api.RPL1RechargeServices;
import amdocs.rpm.sessions.interfaces.api.RPL1SubscriberServices;
import amdocs.rpm.sessions.interfaces.home.RPL1BucketServicesHome;
import amdocs.rpm.sessions.interfaces.home.RPL1RechargeServicesHome;
import amdocs.rpm.sessions.interfaces.home.RPL1SubscriberServicesHome;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLogin;
import amdocs.uamsx.login.ejb.basic.UamsEjbBasicLoginHome;
import amdocs.vm.sessions.interfaces.api.RefDataManager;
import amdocs.vm.sessions.interfaces.home.RefDataManagerHome;

public class RPLClient {

	private static final String REC_JNDI_NAME = "amdocsBeans.RPL1RechargeServicesHome";
	private static final String SUB_JNDI_NAME = "amdocsBeans.RPL1SubscriberServicesHome";
	private static final String BUCK_JNDI_NAME = "amdocsBeans.RPL1BucketServicesHome";
	
	static InitialContext ctx, initCtx = null;
	//private static final String WL_SERVER_URL = "indlin542:31131";
	//private static final String SecUser = "glb14";
	
//	private static final String WL_SERVER_URL = "illin1834:10011";
//	private static final String SecUser = "hsd1";	
//	private static final String WL_SERVER_URL = "illin1954:12051";
//	private static final String SecUser = "cnt2";
	
	
	private static final String WL_SERVER_URL = "indlin1752:25121";
	
	private static final String user = "ABPBatchUser";

	private static final String appId = "RPL";

	private static final char[] password = new char[] { 'U', 'n', 'i', 'x', '1', '1' };
	
	public static void main(String args[]) 
	{
					
		RPL1RechargeServices a = getRechargeServices();
		RPL1SubscriberServices b = getSubscriberServices();	
	//	getRefDataManager();
		
		RPL1BucketServices bucketService = getBucketServices();
		
		 
		
	
		//BucketBalanceInfo balanceInfo =  queryBalance(bucketService);
		/*System.out.println("PCN:- " + balanceInfo.getBucketHeader().getBucketIdInfo().getPcn());
		System.out.println("amount:- " + balanceInfo.getOlcQueryBalanceInfo().getBalanceAmount());
		System.out.println("exp date:- " + balanceInfo.getBucketHeader().getBucketRcgDateInfo().getBalExpDate());
		System.out.println("balance status:- " + balanceInfo.getOlcQueryBalanceInfo().getStatus());
		*/
		/*BucketBalanceInfo[] balanceInfo =queryBalanceBySubscriberId(b);
				System.out.println("PCN:- " + balanceInfo[0].getBucketHeader().getBucketIdInfo().getPcn());
		System.out.println("amount:- " + balanceInfo[0].getOlcQueryBalanceInfo().getBalanceAmount());
		System.out.println("exp date:- " + balanceInfo[0].getBucketHeader().getBucketRcgDateInfo().getBalExpDate());
		System.out.println("balance status:- " + balanceInfo[0].getOlcQueryBalanceInfo().getStatus()); */
		
		/*RechargeResult result =  cashRecharge(a);
		System.out.println("PCN:- " + result.getRechargeBucketInfo().getPcn());*/
		
		
		RechargeResult resultUpdateExpdate = updateExpirationDateByDate(a);
		
		//RechargeResult resultUpdateExpdate = updateExpirationDateByDays(a);
		
		//negativeAdjustment(a);
		
		//balanceTransfer(a);
		//creditCardRecharge(a);
		//BankRecharge(a);
		//positiveAdjustment(a);
		
		//cashRecharge(a);
		//viewRecharge(a);
		//l9ResumeSuspend(b);
		//getVoucher(a);
		
		//b[0].getName()
		
		
		/*VoucherInfo[] a = searchServicesLI.l9CallCardVoucherSearch(callCard);
		//VoucherInfo[] a = searchServicesLI.searchVoucher(voucherSerialNumberInfo, voucherSelectCriteriaInfo);
		System.out.print("Voucher status"+a[0].getVoucherStatus());
		System.out.print("Voucher dealer code"+a[0].getDealerCode());
		System.out.print("Voucher pin"+a[0].getEncPinNumber());*/
		
	}
	/*public static String getTicket() 
	{
		System.out.println(SecUser + SecPassword + applicationId + SecEnv);
		DirectLoginServiceWrapper directLoginWraper = null;
		
		try 
		{
			System.out.println("before");
			directLoginWraper = (DirectLoginServiceWrapper) UamsSystem.getService(null, "DirectLogin");
			System.out.println("after");
		} 
		catch (Exception th) 
		{
			System.out.println(th);
		}
		UamsLoginContext uamsLoginCtx = directLoginWraper.login(SecUser, SecPassword, applicationId, SecEnv);
		
		if (uamsLoginCtx.getStatus() != UamsLoginContext.COMPLETE) 
		{
			System.out.println(uamsLoginCtx.getStatus());
			System.out.println("ErrorMessages.INVALID_INVALID_RESOURCE");
		}
		
		String ticket = uamsLoginCtx.getTicket();
		System.out.println("Ticket " + ticket);
		
		return ticket;
		
	}*/
	
	public static RPL1RechargeServices getRechargeServices()
	{
		
		
			RPL1RechargeServices mRPL1RechargeServices = null;
		try 
		{
			Context ctx = getInitialContext();
			Object obj = ctx.lookup(REC_JNDI_NAME);
			RPL1RechargeServicesHome home = (RPL1RechargeServicesHome)PortableRemoteObject.narrow(obj, amdocs.rpm.sessions.interfaces.home.RPL1RechargeServicesHome.class);
			mRPL1RechargeServices = home.create();
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		} 
		catch (CreateException e) 
		{
			e.printStackTrace();
		}
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
		return mRPL1RechargeServices;
	}
	
	
	public static RPL1SubscriberServices getSubscriberServices()
	{
		
			RPL1SubscriberServices subscriberServices = null;
		try 
		{
			Context ctx = getInitialContext();
			Object obj = ctx.lookup(SUB_JNDI_NAME);
			RPL1SubscriberServicesHome home = (RPL1SubscriberServicesHome)PortableRemoteObject.narrow(obj, amdocs.rpm.sessions.interfaces.home.RPL1SubscriberServicesHome.class);
			subscriberServices = home.create();
		} 
		catch (RemoteException e) 
		{
			System.out.println(e);
		} 
		catch (CreateException e) 
		{
			System.out.println(e);
		}
		catch (NamingException e) 
		{
			System.out.println(e);
		}
		return subscriberServices;
	}
	
	public static RPL1BucketServices getBucketServices()
	{
		
			RPL1BucketServices bucketServices = null;
		try 
		{
			Context ctx = getInitialContext();
			Object obj = ctx.lookup(BUCK_JNDI_NAME);
			RPL1BucketServicesHome home = (RPL1BucketServicesHome)PortableRemoteObject.narrow(obj, amdocs.rpm.sessions.interfaces.home.RPL1BucketServicesHome.class);
			bucketServices = home.create();
		} 
		catch (RemoteException e) 
		{
			System.out.println(e);
		} 
		catch (CreateException e) 
		{
			System.out.println(e);
		}
		catch (NamingException e) 
		{
			System.out.println(e);
		}
		return bucketServices;
	}
	
	public static RefDataManager getRefDataManager()
	{
		
			
			
			RefDataManager refDataMngr = null;
			

		try 
		{
			Context ctx = getInitialContext();
			Object obj = ctx.lookup(BUCK_JNDI_NAME);
			RefDataManagerHome home = (RefDataManagerHome)PortableRemoteObject.narrow(obj, amdocs.vm.sessions.interfaces.home.RefDataManagerHome.class);
			
			refDataMngr = home.create();
		} 
		catch (RemoteException e) 
		{
			System.out.println(e);
		} 
		catch (CreateException e) 
		{
			System.out.println(e);
		}
		catch (NamingException e) 
		{
			System.out.println(e);
		}
		return refDataMngr;
	}
	
	public static BucketBalanceInfo queryBalance(RPL1BucketServices rpl1BucketServices)
	{
		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		//pcn.setPcnPaymentCategory("PRE");
		
		BucketBalanceInfo balanceInfo = null;
		try
		{
			balanceInfo  = rpl1BucketServices.getOLCBalanceInfo(pcn);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		return balanceInfo;
	}
	
	public static BucketBalanceInfo[] queryBalanceBySubscriberId(RPL1SubscriberServices rpl1SubscriberServices)
	{
		
		SubscriberIdentInfo subscriber = new SubscriberIdentInfo();
		subscriber.setSubscriberNo(200131325);
		
		SubscriberPrimaryResourceInfo primaryResource = new SubscriberPrimaryResourceInfo();
		primaryResource.setPrimaryResourceValue("0972100140");
		primaryResource.setPrimaryResourceType("C");
		
		BucketBalanceInfo[] balanceInfo = null;
		try
		{
			balanceInfo  = rpl1SubscriberServices.viewAllSubscriberBalanceDetails(primaryResource);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		return balanceInfo;
	}
	public static void voucherRecharge(RPL1RechargeServices rpl1RechargeServices)
	{
		CRMChannelInfo channel = new CRMChannelInfo();
		channel.setReferenceId("CRM");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		
		
				
		VoucherRechargeMethodInfo method = new VoucherRechargeMethodInfo();
		method.setPaySourceId("1");
		method.setCoverDebitInd(DOMAINyesnoind.VV_NO_INDICATOR);
		method.setPaySourceType(DOMAINrpl1paysrtp.VV_RPM);
		method.setPinNumber("223044760739");
		//method.setSelectCriteria("123");
	//	method.setSerialNumber("11");
	//	method.setUniqueValue("12");
		
		BucketIdentInfo bucket = new BucketIdentInfo();
		bucket.setBucketId(21);
		
		SubscriberIdentInfo subscriber = new SubscriberIdentInfo();
		subscriber.setSubscriberNo(86);
		
		
		
		try
		{
			rpl1RechargeServices.rechargeService(channel, method, bucket, null);
			System.out.println("Successful");
		}
		catch(Exception a)
		{
			System.out.println("Exception");
			a.printStackTrace();
		}
		
	}
	public static RechargeResult cashRecharge(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		RPL3WEBChannelInfo	channel = new RPL3WEBChannelInfo();
		channel.setL3TransactionID("88409");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		CashRechargeMethodInfo method = new CashRechargeMethodInfo();
		method.setAmount(5.10);
		method.setCurrency("NTD");
		

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		pcn.setPcnPaymentCategory("PRE");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.rechargeService(channel, method, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	public static RechargeResult balanceTransfer(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		RPL3WEBChannelInfo	channel = new RPL3WEBChannelInfo();
		channel.setL3TransactionID("88410");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		RPL3AdjustMethodInfo method = new RPL3AdjustMethodInfo();
		method.setAmount(500);
		method.setCurrency("NTD");
		method.setReasonCode("hsj");
	//	method.setReasonDesc("");
		

		SubscriberIdentInfo sourceSubscriber = new SubscriberIdentInfo();
		sourceSubscriber.setSubscriberNo(1919155586);
		
		SubscriberIdentInfo targetSubscriber = new SubscriberIdentInfo();
		targetSubscriber.setSubscriberNo(1919155588);
		
		/*PcnInfo pcn = new PcnInfo();
		pcn.setPcn(123668);
		pcn.setPcnPaymentCategory("PRE");*/
		
		RechargeResult r = null;
		
		try
		{
			rpl1RechargeServices.l3BalanceTransfer(channel, method,sourceSubscriber, null, targetSubscriber, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	@SuppressWarnings("deprecation")
	public static RechargeResult positiveAdjustment(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		CRMChannelInfo	channel = new CRMChannelInfo();
		//channel.setL3TransactionID("1260");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		
		
		IncreaseRechargeMethodInfo method = new IncreaseRechargeMethodInfo();
		method.setAmount(50);
		method.setIncreaseType("GW");
		method.setReasonCode("abc");
		method.setCurrency("NTD");
		

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		pcn.setPcnPaymentCategory("PRE");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.rechargeService(channel, method, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	
	public static RechargeResult negativeAdjustment(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		RPL3WEBChannelInfo	channel = new RPL3WEBChannelInfo();
		/*channel.setL3TransactionID("1260");*/
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		ReduceRechargeMethodInfo method = new ReduceRechargeMethodInfo();
		method.setReduceType("IM");
		method.setAmount(5);
		method.setCurrency("NTD");
		method.setReasonCode("hhhds");
		//method.setL3ReasonDesc("");
		
		

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		pcn.setPcnPaymentCategory("PRE");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.rechargeService(channel, method, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	
	
	
	public static RechargeResult creditCardRecharge(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		CRMChannelInfo	channel = new CRMChannelInfo();
		//channel.setL3TransactionID("1260");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		CreditCardRechargeMethodInfo method = new CreditCardRechargeMethodInfo();
		
		method.setAmount(50);
		method.setCurrency("NTD");
		try {
			method.setCreditCardExpDate(formatter.parse("25/05/2016"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		method.setCreditCardNumber("A");
		//method.setCreditCardExpDate(d);
		method.setCreditCardType("A");
		method.setCoverDebitInd(DOMAINyesnoind.VV_YES_INDICATOR);

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		//pcn.setPcnPaymentCategory("");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.rechargeService(channel, method, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	
	public static RechargeResult BankRecharge(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		CMChannelInfo	channel = new CMChannelInfo();
		//channel.setL3TransactionID("1260");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		BankRechargeMethodInfo method = new BankRechargeMethodInfo();
		
		method.setAmount(50);
		method.setCurrency("NTD");
		method.setBankAccountNumber("4587345978364");
		method.setBankBranchNumber("8840");
		method.setBankCode("100");
		
		//method.setCreditCardExpDate(d);
		

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		pcn.setPcnPaymentCategory("PRE");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.rechargeService(channel, method, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	
	
	public static RechargeResult updateExpirationDateByDate(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date d = Calendar.getInstance().getTime();
		
		CRMChannelInfo	channel = new CRMChannelInfo();
		/*channel.setL3TransactionID("1260");*/
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		
		
		
		SetBalExpDateByDateRechargeMethodInfo expDateMethod = new SetBalExpDateByDateRechargeMethodInfo();
		try {
			expDateMethod.setExpirationDate(formatter.parse("10/01/2015"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*CashRechargeMethodInfo method = new CashRechargeMethodInfo();
		method.setAmount(50);
		method.setCurrency("NTD");*/
		

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(1329123718);
		//pcn.setPcnPaymentCategory("PRE");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.updateExpDate(channel, expDateMethod, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	
	public static RechargeResult updateExpirationDateByDays(RPL1RechargeServices rpl1RechargeServices)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		CMChannelInfo	channel = new CMChannelInfo();
		channel.setL3TransactionID("1260");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
		channel.setReferenceId("OVR");
		
		SetBalExpDateByDaysRechargeMethodInfo expDateMethod = new SetBalExpDateByDaysRechargeMethodInfo();
		expDateMethod.setNumOfDays(-100);
		
		
		CashRechargeMethodInfo method = new CashRechargeMethodInfo();
		method.setAmount(50);
		method.setCurrency("NTD");
		

		PcnInfo pcn = new PcnInfo();
		pcn.setPcn(123640);
		pcn.setPcnPaymentCategory("PRE");
		
		RechargeResult r = null;
		
		try
		{
			r = rpl1RechargeServices.updateExpDate(channel, expDateMethod, pcn, null);
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(ACMException acme)
		{
			acme.printStackTrace();
		}
		
		
		return r;
	}
	
	
	public static void viewRecharge(RPL1RechargeServices rpl1RechargeServices)
	{
		CRMChannelInfo channel = new CRMChannelInfo();
		channel.setReferenceId("CRM");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
				
		VoucherRechargeMethodInfo method = new VoucherRechargeMethodInfo();
		method.setPaySourceId("1");
		method.setCoverDebitInd(DOMAINyesnoind.VV_NO_INDICATOR);
		method.setPaySourceType(DOMAINrpl1paysrtp.VV_RPM);
		//method.setPinNumber("123456789");
		//method.setSelectCriteria("123");
		method.setSerialNumber("11");
	//	method.setUniqueValue("12");
		
		BucketIdentInfo bucket = new BucketIdentInfo();
		bucket.setBucketId(4682);
		
		SubscriberIdentInfo subscriber = new SubscriberIdentInfo();
		subscriber.setSubscriberNo(86);
		
		DatesRangeInfo dt = new DatesRangeInfo();
		Date d1 = new Date();
		d1.setTime(1356998400000L);
		
		Date d2 = new Date();
		d2.setTime(1385856000000L);
		
		dt.setFromDate(d1);
		dt.setToDate(d2);
		
		try
		{
			RechargeHeader[] header =  rpl1RechargeServices.viewRecharge(bucket, dt);
			
			
			for(int i=0; i < header.length ; i++)
			{
				System.out.println("header["+i+"].getRechargeAmountsInfo().getInputAmt() = " + header[i].getRechargeAmountsInfo().getInputAmt());
				System.out.println("header["+i+"].getRechargeAmountsInfo().getInputCurrency() = " + header[i].getRechargeAmountsInfo().getInputCurrency());
				System.out.println("header["+i+"].getRechargeAmountsInfo().getPpsAmt() = " + header[i].getRechargeAmountsInfo().getPpsAmt());
				System.out.println("header["+i+"].getRechargeAmountsInfo().getPpsBalance() = " + header[i].getRechargeAmountsInfo().getPpsBalance());
				System.out.println("header["+i+"].getRechargeAmountsInfo().getPpsNewBalance() =  " + header[i].getRechargeAmountsInfo().getPpsNewBalance());
				System.out.println("header["+i+"].getRechargeAmountsInfo().getRcgChlAmt() = " + header[i].getRechargeAmountsInfo().getRcgChlAmt());
				System.out.println("header["+i+"].getRechargeBalanceExpirationInfo().getRcgNumOfDays() = " + header[i].getRechargeBalanceExpirationInfo().getRcgNumOfDays());
				System.out.println("header["+i+"].getRechargeBalanceExpirationInfo().getBalExpDateCalc() = " + header[i].getRechargeBalanceExpirationInfo().getBalExpDateCalc());				
				System.out.println("header["+i+"].getRechargeBucketInfo().getBan() = " + header[i].getRechargeBucketInfo().getBan());			
				System.out.println("header["+i+"].getRechargeBucketInfo().getBen() = " + header[i].getRechargeBucketInfo().getBen());
				System.out.println("header["+i+"].getRechargeBucketInfo().getBucketId() = " + header[i].getRechargeBucketInfo().getBucketId());
				System.out.println("header["+i+"].getRechargeBucketInfo().getCrmExternalId() = " + header[i].getRechargeBucketInfo().getCrmExternalId());
				System.out.println("header["+i+"].getRechargeBucketInfo().getCurrency() = " + header[i].getRechargeBucketInfo().getCurrency());
				System.out.println("header["+i+"].getRechargeBucketInfo().getCustomerId() =" + header[i].getRechargeBucketInfo().getCustomerId());
				System.out.println("header["+i+"].getRechargeBucketInfo().getPcn() = " + header[i].getRechargeBucketInfo().getPcn());
				System.out.println("header["+i+"].getRechargeBucketInfo().getPcnName() = " + header[i].getRechargeBucketInfo().getPcnName());
				System.out.println("header["+i+"].getRechargeBucketInfo().getBalExpDate() = " + header[i].getRechargeBucketInfo().getBalExpDate());
				System.out.println("header["+i+"].getRechargeDatesInfo().getLogicalDate() = " + header[i].getRechargeDatesInfo().getLogicalDate());
				System.out.println("header["+i+"].getRechargeDatesInfo().getProcessDate() = " + header[i].getRechargeDatesInfo().getProcessDate());
				System.out.println("header["+i+"].getRechargeDatesInfo().getRcgRefDate() = " + header[i].getRechargeDatesInfo().getRcgRefDate());
				System.out.println("header["+i+"].getRechargeDatesInfo().getRcgReqDate() = " + header[i].getRechargeDatesInfo().getRcgReqDate());
				System.out.println("header["+i+"].getRechargeIdentInfo().getBucketKey() = " + header[i].getRechargeIdentInfo().getBucketKey());
				System.out.println("header["+i+"].getRechargeIdentInfo().getPeriodKey() = " + header[i].getRechargeIdentInfo().getPeriodKey());
				System.out.println("header["+i+"].getRechargeIdentInfo().getRcgId() = " + header[i].getRechargeIdentInfo().getRcgId());
				System.out.println("header["+i+"].getRechargeLogInfo().getCdMainId() = " + header[i].getRechargeLogInfo().getCdMainId());
				System.out.println("header["+i+"].getRechargeLogInfo().getCdUniqueId() = " + header[i].getRechargeLogInfo().getCdUniqueId());
				System.out.println("header["+i+"].getRechargeLogInfo().getCdVals() = " + header[i].getRechargeLogInfo().getCdVals());
				System.out.println("header["+i+"].getRechargeLogInfo().getClientTimeStamp() = " + header[i].getRechargeLogInfo().getClientTimeStamp());
				System.out.println("header["+i+"].getRechargeLogInfo().getL3AggrRcg() = " + header[i].getRechargeLogInfo().getL3AggrRcg());
				System.out.println("header["+i+"].getRechargeLogInfo().getRadTimeStamp() = " + header[i].getRechargeLogInfo().getRadTimeStamp());
				System.out.println("header["+i+"].getRechargeLogInfo().getRmdMainId() = " + header[i].getRechargeLogInfo().getRmdMainId());
				System.out.println("header["+i+"].getRechargeLogInfo().getRmdVals() = " + header[i].getRechargeLogInfo().getRmdVals());
				System.out.println("header["+i+"].getRechargeLogInfo().getRplTimeStamp() = " + header[i].getRechargeLogInfo().getRplTimeStamp());
				System.out.println("header["+i+"].getRechargeLogInfo().getSubIdVals() = " + header[i].getRechargeLogInfo().getSubIdVals());
				System.out.println("header["+i+"].getRechargeLogInfo().getL3BnsEffDate() = " + header[i].getRechargeLogInfo().getL3BnsEffDate());
				System.out.println("header["+i+"].getRechargeStatusInfo().getCancelRcgType() = " + header[i].getRechargeStatusInfo().getCancelRcgType());
				System.out.println("header["+i+"].getRechargeStatusInfo().getCdCancelRefId() = " + header[i].getRechargeStatusInfo().getCdCancelRefId());
				System.out.println("header["+i+"].getRechargeStatusInfo().getRcgStatus() = " + header[i].getRechargeStatusInfo().getRcgStatus());
				System.out.println("header["+i+"].getRechargeStatusInfo().getRelRcgId() = " + header[i].getRechargeStatusInfo().getRelRcgId());
				System.out.println("header["+i+"].getRechargeStatusInfo().getRelRcgType() = " + header[i].getRechargeStatusInfo().getRelRcgType());
				System.out.println("header["+i+"].getRechargeStatusInfo().getLastStsUpdate() = " + header[i].getRechargeStatusInfo().getLastStsUpdate());
				System.out.println("header["+i+"].getRechargeSubscriberInfo().getPrimaryResourceType() = " + header[i].getRechargeSubscriberInfo().getPrimaryResourceType());
				System.out.println("header["+i+"].getRechargeSubscriberInfo().getPrimaryResourceValue() = " + header[i].getRechargeSubscriberInfo().getPrimaryResourceValue());
				System.out.println("header["+i+"].getRechargeSubscriberInfo().getSubscriberNo() = " + header[i].getRechargeSubscriberInfo().getSubscriberNo());
				System.out.println("header["+i+"].getRechargeTypeInfo().getChannelCode() = " + header[i].getRechargeTypeInfo().getChannelCode());
				System.out.println("header["+i+"].getRechargeTypeInfo().getMethodCode() = " + header[i].getRechargeTypeInfo().getMethodCode());
				System.out.println("header["+i+"].getRechargeTypeInfo().getMethodGroup() = " + header[i].getRechargeTypeInfo().getMethodGroup());
				System.out.println("header["+i+"].getRechargeTypeInfo().getMethodType() = " + header[i].getRechargeTypeInfo().getMethodType());
				System.out.println("header["+i+"].getRechargeTypeInfo().getRcgType() = " + header[i].getRechargeTypeInfo().getRcgType());
				System.out.println("header["+i+"].getRechargeTypeInfo().getSplitRcgSeq() = " + header[i].getRechargeTypeInfo().getSplitRcgSeq());
				
			}
			System.out.println("Successful");
		}
		catch(Exception a)
		{
			System.out.println("Exception");
			a.printStackTrace();
		}
		
	}
	

	public static void viewRechargeError(RPL1RechargeServices rpl1RechargeServices)
	{
		CRMChannelInfo channel = new CRMChannelInfo();
		channel.setReferenceId("CRM");
		Date d = Calendar.getInstance().getTime();
		channel.setRequestDateTime(d);
				
		VoucherRechargeMethodInfo method = new VoucherRechargeMethodInfo();
		method.setPaySourceId("1");
		method.setCoverDebitInd(DOMAINyesnoind.VV_NO_INDICATOR);
		method.setPaySourceType(DOMAINrpl1paysrtp.VV_RPM);
		//method.setPinNumber("123456789");
		//method.setSelectCriteria("123");
		method.setSerialNumber("11");
	//	method.setUniqueValue("12");
		
		BucketIdentInfo bucket = new BucketIdentInfo();
		bucket.setBucketId(4682);
		
		SubscriberIdentInfo subscriber = new SubscriberIdentInfo();
		subscriber.setSubscriberNo(86);
		
		DatesRangeInfo dt = new DatesRangeInfo();
		Date d1 = new Date();
		d1.setTime(1356998400000L);
		
		Date d2 = new Date();
		d2.setTime(1385856000000L);
		
		dt.setFromDate(d1);
		dt.setToDate(d2);
		
		try
		{
			RcgErrorHeader[] header =  rpl1RechargeServices.viewRechargeError(bucket, dt);
			
			
			for(int i=0; i < header.length ; i++)
			{
				System.out.println("header["+i+"].getRcgErrorIdentInfo().getPeriodKey() = " + header[i].getRcgErrorIdentInfo().getPeriodKey());
				System.out.println("header["+i+"].getRcgErrorIdentInfo().getRcgErrId() = " + header[i].getRcgErrorIdentInfo().getRcgErrId());			
				System.out.println("header["+i+"].getRcgErrorInfo().getBan() = " + header[i].getRcgErrorInfo().getBan());			
				System.out.println("header["+i+"].getRcgErrorInfo().getBen() = " + header[i].getRcgErrorInfo().getBen());
				System.out.println("header["+i+"].getRcgErrorInfo().getBucketId() = " + header[i].getRcgErrorInfo().getBucketId());
				System.out.println("header["+i+"].getRcgErrorInfo().getCdMainId() = " + header[i].getRcgErrorInfo().getCdMainId());
				System.out.println("header["+i+"].getRcgErrorInfo().getCdVals() = " + header[i].getRcgErrorInfo().getCdVals());
				System.out.println("header["+i+"].getRcgErrorInfo().getChannelCode() =" + header[i].getRcgErrorInfo().getChannelCode());
				System.out.println("header["+i+"].getRcgErrorInfo().getCustomerId() = " + header[i].getRcgErrorInfo().getCustomerId());
				System.out.println("header["+i+"].getRcgErrorInfo().getErrCode() = " + header[i].getRcgErrorInfo().getErrCode());
				System.out.println("header["+i+"].getRcgErrorInfo().getErrDesc() = " + header[i].getRcgErrorInfo().getErrDesc());
				System.out.println("header["+i+"].getRcgErrorInfo().getErrSts() = " + header[i].getRcgErrorInfo().getErrSts());
				System.out.println("header["+i+"].getRcgErrorInfo().getExternalId() = " + header[i].getRcgErrorInfo().getExternalId());
				System.out.println("header["+i+"].getRcgErrorInfo().getImsi() = " + header[i].getRcgErrorInfo().getImsi());				
				System.out.println("header["+i+"].getRcgErrorInfo().getInputAmount() = " + header[i].getRcgErrorInfo().getInputAmount());
				System.out.println("header["+i+"].getRechargeBucketInfo().getBalExpDate() = " + header[i].getRcgErrorInfo().getInputCurrency());
				System.out.println("header["+i+"].getRcgErrorInfo().getInputCurrency() = " + header[i].getRcgErrorInfo().getMethodCode());
				System.out.println("header["+i+"].getRcgErrorInfo().getMethodGroup() = " + header[i].getRcgErrorInfo().getMethodGroup());
				System.out.println("header["+i+"].getRcgErrorInfo().getMethodType() = " + header[i].getRcgErrorInfo().getMethodType());				
				System.out.println("header["+i+"].getRcgErrorInfo().getOutputCurrency() = " + header[i].getRcgErrorInfo().getOutputCurrency());
				System.out.println("header["+i+"].getRcgErrorInfo().getPrimResourceTp() = " + header[i].getRcgErrorInfo().getPrimResourceTp());
				System.out.println("header["+i+"].getRcgErrorInfo().getPrimResourceVal() = " + header[i].getRcgErrorInfo().getPrimResourceVal());
				System.out.println("header["+i+"].getRcgErrorInfo().getPymChannelNo() = " + header[i].getRcgErrorInfo().getPymChannelNo());
				System.out.println("header["+i+"].getRcgErrorInfo().getRcgChlAmt() = " + header[i].getRcgErrorInfo().getRcgChlAmt());				
				System.out.println("header["+i+"].getRcgErrorInfo().getRcgId() = " + header[i].getRcgErrorInfo().getRcgId());
				System.out.println("header["+i+"].getRcgErrorInfo().getRcgType() = " + header[i].getRcgErrorInfo().getRcgType());
				System.out.println("header["+i+"].getRcgErrorInfo().getRmdMainId() = " + header[i].getRcgErrorInfo().getRmdMainId());
				System.out.println("header["+i+"].getRcgErrorInfo().getRmdVals() = " + header[i].getRcgErrorInfo().getRmdVals());												
				System.out.println("header["+i+"].getRcgErrorInfo().getSubIdVals() = " + header[i].getRcgErrorInfo().getSubIdVals());
				System.out.println("header["+i+"].getRcgErrorInfo().getSubscriberNo() = " + header[i].getRcgErrorInfo().getSubscriberNo());
				System.out.println("header["+i+"].getRcgErrorInfo().getErrRcgDate() = " + header[i].getRcgErrorInfo().getErrRcgDate());
				System.out.println("header["+i+"].getRcgErrorInfo().getRcgReqDate() = " + header[i].getRcgErrorInfo().getRcgReqDate());
				
			}
			System.out.println("Successful");
		}
		catch(Exception a)
		{
			System.out.println("Exception");
			a.printStackTrace();
		}
		
	}
	
	public static void getVoucher(RPL1RechargeServices rpl1RechargeServices)
	{
		
				
		VoucherRechargeMethodInfo method = new VoucherRechargeMethodInfo();
		method.setPaySourceId("1");
		method.setCoverDebitInd(DOMAINyesnoind.VV_NO_INDICATOR);
		method.setPaySourceType(DOMAINrpl1paysrtp.VV_RPM);
		method.setPinNumber("123456789");
		method.setSelectCriteria("1234567");
		method.setSerialNumber("11");
		method.setUniqueValue("12");
				
		
		try
		{
			VoucherInfo v = rpl1RechargeServices.getVoucherInfo(method);
			System.out.println("Successful" + v.getVoucherAmount()+ "\n" + v.getVoucherSerialNumber());
			
		}
		catch(Exception a)
		{
			System.out.println("Exception");
			a.printStackTrace();
		}
		
	}
	
	/**
	 * @return
	 */
	private static InitialContext getInitialContext()
	{
		Hashtable env = new Hashtable(2);
		

		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://" + WL_SERVER_URL);
		if(initCtx != null)
		{
			return initCtx;
		}

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
			ht.put(Context.PROVIDER_URL, "t3://" + WL_SERVER_URL);
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
