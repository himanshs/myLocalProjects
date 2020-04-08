package sample;

import java.util.Map;

@SuppressWarnings("serial")
public class FinalVariable extends RuntimeException {
	
    private final Throwable initialCause;
    final int var;
    
    public FinalVariable(final Throwable cause) {
        super(cause);
        this.initialCause = initialCause(cause);
        this.var = 50;
    }
    
    private Throwable initialCause(final Throwable cause) {
        Throwable retCause = null;
        if (cause != null) {
            if (cause instanceof FinalVariable) {
                retCause = ((FinalVariable)cause).getInitialCause();
            }
            if (retCause == null) {
                retCause = cause;
            }
        }
        return retCause;
    }
    
    public Throwable getInitialCause() {
        return initialCause;
    }
    
    
}
