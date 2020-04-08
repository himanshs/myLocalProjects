import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SampleCode {
	
	public static void main(String[] args) {
		//System.out.println(SampleCode.isValidOfferLevelParamName("9156122 Reason Code"));
		//System.out.println(SampleCode.isValidOfferLevelParamName("9156122 Recharge Amount"));
		String toImsiValue = "534537587358256385";
		String toIccidValue ="35835345835985985";
		String fromImsiValue=Long.toString(Long.parseLong(toImsiValue)+ new Long(1));
		String fromIccidValue=Long.toString(Long.parseLong(toIccidValue)+ new Long(1));
		
		System.out.println(fromImsiValue);
		System.out.println(fromIccidValue);
	}
	
	public static boolean isValidOfferLevelParamName(String str){
	     Pattern pattern=Pattern.compile("^[0-9]{1,}\\s{1}"); 
		 Matcher matcher = pattern.matcher(str); 
		 if(matcher.find()){  
			 return true;
		 } 
		 return false;
    }
}
