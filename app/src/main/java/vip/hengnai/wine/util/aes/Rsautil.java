/** 
 * @(#)RSAUtil.java 2018-5-16
 * 
 * Copyright (c) 1995-2016 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package vip.hengnai.wine.util.aes;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


/** 
 * @ClassName: Rsautil
 * @author chenlin
 * @date 2018-5-16 上午09:30:53 
 *  
 */
public class Rsautil {
    /***
     * //生成秘钥对
     * @return
     * @throws Exception
     */

    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;  
    }

    /***
     * 获取公钥(Base64编码)
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();  
        return byte2Base64(bytes);  
    }

    /***
     * 获取私钥(Base64编码)
     * @param keyPair
     * @return
     */
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();  
        return byte2Base64(bytes);  
    }

    /***
     * 将Base64编码后的公钥转换成PublicKey对象
     * @param pubStr
     * @return
     * @throws Exception
     */
    public static PublicKey string2PublicKey(String pubStr) throws Exception {
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;  
    }

    /***
     * 将Base64编码后的私钥转换成PrivateKey对象
     * @param priStr
     * @return
     * @throws Exception
     */
    public static PrivateKey string2PrivateKey(String priStr) throws Exception {
        byte[] keyBytes = base642Byte(priStr);  
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;  
    }

    /***
     *
     * @param content
     * @param publicKey
     * @return 公钥加密
     * @throws Exception
     */

    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /***
     *
     * @param content
     * @param privateKey
     * @return私钥解密
     * @throws Exception
     */

    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /***
     * 字节数组转Base64编码
      * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes){
        Base64EncoderBase encoder = new Base64EncoderBase();
        return encoder.encode(bytes);  
    }

    /***
     * Base64编码转字节数组
     * @param base64Key
     * @return
     * @throws IOException
     */
    public static byte[] base642Byte(String base64Key) throws IOException {
        Base64DecoderBase decoder = new Base64DecoderBase();
        return decoder.decodeBuffer(base64Key);
    }  
    
    public static void main(String[] args) {
    	try {  
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================  
            //生成RSA公钥和私钥，并Base64编码  
            String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9XL61n+aNg2tOnOKzXFKNjhcGgzVAZUUgddwkwCkqqcg0YUaTbJuKnv3VK4q1kG3uNe2BtNw3Xabz6SPMspPqUMTc1XzE36F2eV8FzTLqKDX/vYz7XD1KBxlGLE6P31pXv6D2RvQtV5YZ4DehTZm/Cbp2QsQlAc6cHZipDJzsLtQ7TgGWk20bmXQvu3Qhcd58+/oXMeyS2fiV3YRbziKF9rGg/2SO4mPFC1zKrRH5ODac/wZcqP1KW6ynNBKFbYofkX7mHcWTcJojdmSACHIKwOD2uPhMYN1VJMHdtFeBtW6tuprD9+cjM3T6PppKIZ0PIglFC9gGcM5+ZQRwMs4awIDAQAB";

            String privateKeyStr = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQD1cvrWf5o2Da06c4rNcUo2OFwaDNUBlRSB13CTAKSqpyDRhRpNsm4qe/dUrirWQbe417YG03DddpvPpI8yyk+pQxNzVfMTfoXZ5XwXNMuooNf+9jPtcPUoHGUYsTo/fWle/oPZG9C1XlhngN6FNmb8JunZCxCUBzpwdmKkMnOwu1DtOAZaTbRuZdC+7dCFx3nz7+hcx7JLZ+JXdhFvOIoX2saD/ZI7iY8ULXMqtEfk4Npz/Blyo/UpbrKc0EoVtih+RfuYdxZNwmiN2ZIAIcgrA4Pa4+Exg3VUkwd20V4G1bq26msP35yMzdPo+mkohnQ8iCUUL2AZwzn5lBHAyzhrAgMBAAECggEAUKcik59EbiSaWo2i+5URfLWSViEHUUjKf0vquvNIKKdYWmCxXvyVxLINeK7udo8Lrtp6/s011h/mXRnG0Dq4aHcZChohELPPieUlnH6FuXh44VfHgmtOs4p5Ed8Ss9Ai8ssRSiMgtqg0xnYLKJzSxGKJDv/ax7mEU3YjPa8yYszVsYflTsAULsnEXfNAXODC4QUAywgkxd6JNNFopZPn9kMHgL1PlJt89gRwE6UV0bVGJjA1KCuaYhbeSZ7k4znkhhpcO0zz0cMY87x+FkpBGkQNVIF4URld0F5GfmVf2UH6Qw8JAsWHzKTL5ZmCYtrvMFvZYRLjZO9B9w6ev4GMGQKBgQD8iPhbOSMhthAaE2EyduTLfudOcVB8uqigSh0Fjh0C/5gaK+SZVWt25XzB0y+8TqNqBOyJChsX7wFVSaUAbJA+n7wQN211JUk6JLLWfBAb5HUecr/cEa87u/hsiB4Zmv1ngdWPad/FOmH67YrvPrHah275yPPCPOZNSvbx7xLhfQKBgQD40R7WNuqC8l7ss+YgpGUIPDA5lQH5zmF5w+YWaXO3MKp0eZxo8NPWbQk7DBCov+q6D264bfJD4avUXJsPn7lMnq4HswxTm0wC/8Yr4VvrG39W3JIKxqzzBb4+9jb9ut03b/srAzQs7z9ksVXGfTiodNtti2YrNW4Pzmf9KlCmBwKBgDb1vE9mLP4uOGzkYNfxxjN/h+mrgZ/To9VWq+BEoI3BgKHZgaPZUk0K/s7UHmR2HNx6+9uLRoA79OdR4sYn2nC2pOBGn9zJ45jy2bUSRRM88jxRLu4/LwrqCtPb/+kEsTewqwDOQvQSk7ZfskgFNgHgTcew81QHmvuhYAMhS9OlAoGAFCAuI1ubym26WeEPSzKg7XKY3/96gm88GTJMSSHMd1PbZcJ0cVSRffWh2oHWUx/654PaC9bw0qbNVEcNT802msj3AK5t39Fe65sut1vqKHE38pmywmZhlOKxCby2o/1aaWKIaWk/7iaH9SgW7RAx5gZVvV2aLQKMXYDvjl2+2j8CgYAQrMNX8ePtFtIKCAOGhx7nlx1uMb2BhFphwgXyP/FWOx1ZIC06wcNf+TVP4lq2+f2BMDdtH0moJXDJzgfyKbLr76CZFldC0aiGMHFzODIuV6aPdHwBCcM3zAv1X33m0hBCSug3BT7GQmIa107lm7Uq2WbqthmeVkJ7t9F3VBW7ZQ==";
            String publicEncryptStr="MftWwP6+v6IcrQQyYTJPW9zXloeuA94JP9DS1GL4Rryus+T6tTV0PZKtXYhwNf+5PGHJn7l8SQDjhImpCPO0PAV3YXDPEtO9EyAmcVGTHWLVZNDfPoa3MLfKBC2DR4DQdeOpqGzFlM1PLKBds5tsnxL1lsDlUwS7TeyIGz0fb65SE0miabMO2b0b1BtAMD4JBlM+5AEsjv5guMAFlIcl535YE/lSV9suNHfPo2ap7rjk41BM9Ln5BGZiCe86CrTNWzLqwHSKbRGOFxmO0K8L+KgCGpiVW0qRlmYy/jbuE0VH26vFmY2fnJ+kI54T9er7en24mOAQ4aVMBlmCfgZeYQ==\n";
            System.out.println("加密结果"+publicEncryptStr);
              
            //##############    网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################  


            /**
             * 将Base64编码后的私钥转换成PrivateKey对象
             * 如果需要自己加密自己解密测试需要将加密解密方式设置为一样
             */
            PrivateKey privateKey = Rsautil.string2PrivateKey(privateKeyStr);
            System.out.println("加密后字节长度"+publicEncryptStr.getBytes().length);
            /**
             * 公钥加密AES秘钥后的内容(Base64编码)，进行Base64解码
             */
            byte[] publicEncrypt2 = Rsautil.base642Byte(publicEncryptStr);
            /**
             * 用私钥解密,得到aesKey
             */
            byte[] aesKeyStrBytes = Rsautil.privateDecrypt(publicEncrypt2, privateKey);
            /**
             * 解密后的aesKey
             */
            String aesKeyStr2 = new String(aesKeyStrBytes);
            /**
             * 解密后的明文
             */
            System.out.println("解密后"+aesKeyStr2);

        } catch (Exception e) {
            e.printStackTrace();  
        }  
	}
}
