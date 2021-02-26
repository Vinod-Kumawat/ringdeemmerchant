package com.merchant.merchant.util;

import com.merchant.merchant.dto.ProductPOJO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUploadUtil {
    public static boolean uploadFile(byte[] bytes,String basePath, String filename)
    {
        boolean flag=false;
        try {
            byte[] bytes1 = bytes;

            File serverFile = new File(basePath+ "/" +filename);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes1);
            stream.close();
            flag=true;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            flag=false;
        }
        return flag;
    }
}
