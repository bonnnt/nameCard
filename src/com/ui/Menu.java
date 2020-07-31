package com.ui;

/**
 * @Description TODO
 * @Classname Menu
 * @Author Kehao Liu
 * @Date 2020/7/23 9:15 下午
 * @Version V1.0
 */
public class Menu {
    /**
     * 主菜单显示
     */
    public void mainMenuView(){
        System.out.println("************欢迎使用名片管理系统************");
        System.out.println("1、注册");
        System.out.println("2、登陆");
        System.out.println("3、退出");
        System.out.println("******************************************");
    }

    public void subMenuView(){
        System.out.println("*************菜单界面**************");
        System.out.println("1、新增界面");
        System.out.println("2、修改界面");
        System.out.println("3、删除界面");
        System.out.println("4、搜索界面");
        System.out.println("5、合并界面");
        System.out.println("6、备份界面");
        System.out.println("7、恢复界面");
        System.out.println("8、退出系统");
        System.out.println("**********************************");
    }
}
