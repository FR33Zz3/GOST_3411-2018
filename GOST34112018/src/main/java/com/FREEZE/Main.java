package com.FREEZE;

import org.bouncycastle.jcajce.provider.digest.GOST3411;
import org.bouncycastle.util.encoders.Hex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Введите сообщение (или 'exit' для выхода): ");
            String input = reader.readLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                byte[] hash = calculateGostHash(input);
                System.out.println("Хэш сообщения: " + Hex.toHexString(hash));
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("\nПриложение завершено");
    }

    private static byte[] calculateGostHash(String message) throws Exception {
        MessageDigest digest = new GOST3411.Digest();
        byte[] bytes = message.getBytes("UTF-8");
        digest.update(bytes);
        return digest.digest();
    }
}