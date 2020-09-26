package com.yjf.services;

import com.yjf.dao.DeptDao;
import com.yjf.entity.Dept;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/23 19:36
 * @Description
 */
public class DeptService {
    DeptDao deptDao=new DeptDao();

    public List<Dept> getDeptList(){
        List<Dept> deptList = deptDao.getAllDept();
        return deptList;
    }
}
