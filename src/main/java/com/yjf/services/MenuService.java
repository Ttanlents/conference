package com.yjf.services;


import com.yjf.dao.MenuDao;
import com.yjf.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author 余俊锋
 * @date 2020/9/22 17:40
 * @Description
 */
public class MenuService  extends MenuDao{
    MenuDao menuDao=new MenuDao();

    public Map<String,List<Menu>> getMenuMap() {
        List<Menu> list = menuDao.getMenuAll();
        List<Menu> parent = list.stream().filter((n) -> {
            return n.getType().equals("1");
        }).collect(Collectors.toList());

        List<Menu> son=list.stream().filter((n)->{
            return n.getType().equals("2");
        }).collect(Collectors.toList());

        Map<String,List<Menu>> map=new HashMap<>();
        map.put("parent",parent);
        map.put("son",son);
        return map;
    }
}
