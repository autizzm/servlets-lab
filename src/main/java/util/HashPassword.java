package util;

import java.nio.ByteBuffer;

public class HashPassword {

    public static byte[] getHash(String str){
        int hash = str.hashCode();
        return ByteBuffer.allocate(4).putInt(hash).array();
    }
}
