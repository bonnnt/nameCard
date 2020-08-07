package com.business;

import com.model.Card;
import com.model.User;
import com.sun.source.tree.TryTree;
import com.utils.CommonUtils;
import com.utils.FileUtils;
import com.utils.InitUtils;

import javax.swing.text.MaskFormatter;
import javax.swing.text.StyledEditorKit;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * @Description TODO 系统功能实现类
 * @Classname Action
 * @Author Kehao Liu
 * @Date 2020/7/23 9:56 下午
 * @Version V1.0
 */
public class Action {
    /**
     * 注册
     */
    public static boolean regUser() {
        //1、用户输入
        CommonUtils.input("******************用户注册********************", false);
        String name = CommonUtils.input("请输入您的用户名：", true);
        String pwd = CommonUtils.input("请输入您的密码：", true);
        String comfirmPwd = CommonUtils.input("请再次确认密码：", true);
        CommonUtils.input("*********************************************", false);

        //2、写入user.txt
        boolean flag = FileUtils.writefile(InitUtils.userFileName, name + "-" + pwd);

        //3、根据写文件结果输出不同的内容
        if (flag) {
            CommonUtils.input("恭喜你，注册成功！", false);
            return true;
        } else {
            CommonUtils.input("注册失败，请重新注册！", false);
            return false;
        }
    }

    /**
     * 登陆
     */
    public static boolean login() {
        //1、用户输入
        CommonUtils.input("******************用户登陆********************", false);
        String name = CommonUtils.input("请输入您的用户名：", true);
        String pwd = CommonUtils.input("请输入您的密码：", true);
        CommonUtils.input("*********************************************", false);

        //2、读取文件 user.txt
        List users = FileUtils.readUserFile(InitUtils.userFileName);

        //3、遍历读取list，判断是否有匹配，匹配到说明登陆成功；否则说明登陆失败
        boolean flag = false; //是否登陆的标志，false登陆失败 true登陆成功
        for (Object object : users) {
            User user = (User) object;
            if (name.trim().equals(user.getUsername()) && pwd.equals(user.getPwd())) {//验证用户名与密码
                flag = true;//登陆成功
                break;
            }
        }

        //4、更具验证的flag，打印相关的用户友好提示信息
        if (flag) {
            CommonUtils.input("恭喜你，登陆成功", false);
        } else {
            CommonUtils.input("登陆失败，请重新登陆！", false);
        }

        return flag;
    }

    /**
     * 新增名片
     *
     * @param
     */
    public static boolean addCard() {
        String name = "";
        String job = "";
        String address = "";
        String company = "";
        String mobile = "";
        String yesOrNo = "";

        boolean result = false;

        //1、用户输入
        CommonUtils.input("**************新增名片界面**************", false);
        name = CommonUtils.input("请输入姓名：", true);
        job = CommonUtils.input("请输入职业：", true);
        mobile = CommonUtils.input("请输入手机号码：", true);
        company = CommonUtils.input("请输入公司名称：", true);
        address = CommonUtils.input("请输入地址：", true);
        yesOrNo = CommonUtils.input("是否保存？（yes｜no）", true);
        CommonUtils.input("***************************************", false);

        //2、写入文件
        if ("yes".equalsIgnoreCase(yesOrNo) || "y".equalsIgnoreCase(yesOrNo)) {
            boolean flag = FileUtils.writefile(InitUtils.cardFileName, name + "-" + job + "-" + mobile + "-" + company + "-" + address);
            if (flag) {
                CommonUtils.input("恭喜你，新增名片成功", false);
                result = true;
            } else {
                CommonUtils.input("新增名片失败，请重新增加名片！", false);
            }
        }
        return result;
    }

