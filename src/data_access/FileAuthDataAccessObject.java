package data_access;

import entity.AuthKey;
import entity.AuthKeyFactory;
import entity.CommonAuthKey;
import use_case.SetupAuth.SetupAuthDataAccessInterface;
import java.io.*;

public class FileAuthDataAccessObject implements SetupAuthDataAccessInterface {
    private final File csvFile;

    private final AuthKey authKey;

    private AuthKeyFactory authKeyFactory;


    /**
     * Constructor for FileAuthDataAccessObject
     * @param csvPath name of csv file in directory
     * @throws IOException input/output exception
     */
    public FileAuthDataAccessObject(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (csvFile.length() == 0) {
            this.authKey = null;
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                String[] col = header.split(":");
                String salt = String.valueOf(col[0]);
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
            writer.write(authKey.getKey());
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

}