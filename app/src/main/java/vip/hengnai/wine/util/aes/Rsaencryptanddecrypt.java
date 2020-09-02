package vip.hengnai.wine.util.aes;

import android.util.Log;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

/***
 * 加密封装
 * @author dell
 */
public class Rsaencryptanddecrypt {
    private  String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFK8/0WUrI+n4aCaP4DQpF0lW5\n" +
            "h28iNrGJwTkf/eJAYFhJ3lzWb7WlYJvz/3+kPq0/SHbTP0DtAvFQwb5PfrZqaJF5\n" +
            "aBt8XGHbe7DNp6PRDrpTbcuKOg+xpZn9nBoNEB8MSL/eWprKPK3QXD1KjZzxeWB3\n" +
            "VhmTWg8vKA4cMM+cVwIDAQAB";

    private  String privateKeyStr = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQD1cvrWf5o2Da06c4rNcUo2OFwaDNUBlRSB13CTAKSqpyDRhRpNsm4qe/dUrirWQbe417YG03DddpvPpI8yyk+pQxNzVfMTfoXZ5XwXNMuooNf+9jPtcPUoHGUYsTo/fWle/oPZG9C1XlhngN6FNmb8JunZCxCUBzpwdmKkMnOwu1DtOAZaTbRuZdC+7dCFx3nz7+hcx7JLZ+JXdhFvOIoX2saD/ZI7iY8ULXMqtEfk4Npz/Blyo/UpbrKc0EoVtih+RfuYdxZNwmiN2ZIAIcgrA4Pa4+Exg3VUkwd20V4G1bq26msP35yMzdPo+mkohnQ8iCUUL2AZwzn5lBHAyzhrAgMBAAECggEAUKcik59EbiSaWo2i+5URfLWSViEHUUjKf0vquvNIKKdYWmCxXvyVxLINeK7udo8Lrtp6/s011h/mXRnG0Dq4aHcZChohELPPieUlnH6FuXh44VfHgmtOs4p5Ed8Ss9Ai8ssRSiMgtqg0xnYLKJzSxGKJDv/ax7mEU3YjPa8yYszVsYflTsAULsnEXfNAXODC4QUAywgkxd6JNNFopZPn9kMHgL1PlJt89gRwE6UV0bVGJjA1KCuaYhbeSZ7k4znkhhpcO0zz0cMY87x+FkpBGkQNVIF4URld0F5GfmVf2UH6Qw8JAsWHzKTL5ZmCYtrvMFvZYRLjZO9B9w6ev4GMGQKBgQD8iPhbOSMhthAaE2EyduTLfudOcVB8uqigSh0Fjh0C/5gaK+SZVWt25XzB0y+8TqNqBOyJChsX7wFVSaUAbJA+n7wQN211JUk6JLLWfBAb5HUecr/cEa87u/hsiB4Zmv1ngdWPad/FOmH67YrvPrHah275yPPCPOZNSvbx7xLhfQKBgQD40R7WNuqC8l7ss+YgpGUIPDA5lQH5zmF5w+YWaXO3MKp0eZxo8NPWbQk7DBCov+q6D264bfJD4avUXJsPn7lMnq4HswxTm0wC/8Yr4VvrG39W3JIKxqzzBb4+9jb9ut03b/srAzQs7z9ksVXGfTiodNtti2YrNW4Pzmf9KlCmBwKBgDb1vE9mLP4uOGzkYNfxxjN/h+mrgZ/To9VWq+BEoI3BgKHZgaPZUk0K/s7UHmR2HNx6+9uLRoA79OdR4sYn2nC2pOBGn9zJ45jy2bUSRRM88jxRLu4/LwrqCtPb/+kEsTewqwDOQvQSk7ZfskgFNgHgTcew81QHmvuhYAMhS9OlAoGAFCAuI1ubym26WeEPSzKg7XKY3/96gm88GTJMSSHMd1PbZcJ0cVSRffWh2oHWUx/654PaC9bw0qbNVEcNT802msj3AK5t39Fe65sut1vqKHE38pmywmZhlOKxCby2o/1aaWKIaWk/7iaH9SgW7RAx5gZVvV2aLQKMXYDvjl2+2j8CgYAQrMNX8ePtFtIKCAOGhx7nlx1uMb2BhFphwgXyP/FWOx1ZIC06wcNf+TVP4lq2+f2BMDdtH0moJXDJzgfyKbLr76CZFldC0aiGMHFzODIuV6aPdHwBCcM3zAv1X33m0hBCSug3BT7GQmIa107lm7Uq2WbqthmeVkJ7t9F3VBW7ZQ==";

