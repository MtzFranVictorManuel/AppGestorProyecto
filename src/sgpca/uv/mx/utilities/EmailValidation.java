package sgpca.uv.mx.utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.String;
/**
 *
 * @author azul_
 */
public class EmailValidation {
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null){
            return false;
        }
        boolean isValid = pat.matcher(email).matches();
        if(isValid){
            return emailValid(email);
        }
        return false;
    }
    
    public static boolean emailValid(String email){
        List<String> emails = new ArrayList<String>();
        boolean isValid = false;
        emails.add("hotmail.com");
        emails.add("gmail.com");
        emails.add("estudiantes.uv.mx");
        for(String domain : emails){
            if(email.endsWith(domain)){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