    /**
     * 修改名片
     */
    public static boolean updateCard() {
        boolean result = false;
        String name = ""; //搜索用的姓名
        String cardMsg = "";
        String yesOrNo = "";

        while (true) {
            CommonUtils.input("**************修改名片界面**************", false);
            name = CommonUtils.input("请输入要修改名片的姓名：", true);
            CommonUtils.input("***************************************", false);

            //2、根据姓名查找
            List cards = FileUtils.readCardFile(InitUtils.cardFileName);
            String msg = ""; //用来保存查询结果
            for (Object obj : cards) {
                Card card = (Card) obj;
                if (name.equals(card.getName())) {
                    msg = card.getName() + "-" + card.getJob() + "-" + card.getMobile() + "-" + card.getCompany() + "-" + card.getAddress();
                    cards.remove(card);//移除要修改的数据
                    break;
                }
            }
            if (null != msg && !"".equals(msg)) {//根据msg是否为空来判断是否有查询到结果
                //3、显示查询结果
                CommonUtils.input("您要修改的名片是：" + msg, false);

                //4、输入修改的名片信息
                cardMsg = CommonUtils.input("请输入要修改的名片信息：", true);
                yesOrNo = CommonUtils.input("是否保存？（yes|no）", true);

                if ("yes".equalsIgnoreCase(yesOrNo) || "y".equalsIgnoreCase(yesOrNo)) {
                    //写文件
                    //1、删除原card.txt
                    //2、在新card.txt中输入没有修改过的card数据
                    //3、在新的card.txt中写入修改过的card数据
                    FileUtils.writeFileForUpdate(InitUtils.cardFileName, cards, cardMsg);
                    result = true;
                    CommonUtils.input("恭喜你，修改名片成功!", false);
                    break;
                } else {
                    break;
                }
            } else {
                CommonUtils.input("没有搜索到符合条件的名片！请重新输入名片姓名！", false);
            }
        }
        return result;
    }


    /**
     * 搜索名片
     *
     * @param
     * @param
     * @return
     */
    public static boolean searchCard() {
        boolean result = true;
        String name = ""; //搜索用的姓名
        String yesOrNo = "";

        while (true) {
            CommonUtils.input("**************搜索名片界面**************", false);
            name = CommonUtils.input("请输入要搜索名片的姓名：", true);
            CommonUtils.input("***************************************", false);

            //2、根据姓名查找
            List cards = FileUtils.readCardFile(InitUtils.cardFileName);
            String findMsg = ""; //用来保存查询结果
            for (Object obj : cards) {
                Card card = (Card) obj;
                if (name.equals(card.getName())) {
                    findMsg = card.getName() + "-" + card.getJob() + "-" + card.getMobile() + "-" + card.getCompany() + "-" + card.getAddress();
                    break;
                }
            }
            if (null != findMsg && !"".equals(findMsg)) {//根据msg是否为空来判断是否有查询到结果
                //3、显示查询结果
                CommonUtils.input("符合条件的名片是：" + findMsg, false);
                yesOrNo = CommonUtils.input("是否继续搜索？（yes|no）", true);

                if ("yes".equalsIgnoreCase(yesOrNo) || "y".equalsIgnoreCase(yesOrNo)) {
                    //继续搜索
                } else {
                    break;
                }
            } else {
                CommonUtils.input("没有搜索到符合条件的名片！请重新输入名片姓名！", false);
            }
        }
        return result;
    }

    /**
     * 删除名片
     */
    public static boolean deleteCard() {
        boolean result = false;
        String name = ""; //搜索用的姓名
        String cardMsg = "";
        String yesOrNo = "";

        while (true) {
            CommonUtils.input("**************删除名片界面**************", false);
            name = CommonUtils.input("请输入要删除名片的姓名：", true);
            CommonUtils.input("***************************************", false);

            //2、根据姓名查找
            List cards = FileUtils.readCardFile(InitUtils.cardFileName);
            String msg = ""; //用来保存查询结果
            for (Object obj : cards) {
                Card card = (Card) obj;
                if (name.equals(card.getName())) {
                    msg = card.getName() + "-" + card.getJob() + "-" + card.getMobile() + "-" + card.getCompany() + "-" + card.getAddress();
                    cards.remove(card);//移除要修改的数据
                    break;
                }
            }
            if (null != msg && !"".equals(msg)) {//根据msg是否为空来判断是否有查询到结果
                //3、显示查询结果
                CommonUtils.input("您要删除的名片是：" + msg, false);

                //4、输入修改的名片信息
                yesOrNo = CommonUtils.input("是否删除？（yes|no）", true);

                if ("yes".equalsIgnoreCase(yesOrNo) || "y".equalsIgnoreCase(yesOrNo)) {
                    result = true;
                    CommonUtils.input("删除名片成功!", false);
                    break;
                } else {
                    break;
                }
            } else {
                CommonUtils.input("没有搜索到符合条件的名片！请重新输入名片姓名！", false);
            }
        }
        return result;
    }

