package br.com.introcdc.textstream.components.login;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 22:42
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.Base64;

/**
 * Methods used here was taken from my other java project
 */
public interface LoginComponents {

    static boolean isInt(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    static String hashSha512(String passwordToHash) {
        return hashSha512(passwordToHash, passwordToHash);
    }

    static String hashSha512(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(String.valueOf(salt.hashCode()).getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return generatedPassword;
    }

    static String stringToAes(String string, String key) {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            return new String(Base64.getEncoder().encode(cipher.doFinal(string.getBytes())))
                    .replace("/", ".-++.-++-.")
                    .replace("\\", ".-++-.++-.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    static String aesToString(String string, String key) {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(string
                    .replace(".-++.-++-.", "/")
                    .replace(".-++-.++-.", "\\"))));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    static String currentDate() {
        return toDate(System.currentTimeMillis());
    }

    static String toDate(long number) {
        return StringClassComponents.TIME_FORMATTER.format(Instant.ofEpochMilli(number).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    class StringClassComponents {
        public static DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder().appendValue(ChronoField.DAY_OF_MONTH, 2)
                .appendLiteral('/').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('/')
                .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral(" - ")
                .appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                .appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
    }

}
