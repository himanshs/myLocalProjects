package sample;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import org.enhydra.xml.xmlc.html.HTMLObject;
import amdocs.rm3gClient.html.queries.*;
import amdocs.rm3gClient.html.common.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import amdocs.rm3gClient.handlers.*;
import  amdocs.rm3gClient.datatypes.*;
import amdocs.rm3gClient.utils.*;
import amdocs.rm3gClient.appservices.*;
import amdocs.uams.login.servlet.*;
import amdocs.uams.*;
import amdocs.rm3g.interfaces.datatypes.*;
import amdocs.rm3g.exceptions.*;
import amdocs.rm3g.domains.*;
import amdocs.gen3g.domains.DOMAINyesnoind;
import  amdocs.rm3g.utils.UrmConstants;
import amdocs.rm3g.utils.RM9Constants;
import org.enhydra.xml.xmlc.html.HTMLObject;
import amdocs.rm3gClient.html.queries.*;
import amdocs.rm3gClient.html.common.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import amdocs.rm3gClient.handlers.*;
import  amdocs.rm3gClient.datatypes.*;
import amdocs.rm3gClient.utils.*;
import amdocs.rm3gClient.appservices.*;
import amdocs.uams.login.servlet.*;
import amdocs.uams.*;
import amdocs.rm3g.interfaces.datatypes.*;
import amdocs.rm3g.exceptions.*;
import amdocs.rm3g.domains.*;
import amdocs.gen3g.domains.DOMAINyesnoind;
import  amdocs.rm3g.utils.UrmConstants;
import amdocs.rm3g.utils.RM9Constants;
import org.enhydra.xml.xmlc.html.HTMLObject;
import amdocs.rm3gClient.html.queries.*;
import amdocs.rm3gClient.html.common.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import amdocs.rm3gClient.handlers.*;
import  amdocs.rm3gClient.datatypes.*;
import amdocs.rm3gClient.utils.*;
import amdocs.rm3gClient.appservices.*;
import amdocs.uams.login.servlet.*;
import amdocs.uams.*;
import amdocs.rm3g.interfaces.datatypes.*;
import amdocs.rm3g.exceptions.*;
import amdocs.rm3g.domains.*;
import amdocs.gen3g.domains.DOMAINyesnoind;
import  amdocs.rm3g.utils.UrmConstants;
import amdocs.rm3g.utils.RM9Constants;