    /**
     * 合并名片
     *
     * @param
     * @return true 合并成功 false 合并失败
     */
    public static boolean mergeCard() {
        boolean result = false;
        String name = ""; //搜索用的姓名
        String yesOrNo = "";

        while (true) {
            CommonUtils.input("**************合并名片界面**************", false);
            name = CommonUtils.input("请输入要合并名片的姓名：", true);
            CommonUtils.input("***************************************", false);

            //2、根据姓名查找
            List cards = FileUtils.readCardFile(InitUtils.cardFileName);
            String msg = ""; //用来保存查询结果
            List keepCards = new ArrayList();//保存未匹配到的名片
            Card mergeCard = new Card();//合并后的名片对象
            for (Object obj : cards) {
                Card card = (Card) obj;
                if (null != name && !"".equals(name) && name.equals(card.getName())) {
                    msg += "、" + card.getName() + "-" + card.getJob() + "-" + card.getMobile() + "-" + card.getCompany() + "-" + card.getAddress();

                    //合并数据
                    mergeCard.setName(card.getName());
                    mergeCard.setJob(null==mergeCard.getJob()||"".equals(mergeCard.getJob()) ? card.getJob() : mergeCard.getJob()+","+card.getJob());
                    mergeCard.setMobile(null==mergeCard.getMobile()||"".equals(mergeCard.getMobile()) ? card.getMobile() : mergeCard.getMobile()+","+card.getMobile());
                    mergeCard.setCompany(null==mergeCard.getCompany()||"".equals(mergeCard.getCompany()) ? card.getCompany() : mergeCard.getCompany()+","+card.getCompany());
                    mergeCard.setAddress(null==mergeCard.getAddress()||"".equals(mergeCard.getAddress()) ? card.getAddress() : mergeCard.getAddress()+","+card.getAddress());
                } else {
                    keepCards.add(card);
                }
            }
            keepCards.add(mergeCard);

            if (null != msg && !"".equals(msg)) {
                msg = msg.substring(1);
                CommonUtils.input("符合条件的名片是:" + msg, false);

                //合并名片
                yesOrNo = CommonUtils.input("是否合并？（yes|no）", true);
                if("Yes".equalsIgnoreCase(yesOrNo)||"y".equalsIgnoreCase(yesOrNo)){
                    boolean mergeResult = FileUtils.writeFileForUpdate(InitUtils.cardFileName, keepCards, null);
                    if (mergeResult){
                        CommonUtils.input("恭喜您，合并成功!",false);
                        break;
                    }
                }
            } else {
                CommonUtils.input("没有搜索到符合条件的名片！", false);
            }
        }
        return result;
    }

    /**
     * 备份文件
     */
    public static boolean backupCard(){
        boolean result = false;
        String descFile = "";// 备份文件
        String yesOrNo = "";

        try {
            CommonUtils.input("**************备份名片界面**************", false);
            descFile = CommonUtils.input("请输入备份文件的路径：", true);
            yesOrNo = CommonUtils.input("是否备份？（yes|no）", true);
            CommonUtils.input("***************************************", false);

            if ("yes".equalsIgnoreCase(yesOrNo) || "y".equalsIgnoreCase(yesOrNo)){
                result = FileUtils.copyFile(InitUtils.diretoryName+ File.separator+InitUtils.cardFileName, descFile);

                if (result){
                    CommonUtils.input("名片备份成功!",false);
                }else{
                    CommonUtils.input("名片备份失败！",false);
                }
            }
        }catch (Exception e){
             e.printStackTrace();
        }
        return result;
    }

    public static boolean resumeCard(){
        boolean result = false;
        String descFile = "";// 恢复文件
        String yesOrNo = "";

        try {
            CommonUtils.input("**************恢复名片界面**************", false);
            descFile = CommonUtils.input("请输入恢复文件的路径：", true);
            yesOrNo = CommonUtils.input("是否恢复？（yes|no）", true);
            CommonUtils.input("***************************************", false);

            if ("yes".equalsIgnoreCase(yesOrNo) || "y".equalsIgnoreCase(yesOrNo)){
                result = FileUtils.copyFile(descFile,InitUtils.diretoryName+ File.separator+InitUtils.cardFileName);

                if (result){
                    CommonUtils.input("名片恢复成功!",false);
                }else{
                    CommonUtils.input("名片恢复失败！",false);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 退出系统
     */
    public static void exitSystem() {
        System.out.println("感谢您的使用，下次再见");
        System.exit(0);
    }
}