package com.liseh.bll.utility;

import java.util.UUID;

public class CommonUtils {
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
