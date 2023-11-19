package data_access;

import entity.AuthKey;
import entity.AuthKeyFactory;
import entity.CommonAuthKey;
import use_case.SetupAuth.SetupAuthDataAccessInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.io.*;

public class FileAuthDataAccessObject implements SetupAuthDataAccessInterface {
    private final File csvFile;

    private final AuthKey authKey;
    private final String salt;

    private AuthKeyFactory authKeyFactory;


    /**
     * Constructor for FileAuthDataAccessObject
     * @param csvPath name of csv file in directory
     * @throws IOException input/output exception
     */
    public FileAuthDataAccessObject(String csvPath, AuthKeyFactory authKeyFactory) throws IOException {
        this.csvFile = new File(csvPath);
        this.authKeyFactory = authKeyFactory;

        if (this.csvFile.length() == 0) {
            this.authKey = null;
            this.salt = generateSalt();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                String[] col = header.split(":");
                this.salt = String.valueOf(col[0]);
                String authKey = String.valueOf(col[1]);
                this.authKey = authKeyFactory.create(authKey);
            }
        }


    }

    /**
     * Method which saves the AuthKey for the password database
     * @param authKey The authentication key
     */
    @Override
    public void save(CommonAuthKey authKey) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            String hashedSaltedKey = "";
            try {
                hashedSaltedKey = hashPassword(this.salt, authKey.getKey());
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            writer.write(this.salt + ":" + hashedSaltedKey);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public String getHashedAuthKey(){
//        return
//    }

//    public String getKeySalt(){
//
//    }

    /**
     * Method which writes the AuthKey to the password database
     */
    private void save() {
        BufferedWriter writer;
        try {

            writer = new BufferedWriter(new FileWriter(csvFile));
            String line = String.format("%s", authKey.getKey());
            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return bytesToHex(saltBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = salt + password;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(saltedPassword.getBytes());

        // Convert bytes to hexadecimal representation
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}