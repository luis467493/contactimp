package com.leeg.contactimp.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtil {

    public static List<String> readInputStream(InputStream inputStream) throws IOException {
        List<String> content = new ArrayList<>();
        Scanner sc = null;
        try {

            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                content.add(sc.nextLine());
            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return content;
    }
}
