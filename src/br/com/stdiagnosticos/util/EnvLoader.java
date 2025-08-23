package br.com.stdiagnosticos.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {

    private static final Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(".env");
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Não foi possível carregar o arquivo .env");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
