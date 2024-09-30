package com.example.jthaicustomersupport;

import java.util.Arrays;

public class Attachment {
    private String name;
    private byte[] contents;

    public Attachment(String name, byte[] contents) {
        this.name = name;
        this.contents = contents;
    }

    public Attachment() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public byte[] getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }
}