public final class _queriesMain extends com.ibm.ws.jsp.runtime.HttpJspBase
     implements com.ibm.ws.jsp.runtime.JspClassInformation {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static String[] _jspx_dependants;
  static {
    _jspx_dependants = new String[41];
    _jspx_dependants[0] = "/queries/QueriesPoolResourcesResult.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[1] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[2] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[3] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[4] = "/queries/QueriesPoolSumResult.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[5] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[6] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[7] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[8] = "/queries/QueriesCategorySumResult.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[9] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[10] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[11] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[12] = "/queries/QueriesRscDetailsResult.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[13] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[14] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[15] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[16] = "/queries/QueriesAttPopUp.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[17] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[18] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[19] = "/queries/QueriesPackageResult.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[20] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[21] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[22] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[23] = "/common/BorderCommon.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[24] = "/queries/QueriesMenuTree.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[25] = "/queries/QueriesParameters.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[26] = "/queries/QueriesRscListParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[27] = "/queries/QueriesPackageListParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[28] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[29] = "/queries/QueriesRscDetailsParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[30] = "/queries/QueriesPackageParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[31] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[32] = "/queries/QueriesPoolSumParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[33] = "/queries/QueriesCategorySumParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[34] = "/queries/QueriesRscHistoryParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[35] = "/queries/QueriesPackageHistoryParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[36] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[37] = "/queries/QueriesPoolResourcesParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[38] = "/queries/QueriesRequestListParams.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[39] = "/queries/QueryBEChangeParam.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
    _jspx_dependants[40] = "/common/Message.inc^1368090466000^Thu May 09 14:37:46 GMT+05:30 2013";
  }

  public String[] getDependants() {
    return _jspx_dependants;
  }

  private com.ibm.wsspi.webcontainer.annotation.AnnotationHelper _jspx_iaHelper;

  private static String _jspx_classVersion;
  private static boolean _jspx_isDebugClassFile;
  static {
    _jspx_classVersion = new String("7.0.0.17");
    _jspx_isDebugClassFile = false;
  }

  public String getVersionInformation() {
    return _jspx_classVersion;
  }
  public boolean isDebugClassFile() {
    return _jspx_isDebugClassFile;
  }

  private boolean _jspx_isJspInited = false;

  private final static char[] _jsp_string1 = "<!--\n//Changes history\n//---------------------------------------------------\n//Author:\t\t\t\tDeepak Ajmera\n//Supervisor:\t\t\tSomanath Padhi\n//Version:\t\t\t\t7.0\n//Date:\t\t\t\t\t24-April-2007\n//CR:\t\t\t\t\t327829 and 327828 \n//CR Description: \t\t(327829)Randomization of resource values\n//\t\t\t\t\t\t(327828)Query for sequential blocks\n//Change number:\t\t#1\n//---------------------------------------------------\n//Author:\t\t\t\tMadhavi K\n//Supervisor:\t\t\tPrashant Khismatrao\n//Version:\t\t\t\t7.5\n//Date:\t\t\t\t\t29-Nov-2007\n//CR:\t\t\t\t\t339784\n//CR Description: \t\tExport all queries to flat file\n//Change number:\t\t#2: Moved Resource List, Pacakge List \n//\t\t\t\t\t\tResource History and Package History to _int1 page\n//---------------------------------------------------\n//Author:\t\t\t\tMadhavi K\n//Supervisor:\t\t\tPrashant Khismatrao\n//Version:\t\t\t\t7.5\n//Date:\t\t\t\t\t13-Dec-2007\n//CR:\t\t\t\t\t52825\n//CR Description: \t\tJSP exception due to Websphere size limitation\n//\t\t\t\t\t\t(Moved Resource and Package Utilization Report to _int2 page)\n//Change number:\t\t#3\n//---------------------------------------------------\n//Author:\t\t\t\tNitesh Kashyap\n//Supervisor:\t\t\tMaheswara Rao EVS\n//Version:\t\t\t\t7.5\n//Date:\t\t\t\t\t13-August-2010\t\t\t\t\n//CR Description: \t\tAdded logic to load BE screen First and only once in one session\n//Change number:\t\t#4\n//---------------------------------------------------\n//Author:\t\t\t\tLovey Gupta\n//Supervisor:\t\t\tSuganthy Jebadurai\n//Version:\t\t\t\t7.5\n//Date:\t\t\t\t\t09-Feb-2011\n//CR Description: \t\tINP file generated should have STATE ID with BE ID\n//Change number:\t\t#5\n//--------------------------------------------------\n-->".toCharArray();
  private final static char[] _jsp_string2 = "\n\n".toCharArray();
  private final static char[] _jsp_string3 = "<!-- Start change #1 // Added amdocs.rm3g.utils.UrmConstants to import list -->".toCharArray();
  private final static char[] _jsp_string4 = "\n".toCharArray();
  private final static char[] _jsp_string5 = "<!-- Start change #4 //Added amdocs.rm3g.utils.RM9Constants to imporrt list -->".toCharArray();
  private final static char[] _jsp_string6 = "<!-- //End Change#4 -->".toCharArray();
  private final static char[] _jsp_string7 = "<!-- //End change #1 -->".toCharArray();
  private final static char[] _jsp_string8 = "\n\t\t\t".toCharArray();
  private final static char[] _jsp_string9 = "\n         \t".toCharArray();
  private final static char[] _jsp_string10 = "\n     ".toCharArray();
  private final static char[] _jsp_string11 = "<!-- Code to forward request: turn to the specified page -->".toCharArray();
  private final static char[] _jsp_string12 = "\n      ".toCharArray();
  private final static char[] _jsp_string13 = "<!-- Code to forward request -->".toCharArray();
  private final static char[] _jsp_string14 = "\n\t  ".toCharArray();
  private final static char[] _jsp_string15 = "\n\n\t  ".toCharArray();
  private final static char[] _jsp_string16 = "\n\t\t".toCharArray();
  private final static char[] _jsp_string17 = "\n\t\t\n\t  ".toCharArray();
  private final static char[] _jsp_string18 = "\n            ".toCharArray();
  private final static char[] _jsp_string19 = "\n         ".toCharArray();
  private final static char[] _jsp_string20 = "<!-- // client message -->".toCharArray();
  private final static char[] _jsp_string21 = "\n           ".toCharArray();
  private final static char[] _jsp_string22 = "\n\t".toCharArray();
  private final static char[] _jsp_string23 = "\n\n\n".toCharArray();
  private final static char[] _jsp_string24 = "\n        ".toCharArray();
  private final static char[] _jsp_string25 = "\n       ".toCharArray();
  private final static char[] _jsp_string26 = "<!----------------- // Message to the client --------->".toCharArray();
  private final static char[] _jsp_string27 = "\n\t\t\t\t".toCharArray();
  private final static char[] _jsp_string28 = "\t\n\t\t".toCharArray();
  private final static char[] _jsp_string29 = "\r\n".toCharArray();
  private final static char[] _jsp_string30 = "\r\n\r\n\r\n".toCharArray();

  static {
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();

    com.ibm.wsspi.webcontainer.annotation.AnnotationHelperManager _jspx_aHelper = com.ibm.wsspi.webcontainer.annotation.AnnotationHelperManager.getInstance (getServletConfig()    .getServletContext());
    _jspx_iaHelper = _jspx_aHelper.getAnnotationHelper();
    _jspx_isJspInited = true;
  }

  private static org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap = null;


  public void _jspService(HttpServletRequest request, HttpServletResponse  response)
      throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;

    JspWriter _jspx_out = null;


    if(!_jspx_isJspInited){
      this._jspInit();
    }


    try {

      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);

      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(_jsp_string1);
      out.write(_jsp_string2);
      out.write(_jsp_string3);
      out.write(_jsp_string4);
      out.write(_jsp_string5);
      out.write(_jsp_string4);
      out.write(_jsp_string4);
      out.write(_jsp_string6);
      out.write(_jsp_string4);
      out.write(_jsp_string7);
      out.write(_jsp_string2);
      
      
      		String logout = request.getParameter("logout");
          if(logout!=null && logout.equals("true")){
                session.invalidate();
        	     session = request.getSession(true);
          }
          /** Start Change#4 --Using screenTacker to trace screen for BE Functionlity and previous flow      **/
          
          Integer screenTracker = (Integer)session.getAttribute("screenTracker");
          if (screenTracker == null) {  				//First load of application
                screenTracker = new Integer(0);
                QueriesFlowHandler.envScreenNum = 0;
                
          } else if( screenTracker.equals(new Integer(0)) && QueriesFlowHandler.envScreenNum ==1 ) {  		//Handles BE screen submit
                screenTracker = new Integer(1);
                
          }else if(screenTracker.equals(new Integer(1))){  															//General case
            	screenTracker = new Integer(2);
              
          }
          session.setAttribute("screenTracker", screenTracker);
          
          /** End Change#4 **/    
      
      out.write(_jsp_string4);
      amdocs.rm3gClient.appservices.GenBean genBean = null;
      synchronized (session) {
        genBean = (amdocs.rm3gClient.appservices.GenBean) pageContext.getAttribute("genBean", PageContext.SESSION_SCOPE);
        if (genBean == null) {
          genBean =  new amdocs.rm3gClient.appservices.GenBean();
          pageContext.setAttribute("genBean", genBean, PageContext.SESSION_SCOPE);
        }
      }
      out.write(_jsp_string2);
      
      {
           int myPage;
           String title       = new String();
           String noDec = null;
        	
           int environmentValidity; //the output number returned after environment-check
        
        	
        	 
           FlowHandler flowHandler;
           DataHandler dataHandler;
           ActionHandler actionHandler;
        
        
          // ========= initiateEJBHomeObjects ===========
            //Initiate EJBHomeObjects only first time we get in search page.
            if(session.getAttribute("isSessionAlive") == null)
            {
                  genBean.initiateEJBHomeObjects(request);
                  genBean.storeAdminData (request);
            }
        
        	// ==========================================================================
        	// Search Page is displayed Only if Application Server is Up
        	// if the session-server is down or a fatal remote error has occured
        	// the proper msg will be displayed to the user in the login page 
        	// ==========================================================================
        	if( !genBean.isAppServerUp(request) )
        	{
          		System.out.println("@@@@@@@@@ queriesMain.jsp The URM App.Server is down or security violation");
          		if (Constants.SECURITY_ON)
          		{
            out.write(_jsp_string8);
            if (true) {
              pageContext.forward(RMFlowHandler.handleCrriticalErrors);
              return;
            }
            out.write(_jsp_string4);
            			
          		}
          		 else
          		 {
            out.write(_jsp_string9);
            if (true) {
              pageContext.forward(RMFlowHandler.appServerDown);
              return;
            }
            out.write(_jsp_string4);
              
          		 }
        	}
        	
        	/** Start Change#4  **/
        	
        	/** Parsing the becode string to get BE code 
           		which will be available in session   **/
        		String becode = request.getParameter("selectBE_ID"); 	
        		if(becode != null){
          		//Change #5 Starts
          		session.setAttribute("BE_STATE_ID",becode); 
          		//Change #5 Ends
          			if(becode.contains("_") ){
            				String[] splittedBECode = becode.split("_");
            				session.setAttribute("BE_ID",splittedBECode[1]);
            				QueriesFlowHandler.envScreenNum = 1; //assiging this variable to 1
            				session.setAttribute("viewEnvScreen",Boolean.FALSE); //Once submitted set EnvSceen attribute to false 
          			}else{
            				out.println("BE_ID is NULL here...\n");
          			}
        		}
        		/** End Change#4  **/
        		
           // ========= Create or get main objects ===========
        
           // Get flow handler object from the session or create a new one
           flowHandler = QueriesFlowHandler.getInstance(request);
        
           // Get data handler object from the session or create a new one
           dataHandler = QueriesDataHandler.getInstance(request, genBean);
        
           // Get action handler object from the session or create a new one
           actionHandler = QueriesActionHandler.getInstance(request, genBean);
           // ===============================================
        
           // For preparation of dataHandler to get data for current folder scope
           session.setAttribute("rmScope", Constants.QUERIES_SCOPE);
           
           
          session.setAttribute("viewEnvScreen",Boolean.FALSE);   
        
         if (!Constants.SECURITY_ON)
        	{
                // Checks if session and/or other validation checks it still valid
                environmentValidity = actionHandler.checkEnvironmentValidity(request);
                //only if there was some error
                if (environmentValidity != ActionHandler.ACTION_OK)
              {
                       System.out.println("@@Error in checkEnvironmentValidity");
                     // return to the specified page
                    // according to the string delievered by the validity resove method
            out.write(_jsp_string10);
            out.write(_jsp_string11);
            out.write(_jsp_string10);
            if (true) {
              pageContext.forward(flowHandler.environmentValidityResolve(environmentValidity));
              return;
            }
            out.write(_jsp_string4);
            
              }
         }
        
           // Clear session if it necessary
           actionHandler.doClearOnDemand(request);
        
           // Perform group action
           actionHandler.doAction(request);
        
           // Find what the next page
           
           /** Start Change#4  **/
           if(QueriesFlowHandler.envScreenNum == 1 &&( ((Integer)session.getAttribute("screenTracker")).equals(new Integer(0)) || ((Integer)session.getAttribute("screenTracker")).equals(new Integer(1))) ){
             		myPage = 0;
           }else {
             	myPage = flowHandler.flowResolve(request);
           }
           /** End Change#4  **/
           //myPage = flowHandler.flowResolve(request);
           // Find the selected query
           String byQuery	 = request.getParameter("byQuery");
        
        //Start change #1
        /*If refreshQuery is true, meaning we clicked the 
        	menu item to access this page, then clear the stored query parameters.
        	This is done to ensure that switch between resource and package does not
        	keep stale data*/
        	String refreshQuery = request.getParameter("refreshQuery"); //Handle when the query option is selected from left menu
        	if (refreshQuery == null)
        	{
          		refreshQuery = "";
        	}
        	if ("true".equals(refreshQuery))
        	{
          		session.removeAttribute("QueriesInfo");
        	}
        //End change #1
        
        	//Start Change#4
        	int queryNum = RM9Constants.BE_ID_PAGE_CONSTANT;  //Setting BE-change page as default page for query page		
           // find the html param	
           /*  Start Niteshka#  -- to intialize queryNum accordingly */
           /** 10-08-2010  **/
           if(QueriesFlowHandler.envScreenNum == 0){
             	queryNum = RM9Constants.BE_ID_PAGE_CONSTANT;
           }else if( QueriesFlowHandler.envScreenNum == 1){
             		queryNum = Constants.RESOURCE_LIST;
           }  
          
          //end Change#4
        		
           QueriesInfo queriesInf  = (QueriesInfo)dataHandler.getData(request, "QueriesInfo");
        
           if (queriesInf != null)
        	   queryNum = queriesInf.getQuery();
        	  
           // If menu action
           if ( byQuery != null)
        	   queryNum = AppUtils.stringToInt(byQuery);
        
           if (myPage == FlowHandler.FORWARD_REQUEST)
           {
                System.out.println("Forward request in Search Folder !!!");
          out.write(_jsp_string12);
          out.write(_jsp_string13);
          out.write(_jsp_string12);
          if (true) {
            pageContext.forward(flowHandler.forwardResolve(request));
            return;
          }
          out.write(_jsp_string4);
          
           }
        
           // Perform page action
           if (actionHandler.doPageAction(request) == ActionHandler.ERROR_NO_CONT) {
                System.out.println("Error in doPageAction Method in Query Folder !!!");
          out.write(_jsp_string12);
          out.write(_jsp_string13);
          out.write(_jsp_string12);
          if (true) {
            pageContext.forward(flowHandler.forwardResolve(request));
            return;
          }
          out.write(_jsp_string4);
          
           }
           
           
        	//	Redirection to the second (internal) main JSP page (cstFolderMain_int.jsp)
        	//	in case of selected internal pages
        //Start change #2
           if (	myPage == QueriesFlowHandler.QUERY_REQUEST_LIST_PAGE ||
          	 	myPage == QueriesFlowHandler.QUERY_REQUEST_DETAILS_PAGE ||
        		myPage == QueriesFlowHandler.QUERY_RESPONSE_DETAILS_PAGE ||
        		queryNum   == Constants.REQUEST_LIST  ||	
        		queryNum   == Constants.REQUEST_DETAILS )
        	{
          out.write(_jsp_string14);
          out.write(_jsp_string13);
          out.write(_jsp_string14);
          
          		  //Code to adhere to URLEncode(String,String) for Websphere Apache Compiler
          		  String _pageStr = ""+myPage;
          	  
          out.write(_jsp_string15);
          out.write(_jsp_string16);
          out.write(_jsp_string14);
          if (true) {
            pageContext.forward("../queries/queriesMain_int.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("_intPage", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(_pageStr), request.getCharacterEncoding()));
            return;
          }
          out.write(_jsp_string4);
          
        	}
        		
        		//StartChange#4 Niteshka Added a condition queryNum == M9Constants.BE_ID_PAGE_CONSTANT
           if (myPage == QueriesFlowHandler.QUERY_RESOURCE_LIST_PAGE || 
           		myPage == QueriesFlowHandler.QUERY_PACKAGE_LIST_PAGE || 
           		myPage == QueriesFlowHandler.QUERY_RESOURCE_HISTORY_PAGE || 
           		myPage == QueriesFlowHandler.QUERY_PACKAGE_HISTORY_PAGE ||
           		queryNum == Constants.RESOURCE_LIST || 
           		queryNum == Constants.PACKAGE_LIST || 
           		queryNum == Constants.RESOURCE_HISTORY || 
           		queryNum == Constants.PACKAGE_HISTORY ||
           		queryNum == RM9Constants.BE_ID_PAGE_CONSTANT )
   