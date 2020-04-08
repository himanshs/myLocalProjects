
public class InnoveCheckDigit {
	public static void main(String args[]) {
		int acct_no = 71085410;
		int chk_digit = 0;
		int wt = 1;
		int new_acct_no = acct_no;

		while(new_acct_no > 0) {
			wt = wt * 2;
			int rem = new_acct_no % 10;
			chk_digit = chk_digit + (rem * wt);
			new_acct_no = new_acct_no / 10;
		}

		chk_digit = chk_digit % 11;
		if(chk_digit > 9)
			chk_digit = 0;
		System.out.println(acct_no +""+ chk_digit);
	}
}
