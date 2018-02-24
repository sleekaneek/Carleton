import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExpiringPolicy extends Policy {

    private Date expiryDate;

    public ExpiringPolicy(float amt, Date da) {
        super(amt);
        expiryDate = da;
    }
    public ExpiringPolicy(float amt) {
        super(amt);
        GregorianCalendar aCalendar = new GregorianCalendar();
        aCalendar.add(Calendar.YEAR,1);
        expiryDate = aCalendar.getTime();
    }
    public Date getexpiryDate(){ return expiryDate; }

    public String toString() {
       String output = "";
       SimpleDateFormat dateFormater = new SimpleDateFormat("MMMM dd, yyyy (hh:mma)");
        if (isExpired()) {
           output += String.format("ExpiringPolicy: %04d amount: $%1.2f",
                   getPolicyNumber(), getAmount());
           output += (" expired on: " + dateFormater.format(expiryDate));
           return output;
       }
       else {
            output += String.format("ExpiringPolicy: %04d amount: $%1.2f",
                    getPolicyNumber(), getAmount());
            output += (" expires: " + dateFormater.format(expiryDate));
            return output;
        }
    }

    public boolean isExpired() {
        return GregorianCalendar.getInstance().getTime().after(expiryDate);
    }
}


