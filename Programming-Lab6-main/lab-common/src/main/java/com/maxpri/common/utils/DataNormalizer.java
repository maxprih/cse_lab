package com.maxpri.common.utils;


public final class DataNormalizer {

    private DataNormalizer() {

    }

    public static String[] normalize(String data) {
        if ("".equals(data) || data == null) {
            return new String[0];
        }
        String[] args = SmartSplitter.smartSplit(data.trim());
        for (String str : args) {
            str = str.trim();
        }
        return args;
    }
}
