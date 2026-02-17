package com.mx.Usuario.AES;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AlgoritmoAES {

	// 16 - 32
    private static String SECRET_KEY = "12345678901234567890123456789012";
    private static String ALGORITHM = "AES/CBC/PKCS5Padding";

    private static SecretKeySpec getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
    }

    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            // IV aleatorio REAL
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, getKey(), ivSpec);

            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Guardamos IV + cifrado juntos
            byte[] encryptedIVAndText = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVAndText, 0, iv.length);
            System.arraycopy(encrypted, 0, encryptedIVAndText, iv.length, encrypted.length);

            return Base64.getEncoder().encodeToString(encryptedIVAndText);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error encrypting");
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            byte[] encryptedIvTextBytes = Base64.getDecoder().decode(encryptedData);

            // Extraemos IV
            byte[] iv = new byte[16];
            byte[] encryptedBytes = new byte[encryptedIvTextBytes.length - 16];

            System.arraycopy(encryptedIvTextBytes, 0, iv, 0, 16);
            System.arraycopy(encryptedIvTextBytes, 16, encryptedBytes, 0, encryptedBytes.length);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey(), new IvParameterSpec(iv));

            return new String(cipher.doFinal(encryptedBytes), StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error decrypting");
        }
    }
}
