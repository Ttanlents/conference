package com.yjf.controller;

import com.yjf.dao.MenuDao;
import com.yjf.entity.Menu;
import com.yjf.services.MenuService;
import com.yjf.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 余俊锋
 * @date 2020/9/22 17:50
 * @Description
 */
@WebServlet("/menu/*")
public class MenuServlet  extends BaseServlet{
    MenuService menuService=new MenuService();

    public void getMenu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<Menu>> map = menuService.getMenuMap();
        JsonUtils.responseJSON(resp,map);
    }
}
