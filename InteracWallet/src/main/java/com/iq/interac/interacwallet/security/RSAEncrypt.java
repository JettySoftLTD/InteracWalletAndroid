package com.iq.interac.interacwallet.security;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAEncrypt {
    private static byte[] tryDecodeKey(String key) {
        try {
            return Base64.decode(key.getBytes(), Base64.DEFAULT);
        } catch (IllegalArgumentException ex) {
            // do nothing
        }
        return key.getBytes();
    }

    public static byte[] encrypt(String data, String publicKey) throws Exception {
        byte[] keyByte = tryDecodeKey(publicKey);
        PublicKey pubKey = getPublicKey(keyByte);
        if (pubKey == null)
            return null;
        return encrypt(data.getBytes(), pubKey);
    }

    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-512AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private static PublicKey getPublicKey(byte[] keyBytes) {
        PublicKey publicKey = null;
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //FirebaseCrashlytics.getInstance().recordException(e);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            //FirebaseCrashlytics.getInstance().recordException(e);
        }
        return publicKey;
    }
}
