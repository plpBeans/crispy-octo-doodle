/** 
 * @(#)AESUtil.java 2018-5-16
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

import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName: AESUtil 
 * @author chuzhenhua
 * @date 2018-12-10
 *  
 */
public class AesUtils {
	/**
     *生成AES秘钥，然后Base64编码
	 */
    public static String genKeyaes() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey key = keyGen.generateKey();
        String base64Str = byte2Base64(key.getEncoded());
        return base64Str;  
    }

    /**
     *  将Base64编码后的AES秘钥转换成SecretKey对象
     */
    public static SecretKey loadKeyaes(String base64Key) throws Exception {
        byte[] bytes = base642Byte(base64Key);  
        SecretKeySpec key = new SecretKeySpec(bytes, "AES");
        return key;  
    }

    /**
     * 字节数组转Base64编码
     */

    public static String byte2Base64(byte[] bytes){
        Base64EncoderBase encoder = new Base64EncoderBase();
        return encoder.encode(bytes);  
    }

    /**
     *  Base64编码转字节数组
     */
    public static byte[] base642Byte(String base64Key) throws IOException {
        Base64DecoderBase decoder = new Base64DecoderBase();
        return decoder.decodeBuffer(base64Key);  
    }

    /**
     *  加密
     */

    public static byte[] encryptAes(byte[] source, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(source);  
    }

    /**
     * 解密
     */

    public static byte[] decryptAes(byte[] source, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(source);  
    }


    /**
     *  key加密
      */

    public static String getBase64(String str) {
        String result = "";
        if( str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     *  key解密
      */

    public static String getFromBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static String setAesdata(String key,String data){
        //加密后的内容Base64编码
        String byte2Base64 = null;
        String base64Str= AesUtils.getBase64(key);
        try {
            SecretKey aesKey = AesUtils.loadKeyaes(base64Str);

            /***
             * 加密
             */
            byte[] encryptAes = AesUtils.encryptAes(data.getBytes(), aesKey);
            /***
             * 加密后的内容Base64编码
             */
            byte2Base64 = AesUtils.byte2Base64(encryptAes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return byte2Base64;
    }
    public static String getAesdata(String key,String data){
        /***
         * 解密
         */
        byte[] decryptAes = new byte[0];
            String base64Str= AesUtils.getBase64(key);
        /**
         * 将Base64编码的字符串，转换成AES秘钥
         */
        SecretKey aesKey2 = null;
        try {
            aesKey2 = AesUtils.loadKeyaes(base64Str);
            /**
             * 加密后的内容Base64解码
             */
        byte[] base642Byte = AesUtils.base642Byte(data);
            decryptAes= AesUtils.decryptAes(base642Byte, aesKey2);
            /**
             * 解密后的明文
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptAes);
    }

    /**
     * 生成AESkey
     * @param aes
     * @return
     */
    public static String getAeskey(String aes) {
        StringBuffer stringBuffer = null;
        String aesKey = null;
        //判断拼接后长度是否大于32
        int value=32;
        if (value < aes.length()) {
            aesKey = aes.substring(0, 32);
        } else if (value > aes.length()) {
            int length = 32 - aes.length();
            String index;
            stringBuffer = new StringBuffer(aes);
            for (int i = 0; i < length; i++) {
                if (String.valueOf(i).length() > 1) {
                    index = String.valueOf(i).substring(1);
                } else {
                    index = String.valueOf(i).substring(0);
                }
                stringBuffer = stringBuffer.append(String.valueOf(index));
            }
            aesKey = stringBuffer.toString();
        }
        return  aesKey;
    }

    /**
     * 加密
     * @param key  加密的公钥
     * @param message 加密的内容
     * @return  加密后的值
     * @throws Exception
     */
        public static String  aesEncrypt(String key,String message)throws Exception{
            //加密后的内容Base64编码
            String byte2Base64 = null;
            String base64Str= AesUtils.getBase64(key);
            try {
                SecretKey aesKey = AesUtils.loadKeyaes(base64Str);
                //加密
                byte[] encryptAes = AesUtils.encryptAes(message.getBytes(), aesKey);
                //加密后的内容Base64编码
                byte2Base64 = AesUtils.byte2Base64(encryptAes);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return byte2Base64;
        }

    /**
     * 解密
     * @param key 解密的公钥  （需要和加密时传的公钥一致）
     * @param message 需要解密的参数
     * @return  解密后的内容
     * @throws Exception
     */
    public static String aesDecrypt(String key,String message) throws Exception {
        //解密
        byte[] decryptAes = new byte[0];
        String base64Str= AesUtils.getBase64(key);
        //===================服务端================
        //将Base64编码的字符串，转换成AES秘钥
        SecretKey aesKey2 = null;
        try {
            aesKey2 = AesUtils.loadKeyaes(base64Str);

            //加密后的内容Base64解码
            byte[] base642Byte = AesUtils.base642Byte(message);
            decryptAes= AesUtils.decryptAes(base642Byte, aesKey2);
            //解密后的明文
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptAes);

    }

    /**
     * 由于在main方法中运行会有包之间的冲突，所以测试效果要在Android界面进行测试
     * @param args
     */
    public static void main(String[] args) {
    	try {  
//            //=================客户端=================
//            //hello, i am infi, good night!加密
            String message = "111111111111111";
////            System.out.println("加密结果:" + getBase64("1111111111111111"));
////            生成AES秘钥，并Base64编码
//            String base64Str = AesUtils.genKeyAES();
//            String base64Str=getKeyByPass();
//            String base64Str="MTExMTExMTExMTExMTExMQ==";
//            System.out.println("AES秘钥Base64编码:" + base64Str);
//            //将Base64编码的字符串，转换成AES秘钥
//            SecretKey aesKey = AesUtils.loadKeyAES(base64Str);
//            //加密
//            byte[] encryptAES = AesUtils.encryptAES(message.getBytes(), aesKey);
//            //加密后的内容Base64编码
//            String byte2Base64 = AesUtils.byte2Base64(encryptAES);
//            System.out.println("加密并Base64编码的结果：" + byte2Base64);

              
            //##############    网络上传输的内容有Base64编码后的秘钥 和 Base64编码加密后的内容      #################
            String key=getAeskey("com.medicalNursingServer355249070707389");
//            String keybase64=getBase64(key);
              String mess=aesEncrypt(key,message);
            System.out.println(mess);
            String me=aesDecrypt(key,mess);
            System.out.println(me);
              
////            ===================服务端================
////            将Base64编码的字符串，转换成AES秘钥
//            SecretKey aesKey2 = AesUtils.loadKeyAES(base64Str);
//            //加密后的内容Base64解码
//            byte[] base642Byte = AesUtils.base642Byte(byte2Base64);
//            //解密
//            byte[] decryptAES = AesUtils.decryptAES(base642Byte, aesKey2);
//            //解密后的明文
//            System.out.println("解密后的明文: " + new String(decryptAES));


        } catch (Exception e) {
            e.printStackTrace();  
        }  
	}
}
