package vip.hengnai.wine.util.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import vip.hengnai.wine.Constants;


/**
 * @author Hugh
 */
public class TrustHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        if(Constants.getBaseUrl().contains(hostname)){
            return true;
        }
        return  false;
    }
}
