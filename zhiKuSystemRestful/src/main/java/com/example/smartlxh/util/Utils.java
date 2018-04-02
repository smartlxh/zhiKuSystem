package com.example.smartlxh.util;

import com.example.smartlxh.model.ReturnCodeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by lixianhai on 02/04/2018.
 */
public class Utils {

    public  static ReturnCodeEnum saveFile(MultipartFile file, String filename){

        File saveFile = new File(filename);
        try {
            if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
                saveFile.getParentFile().mkdirs();
            }
            if (saveFile != null && !saveFile.exists()) {
                saveFile.createNewFile();
            }
            file.transferTo(saveFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return ReturnCodeEnum.ERROR;

        } catch (IOException e) {
            e.printStackTrace();
            return ReturnCodeEnum.ERROR;

        }
        return ReturnCodeEnum.SUCCESS;
    }
}
