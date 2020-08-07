package com.utils;

import com.model.Card;
import com.model.User;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 文件操作类
 * @Classname FileUtils
 * @Author Kehao Liu
 * @Date 2020/8/5 9:59 上午
 * @Version V1.0
 */
public class FileUtils {
    /**
     * 写文件
     */
    public  static boolean writefile(String fileName, String data){
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            File file = new File(InitUtils.diretoryName + File.separator + fileName);//创建文件，如果文件不存在，会自动创建文件；如果存在，则向文件里追加内容
            fw = new FileWriter(file,true);//如果文件里没内容，则添加内容，如果文件里已经有内容，则向文件里追加内容

            pw = new PrintWriter(fw);
            pw.println(data);

            pw.flush();//刷新缓存，将内容输出到磁盘上

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                pw.close();
                fw.close();
            }catch(Exception e){
                    e.printStackTrace();
                }
        }
    }

    /**
     * 读用户文件 user.txt
     * @param userFileName:用户文件名
     * @return 用户文件里所有用户的集合
     */
    public static List readUserFile(String userFileName){
        FileReader fr = null;
        BufferedReader br = null;
        List list = new ArrayList();//用来存储从文件中读取的内容
        try {
            fr = new FileReader(InitUtils.diretoryName + File.separator + userFileName);
            br = new BufferedReader(fr);
            String line = "";//用来存入读出的每一行，一次存一行
            String[] arrs = null;
            while ((line=br.readLine()) != null){
                arrs = line.split("-");

                User user = new User();
                user.setUsername(arrs[0]);//用户名
                user.setPwd(arrs[1]);//密码

                list.add(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                fr.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 读用户文件 card.txt
     * @param cardFileName:名片文件名
     * @return 文件里所有名片的集合
     */
    public static List readCardFile(String cardFileName){
        FileReader fr = null;
        BufferedReader br = null;
        List list = new ArrayList();//用来存储从文件中读取的内容
        try {
            fr = new FileReader(InitUtils.diretoryName + File.separator + cardFileName);
            br = new BufferedReader(fr);
            String line = "";//用来存入读出的每一行，一次存一行
            String[] arrs = null;
            while ((line=br.readLine()) != null){
                arrs = line.split("-");

                Card card = new Card();
                card.setName(arrs[0]);
                card.setJob(arrs[1]);
                card.setMobile(arrs[2]);
                card.setCompany(arrs[3]);
                card.setAddress(arrs[4]);

                list.add(card);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                fr.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     *修改名片
     * @param fileName 名片文件
     * @param datas 未修改的名片数据
     * @param updateData 需要修改的数据
     * @return 是否成功修改名片
     */
    public static boolean writeFileForUpdate(String fileName, List datas, String updateData){
        //1、删除原来card.txt
        File  file = new File(InitUtils.diretoryName+File.separator+InitUtils.cardFileName);
        file.delete();

        //2、将未修改的数据写入到新的card.txt文件中去
        for(Object obj:datas){
            Card card = (Card) obj;
            writefile(fileName, card.toString());
        }

        //3、写入修改后的名片信息
        if (null!=updateData && !"".equals(updateData)){
            writefile(fileName, updateData);
        }
        return true;
    }

    /**
     * 复制文件
     * @param srcFile 源文件
     * @param descfile 目标文件
     * @return
     */
    public static boolean copyFile(String srcFile, String descfile){
        boolean flag = true;
        FileReader fr = null;
        FileWriter fw = null;

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            fr = new FileReader(srcFile);
            br = new BufferedReader(fr);

            fw = new FileWriter(descfile);
            bw = new BufferedWriter(fw);

            String s = "";
            while ((s = br.readLine()) != null){
                bw.write(s + "\r\n"); //换行回车
            }
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bw.close();
                fw.close();
                br.close();
                fr.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return flag;
    }
}
