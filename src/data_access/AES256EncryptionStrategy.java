package data_access;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AES256EncryptionStrategy implements EncryptionStrategy {

    /**
     * Method which encrypts data using AES-256 given an encryption key
     * @param plaintext The data being encrypted as a string of regular characters
     * @param key The encryption key as a hexadecimal string
     * @return The encrypted data in a hexadecimal string
     */
    @Override
    public String encrypt(String plaintext, String key) {
        if (plaintext.equals("")){
            return "";
        }
        try {
            Key secretKey = new SecretKeySpec(EncryptionUtilities.hexStringToByteArray(key), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            return EncryptionUtilities.byteArrayToHexString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method which decrypts data using AES-256 given an encryption key
     * @param ciphertext The data being decrypted as a hexademical string
     * @param key The encryption key as a hexadeicmal string
     * @return The decrypted ciphertext as a string of regular characters
     */
    @Override
    public String decrypt(String ciphertext, String key) {
        if (ciphertext.equals("")){
            return "";
        }
        try {
            Key secretKey = new SecretKeySpec(EncryptionUtilities.hexStringToByteArray(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
