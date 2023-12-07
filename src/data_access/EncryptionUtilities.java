package data_access;

import java.util.Base64;

public class EncryptionUtilities {
    /**
     * Method which converts a hex string to a byte array
     * @param hexString The hex string
     * @return The byte array
     */
    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Method which converts a byte array to a hexadecimal string
     * @param byteArray The byte array
     * @return The hexadecimal string
     */
    public static String byteArrayToHexString(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
