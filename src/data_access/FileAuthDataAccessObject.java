package data_access;

import entity.CommonAuthKey;
import use_case.SetupAuth.SetupAuthDataAccessInterface;
import java.io.*;
import java.time.LocalDateTime;

public class FileAuthDataAccessObject implements SetupAuthDataAccessInterface {
    private final File txtFile;

    public FileAuthDataAccessObject(String txtPath) throws IOException {
        this.txtFile = new File(txtPath);

        if (txtFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {

                }
            }
        }
    }

    public void save(CommonAuthKey authKey) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(txtFile));
            writer.write(authKey.getkey());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}