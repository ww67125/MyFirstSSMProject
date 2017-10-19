package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {
    //生成唯一的文件名
    public static String generateFilename() {

        //生成当前时间
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String now = df.format(new Date());

        //生成100到999之间的随机数
        Random random = new Random();
        int num = random.nextInt(900) + 100;

        return now + num;

        //return UUID.randomUUID().toString().replaceAll("-", "");

    }

    //文件改名
    public static String convertFilename(String originalFilename) {

        //获取文件的扩展名
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        //生成唯一的文件名
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        String serverFileName = df.format(new Date()) + "." + fileExtension;

        return serverFileName;
    }
}
