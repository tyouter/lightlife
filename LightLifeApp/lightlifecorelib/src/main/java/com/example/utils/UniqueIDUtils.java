package com.example.utils;

import java.util.UUID;

/** for a unique id generation
 * Created by Administrator on 2016/12/4.
 */
public class UniqueIDUtils {

    public static String generateUUID() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }
}
