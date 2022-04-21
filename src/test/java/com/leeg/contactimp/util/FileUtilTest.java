package com.leeg.contactimp.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileUtilTest {

    @Test
    public void readInputStream() throws IOException {
        String rowContent = "name1;phone1;address1;credit_card1;franchise1;email1;dob1";
        InputStream anyInputStream = new ByteArrayInputStream(rowContent.getBytes());

        List<String> content = FileUtil.readInputStream(anyInputStream);

        Assert.assertEquals(1, content.size());
        Assert.assertEquals(rowContent, content.get(0));
    }

//    @Test
//    public void readInputStreamThrowsExcp() {
//        InputStream anyInputStream = null;
//
//        when()
//
//        Assert.assertThrows(IOException.class, () -> {
//            FileUtil.readInputStream(anyInputStream);
//        });
//    }
}
