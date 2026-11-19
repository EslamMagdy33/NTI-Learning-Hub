package com.nti.hub.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    private PasswordHasher() {
    }
    public static String hash(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }
    public static  boolean match(String password,String hashedPassword){
        return BCrypt.checkpw(password,hashedPassword);
    }
}