     PublicKey publicKey = Rsautil.string2PublicKey(publicKeyStr);
    /***
     * 生成AES秘钥，并Base64编码
     */
    String aesKeyStr = AesUtils.genKeyaes();
    /***
     * 用公钥加密AES秘钥
     */
    byte[] publicEncrypt = Rsautil.publicEncrypt(aesKeyStr.getBytes(), publicKey);
    /***
     * 公钥加密AES秘钥后的内容Base64编码
     */
    String publicEncryptStr = Rsautil.byte2Base64(publicEncrypt);

    public Rsaencryptanddecrypt() throws Exception {
    }


    public Object[] rsaEncrypt(String message) throws Exception {
        //生成AES秘钥，并Base64编码
        String aesKeyStr = AesUtils.genKeyaes();
        Log.e("KEY=========",aesKeyStr);
        PublicKey publicKey = Rsautil.string2PublicKey(publicKeyStr);
        /**
         * 生成AES秘钥，并Base64编码
         * 用公钥加密AES秘钥
         */
        byte[] publicEncrypt = Rsautil.publicEncrypt(aesKeyStr.getBytes(), publicKey);

        /**
         * 公钥加密AES秘钥后的内容Base64编码
         */
        String publicEncryptStr = Rsautil.byte2Base64(publicEncrypt);
        /**
         * 将Base64编码后的AES秘钥转换成SecretKey对象
         */
        SecretKey aesKey = AesUtils.loadKeyaes(aesKeyStr);
        /**
         * 用AES秘钥加密实际的内容
         */
        byte[] encryptAes = AesUtils.encryptAes(message.getBytes("UTF-8"), aesKey);
        /**
         * AES秘钥加密后的内容Base64编码
         */
        String encryptAesStr = AesUtils.byte2Base64(encryptAes);
        Object[] os=new Object[2];
        /**
         * 加密后的aes密钥
         */
        os[0]=aesKeyStr.toString();
        Log.e("KEY加密后",publicEncryptStr.toString());
        os[1]=encryptAesStr.toString();
        return os;
    }
    public String rsaDecrypt(String key, String message) throws Exception {

        /**
         * 将Base64编码后的私钥转换成PrivateKey对象
         */
        PrivateKey privateKey = Rsautil.string2PrivateKey(privateKeyStr);
        /**
         * 公钥加密AES秘钥后的内容(Base64编码)，进行Base64解码
         */
        byte[] publicEncrypt2 = Rsautil.base642Byte(key);
        /**
         * 用私钥解密,得到aesKey
         */
        byte[] aesKeyStrBytes = Rsautil.privateDecrypt(publicEncrypt2, privateKey);

        SecretKey aesKey2 = AesUtils.loadKeyaes(key);
        /**
         * AES秘钥加密后的内容(Base64编码)，进行Base64解码
         */
        byte[] encryptAes2 = AesUtils.base642Byte(message);
        /**
         * 用AES秘钥解密实际的内容
         */
        byte[] decryptAes = AesUtils.decryptAes(encryptAes2, aesKey2);
        Log.e("解密后的内容",new String(decryptAes));
        /**
         * 解密后的实际内容
         */
        return new String(decryptAes);
    }




}
