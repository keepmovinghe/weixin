package com.keep.main;

import com.keep.menu.*;
import com.keep.pojo.Token;
import com.keep.utils.CommonUtil;
import com.keep.utils.MenuUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 菜单管理类
 *
 * @author chenxh
 * @date 2017/6/27
 */
public class MenuManager {
    private static Logger logger = LogManager.getLogger(MenuManager.class);

    private static Menu getMenu(){
        ClickButton btn11 = new ClickButton();
        btn11.setName("开源中国");
        btn11.setType("click");
        btn11.setKey("oschina");

        ClickButton btn12 = new ClickButton();
        btn12.setName("ITeye");
        btn12.setType("click");
        btn12.setKey("iteye");

        ViewButton btn13 = new ViewButton();
        btn13.setName("CocoaChina");
        btn13.setType("view");
        btn13.setUrl("http://www.cocoachina.com");

        ViewButton btn21 = new ViewButton();
        btn21.setName("淘宝");
        btn21.setType("view");
        btn21.setUrl("http://m.taobao.com");

        ViewButton btn22 = new ViewButton();
        btn22.setName("京东");
        btn22.setType("view");
        btn22.setUrl("http://m.jd.com");

        ViewButton btn23 = new ViewButton();
        btn23.setName("唯品会");
        btn23.setType("view");
        btn23.setUrl("http://m.vipshop.com");

        ViewButton btn24 = new ViewButton();
        btn24.setName("当当网");
        btn24.setType("view");
        btn24.setUrl("http://m.dangdang.com");

        ViewButton btn25 = new ViewButton();
        btn25.setName("苏宁易购");
        btn25.setType("view");
        btn25.setUrl("http://m.suning.com");

        ViewButton btn31 = new ViewButton();
        btn31.setName("多泡");
        btn31.setType("view");
        btn31.setUrl("http://www.duopao.com");

        ViewButton btn32 = new ViewButton();
        btn32.setName("一窝88");
        btn32.setType("view");
        btn32.setUrl("http://www.yi588.com");

        ComplexButton mainButton1 = new ComplexButton();
        mainButton1.setName("技术交流");
        mainButton1.setSub_button(new Button[]{btn11,btn12,btn13});

        ComplexButton mainButton2 = new ComplexButton();
        mainButton2.setName("购物");
        mainButton2.setSub_button(new Button[]{btn21,btn22,btn23,btn24,btn25});

        ComplexButton mainButton3 = new ComplexButton();
        mainButton3.setName("网页游戏");
        mainButton3.setSub_button(new Button[]{btn31,btn32});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainButton1,mainButton2,mainButton3});
        return menu;
    }

    /*public static void main(String[] args){
        // 调用接口获取凭证
        Token token = CommonUtil.getToken(APPID,APPSECRET);
        if(null != token){
            logger.info("凭证token:{"+token.getAccessToken()+"}");
            boolean result = MenuUtil.createMenu(getMenu(),token.getAccessToken());
            if(result){
                logger.info("创建菜单成功！");
            }else{
                logger.info("创建菜单失败！");
            }
        }
    }*/

    public static void main(String[] args){
        String token = CommonUtil.getToken();
        if(null != token) {
            logger.info("凭证 token :{"+token+"}");
            String menu = MenuUtil.getMenu(token);
            logger.info("菜单：{"+menu+"}");
        }
    }
}
