package org.example;

import java.util.UUID;

public class GeneradorId {
    public static String generar()
    {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
