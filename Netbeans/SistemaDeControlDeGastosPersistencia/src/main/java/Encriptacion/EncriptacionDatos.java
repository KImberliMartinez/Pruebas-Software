package Encriptacion;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 *
 * @author molin
 */
public class EncriptacionDatos {

    public static SecureRandom sr = new SecureRandom();

    public EncriptacionDatos() {
    }

    
    public static String encriptar(String value) {
        String clave = "pruebaencripta23";

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec sks = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(new byte[16]));

            byte[] encriptado = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encriptado);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decriptar(String encriptado) {
        String clave = "pruebaencripta23";

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec sks = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(new byte[16]));

            byte[] dec = cipher.doFinal(Base64.getDecoder().decode(encriptado));
            return new String(dec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
