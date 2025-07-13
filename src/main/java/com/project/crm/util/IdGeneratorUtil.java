package com.project.crm.util;

import java.util.UUID;

public class IdGeneratorUtil {

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
