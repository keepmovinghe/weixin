package com.keep.utils;

import com.keep.menu.Menu;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 菜单工具类
 *
 * @author chenxh
 * @date 2017/6/27
 */
public class MenuUtil {
    private static Logger logger = LogManager.getLogger(MenuUtil.class);

    /**
     * 创建菜单
     * @param menu
     * @param accessToken
     * @return
     */
    public static boolean createMenu(Menu menu, String accessToken){
        boolean result = false;
        String url = Constant.MENU_CREATE_URL.replace("ACCESS_TOKEN",accessToken);
        // 将菜单对象转换为JSON字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 发送 POST 请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url,"POST",jsonMenu);

        if(null != jsonObject){
            int errorCode = jsonObject.getInt("errorCode");
            String errorMsg = jsonObject.getString("errorMsg");
            if(0 == errorCode){
                result = true;
            }else{
                result = false;
                logger.error("创建菜单失败 errorCode:{"+errorCode+"} errorMsg:{"+errorMsg+"}");
            }
        }
        return result;
    }

    /**
     * 查询菜单
     * @param accessToken
     * @return
     */
    public static String getMenu(String accessToken){
        String result = null;
        String url = Constant.MENU_GET_URL.replace("ACCESS_TOKEN",accessToken);
        // 发起 GET 请求查询菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url,"GET",null);
        if(null != jsonObject){
            result = jsonObject.toString();
        }
        return result;
    }

    /**
     * 删除菜单
     * @param accessToken
     * @return
     */
    public static boolean deleteMenu(String accessToken){
        boolean result = false;
        String url = Constant.MENU_DELETE_URL.replace("ACCESS_TOKEN",accessToken);
        // 发起 GET 请求删除菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url,"GET",null);
        if(null != jsonObject){
            int errorCode = jsonObject.getInt("errorCode");
            String errorMsg = jsonObject.getString("erroeMsg");
            if(0 == errorCode){
                result = true;
            }else{
                result = false;
                logger.error("删除菜单失败 errorCode:{"+errorCode+"} errorMsg:{"+errorMsg+"}");
            }
        }
        return result;
    }
}
