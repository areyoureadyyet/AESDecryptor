import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AESDecryptor {

    public static void main(String[] args) {
        try {
            // Create a Scanner object to read input from the console
            Scanner scanner = new Scanner(System.in);

            // Get input for the secret key
            System.out.print("Enter the secret key: ");
            String key = scanner.nextLine();

            // Get input for the encrypted data
            System.out.print("Enter the encrypted data: ");
            String encryptedData = scanner.nextLine();

            // Convert the key from hex string to byte array
            byte[] keyBytes = hexStringToByteArray(key);

            // Convert the encrypted data from Base64 string to byte array
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

            // Create a SecretKeySpec object using the key
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            // Create a Cipher object and initialize it for decryption using the key
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // Decrypt the data
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Convert the decrypted byte array to a string
            String decryptedData = new String(decryptedBytes);

            // Print the decrypted data
            System.out.println("Decrypted data: " + decryptedData);
            
            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility method to convert a hex string to a byte array
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
