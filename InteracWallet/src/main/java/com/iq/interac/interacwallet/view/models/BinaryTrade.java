package com.iq.interac.interacwallet.view.models;

/**
 * Created by JITHENDRA on 9/8/2017.
 */

public abstract class BinaryTrade {

    byte[] data;

    public long wordFromBytesFromOffset(int offset) {
        long val = 0;
        for (int i = 0; i < 3; i++)
            val = (val << 8) + (data[offset + i] & 0xff);
        return val;
    }

    public long longFromBytesFromOffset(int offset) {
        long val = 0;
        for (int i = 0; i < 8; i++)
            val = (val << 8) + (data[offset + i] & 0xff);
        return val;
    }

    public void string2Bytes(String value, byte[] data) {
        for (int i = 0; i < value.length(); i++)
            data[i] = (byte) value.charAt(i);
    }
    public static void stringtoBytes(String value, byte[] data) {
        for (int i = 0; i < value.length(); i++)
           data[i] = (byte) value.charAt(i);
    }

    void string2BytesFromOffset(String value, byte[] data, int offset) {
        for (int i = 0; i < value.length(); i++)
            data[offset + i] = (byte) value.charAt(i);
    }

    void long2BytesFromOffset(long value, int offset) {
        data[offset] = (byte) (value >>> 56);
        data[offset + 1] = (byte) (value >>> 48);
        data[offset + 2] = (byte) (value >>> 40);
        data[offset + 3] = (byte) (value >>> 32);
        data[offset + 4] = (byte) (value >>> 24);
        data[offset + 5] = (byte) (value >>> 16);
        data[offset + 6] = (byte) (value >>> 8);
        data[offset + 7] = (byte) (value);
    }
}

