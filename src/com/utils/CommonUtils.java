package com.utils;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @Description TODO
 * @Classname CommonUtils
 * @Author Kehao Liu
 * @Date 2020/7/22 12:20 上午
 * @Version V1.0
 */
public class CommonUtils {
    /**
     * 打印 和 打印并接受输入
     * @param msg 需要打印显示的信息
     * @param isInput 是否需要获取输入信息
     * @return 用户的屏幕输入
     */
    public static String input(String msg,boolean isInput){

        String input = "";
        if (isInput){//需要用户输入
            System.out.print(msg);
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
        }else {//不需要用户输入
            System.out.println(msg);
        }
        return input;
    }

    /**
     * 用户选择菜单的方法
     *输入 菜单的提示信息
     * @return
     */
    public static Integer selectMenu(String msgTips, int maxOption){
        Integer menuNo = null;//用户选择的菜单
        Scanner scanner = new Scanner(System.in);
        System.out.println(msgTips);//打印提示信息

        while(true){//控制用户输入直到正确为止
            try{
                String input = scanner.next();
                menuNo = Integer.parseInt(input);
                if(menuNo<=0||menuNo>maxOption){
                    System.out.println("没有您选择的操作，请重新选择");
                }else{
                    break;//输入正确 终止程序
                }
            }catch (Exception e){
                System.out.println("输入错误，请输入数字！");
            }
        }
        return menuNo;
    }
}
