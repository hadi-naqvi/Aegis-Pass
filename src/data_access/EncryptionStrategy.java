package data_access;

public interface EncryptionStrategy {
    /**
     * method that encrypts a given plaintext with the encryptionkey based on encryption strategy
     * @param plaintext  the plaintext to encrypt
     * @param key  the encryption key
     * @return encrypted string
     */
    String encrypt(String plaintext, String key);

    /**
     * method that decrypts a given ciphertext with the encryptionkey based on the encryption strategy(assuming encryption=decryption strategy)
     * @param ciphertext the ciphertext to decrypt
     * @param key the encryption key
     * @return the decrypted text
     */
    String decrypt(String ciphertext, String key);
}
