package world.ucode.utilits;

import world.ucode.pojo.Registration;
import world.ucode.model.BidRunner;
import world.ucode.model.RegistrationRunner;

import javax.servlet.http.HttpServletRequest;

public class CheckUser {

    public boolean checkSignIn(String login, String IPadress, int hash) {
        RegistrationRunner registRun = new RegistrationRunner();
        registRun.getSessionFactory();
        Registration reg = registRun.getPass(login);
        if (reg != null) {
            if (IPadress.equals(reg.getIPadress()) == true &&
                    hash == reg.getHash())
                return true;
        }
        return false;
    }

    public boolean checkRole(String login, String needRole) {
        RegistrationRunner registRun = new RegistrationRunner();
        registRun.getSessionFactory();
        Registration reg = registRun.getPass(login);
        if (needRole.equals(reg.getRole()) == true)
                return true;

        return false;
    }

    private static final String[] HEADERS_TO_TRY = {
            "X-FORWARDED-FOR",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    public String getClientIpAddress(HttpServletRequest request) {
        String ip = new String();
        for (String header : HEADERS_TO_TRY) {
            ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        if(ip == null)
            ip = request.getRemoteAddr();
        return ip;
    }
}
