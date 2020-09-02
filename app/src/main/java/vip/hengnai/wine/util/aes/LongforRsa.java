package vip.hengnai.wine.util.aes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;


/**
 * @author Hugh
 */
public class LongforRsa {

    /**
     * RSA最大加密明文大小
     */
    public static final int DEFAULT_MAX_ENCRYPT_BLOCK = 117;
    public static String encrypt(String plainText) throws Exception {
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFK8/0WUrI+n4aCaP4DQpF0lW5h28iNrGJwTkf/eJAYFhJ3lzWb7WlYJvz/3+kPq0/SHbTP0DtAvFQwb5PfrZqaJF5aBt8XGHbe7DNp6PRDrpTbcuKOg+xpZn9nBoNEB8MSL/eWprKPK3QXD1KjZzxeWB3VhmTWg8vKA4cMM+cVwIDAQAB";
        Base64DecoderBase b64d = new Base64DecoderBase();
        byte[] keyByte = b64d.decodeBuffer(publicKeyStr);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509ek);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] byteArray = segmentedProcess(cipher, plainText.getBytes(), DEFAULT_MAX_ENCRYPT_BLOCK);
        Base64EncoderBase base64Encoder = new Base64EncoderBase();
        return base64Encoder.encode(byteArray);
    }


    /**
     * @description 分段加解密数据
     * @author houjuntao
     * @date 2019/1/15 18:27
     */
    private static byte[] segmentedProcess(Cipher cipher, byte[] textArray, int maxBlock)
            throws BadPaddingException, IllegalBlockSizeException, IOException {
        int textLength = textArray.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int loopIndex = 0;

        // 对数据分段解密
        while (textLength - offSet > 0) {
            if (textLength - offSet > maxBlock) {
                cache = cipher.doFinal(textArray, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(textArray, offSet, textLength - offSet);
            }

            out.write(cache, 0, cache.length);
            loopIndex++;
            offSet = loopIndex * maxBlock;
        }

        byte[] byteArray = out.toByteArray();
        out.close();
        return byteArray;
    }
}
