package com.ixchou.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/08 07:12<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 散列计算工具集<br/>
 * <b>Description</b>:
 */
public class DigestUtil {

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA-1";

    /**
     * 计算 byte[] 的 md5 值
     */
    public static String md5(byte[] data) {
        return digestAsHexString(MD5, data);
    }

    /**
     * 从 inputStream 中读取数据并计算 md5 散列值
     */
    public static String md5(InputStream inputStream) {
        try {
            return digestAsHexString(MD5, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算 byte[] 的 sha1 值
     */
    public static String sha1(byte[] data) {
        return digestAsHexString(SHA1, data);
    }

    /**
     * 从 inputStream 中读取数据并计算 sha1 散列值
     */
    public static String sha1(InputStream inputStream) {
        try {
            return digestAsHexString(SHA1, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] digest(String algorithm, byte[] bytes) {
        return getDigest(algorithm).digest(bytes);
    }

    /**
     * Create a new {@link MessageDigest} with the given algorithm.
     * Necessary because {@code MessageDigest} is not thread-safe.
     */
    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + algorithm + "\"", ex);
        }
    }

    private static byte[] digest(String algorithm, InputStream inputStream) throws IOException {
        MessageDigest messageDigest = getDigest(algorithm);
        final byte[] buffer = new byte[StreamUtils.BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            messageDigest.update(buffer, 0, bytesRead);
        }
        return messageDigest.digest();
    }

    private static String digestAsHexString(String algorithm, byte[] bytes) {
        char[] hexDigest = digestAsHexChars(algorithm, bytes);
        return new String(hexDigest);
    }

    private static String digestAsHexString(String algorithm, InputStream inputStream) throws IOException {
        char[] hexDigest = digestAsHexChars(algorithm, inputStream);
        return new String(hexDigest);
    }

    private static char[] digestAsHexChars(String algorithm, byte[] bytes) {
        byte[] digest = digest(algorithm, bytes);
        return encodeHex(digest);
    }

    private static char[] digestAsHexChars(String algorithm, InputStream inputStream) throws IOException {
        byte[] digest = digest(algorithm, inputStream);
        return encodeHex(digest);
    }

    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[bytes.length * 2];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }
}
