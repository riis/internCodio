package riis.bankjoy;

import android.text.TextUtils;
import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Vault
{
    public static final String PREF_NAME = "preferences";
    public static final String VAULT_STRING = "vault_key";

    private static String getBaseUrl()
    {
        return "https://bankjoyapi.azurewebsites.net/api";
    }

    static String getLoginUrl()
    {
        return getBaseUrl() + "/auth";
    }

    static String getPayeeListUrl()
    {
        return getBaseUrl() + "/billpaypayee?Page=1&Count=30";
    }

    static String getPayeeInfoUrl(String id)
    {
        return getBaseUrl() + "/billpaypayee/" + id;
    }

    /**
     * Encrypts a String with AES in ECB mode with PKCS7 padding.
     *
     * @param original
     *            The string to encrypt
     * @return The encrypted string
     */
    public static String encrypt(String original)
    {
        if(TextUtils.isEmpty(original)) {
            return original;
        }
            
        byte[] key = md5("1z2y3x".getBytes());
        final SecretKeySpec sks = new SecretKeySpec(key, "AES");
        byte[] encrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            encrypted = cipher.doFinal(original.getBytes());
        } catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException nsae) {
            nsae.printStackTrace();
        }

        if(null == encrypted) {
            return null;
        }

        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    /**
     * Decrypts the string with a specified key.
     *
     * @param encrypted
     *            The string to decrypt
     * @return The decrypted string
     */
    public static String decrypt(String encrypted)
    {
        if(TextUtils.isEmpty(encrypted)) {
            return encrypted;
        }

        byte[] key = md5("1z2y3x".getBytes());
        final SecretKeySpec sks = new SecretKeySpec(key, "AES");
        byte[] decrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, sks);
            decrypted = cipher.doFinal(Base64.decode(encrypted, Base64.NO_WRAP));
        } catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException nsae) {
            nsae.printStackTrace();
        }

        if(null == decrypted) {
            return null;
        }

        return new String(decrypted);
    }

    private static byte[] md5(byte[] input) {
        return hash(input, "MD5");
    }

    private static byte[] hash(byte[] input, String algorithm) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch(NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            return null;
        }
        md.update(input, 0, input.length);
        return md.digest();
    }
}
