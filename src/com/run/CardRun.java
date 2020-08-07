package com.run;

import com.business.Action;
import com.ui.Menu;
import com.utils.CommonUtils;
import jdk.jshell.ImportSnippet;

import java.util.Scanner;

/**
 * @Description TODO
 * @Classname cardRun 主函数
 * @Author Kehao Liu
 * @Date 2020/7/23 1:57 下午
 * @Version V1.0
 */
public class CardRun {
    public static void main(String[] args) {
//        CommonUtils.input("****************名片管理系统***************", false);
//        String name = CommonUtils.input("请输入您的用户名：", true);
//        String pwd = CommonUtils.input("请输入您的密码：", true);
//        System.out.println(name+"===="+pwd);
        //1、显示主菜单界面
        Menu menu = new Menu();

        while (true){
            menu.mainMenuView();

            //2、接受用户输入的菜单号
            int menuNo = CommonUtils.selectMenu("请输入您选择的操作：", 3);
            //System.out.println(menuNo);

            //3、用户的操作
            if(menuNo==1){//注册
                Action.regUser();
            }else if(menuNo==2){//登陆
                boolean isLoginSuc = Action.login();
                if(isLoginSuc){//登陆成功 显示子菜单

                    while (true){
                        menu.subMenuView();
                        int subMenuNo = CommonUtils.selectMenu("请输入您选择的操作：", 8);
                        if(subMenuNo==1){//新增名片
                            Action.addCard();
                        }else if(subMenuNo==2){//修改界面
                            Action.updateCard();
                        }else if(subMenuNo==3){//删除界面
                            Action.deleteCard();
                        }else if(subMenuNo==4){//搜索界面
                            Action.searchCard();
                        }else if(subMenuNo==5){//合并界面
                            Action.mergeCard();
                        }else if(subMenuNo==6){//备份名片
                            Action.backupCard();
                        }else if(subMenuNo==7){//恢复界面
                            Action.resumeCard();
                        }else if(subMenuNo==8){//退出系统
                            Action.exitSystem();
                        }
                    }
                }
            }else if(menuNo==3){//退出
                Action.exitSystem();
            }
        }
    }
}
