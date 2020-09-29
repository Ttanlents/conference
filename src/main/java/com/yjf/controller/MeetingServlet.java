package com.yjf.controller;


import com.yjf.entity.Dept;
import com.yjf.entity.Meeting;
import com.yjf.entity.Page;
import com.yjf.entity.User;
import com.yjf.services.DeptService;
import com.yjf.services.MeetingService;
import com.yjf.utils.JsonUtils;
import com.yjf.utils.timeUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 余俊锋
 * @date 2020/9/25 12:08
 * @Description
 */
@WebServlet("/meet/*")
public class MeetingServlet extends BaseServlet {
    MeetingService meetingService = new MeetingService();
    DeptService deptService = new DeptService();

    /**
     * @param resp
     * @return void
     * @Description TODO:分页显示 会议列表
     * @author 余俊锋
     * @date 2020/9/27 9:26
     * @params req
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageCurrent = "1";
        String pageNumber = req.getParameter("pageCurrent");
        if (pageNumber != null) {
            pageCurrent = pageNumber;
        }
        int count = meetingService.getCount();
        Page<List<Meeting>> page = new Page<>(Integer.parseInt(pageCurrent), 5, count);
        List<Meeting> list = meetingService.listAllMeet(page);
        for (Meeting meeting : list) {
           meeting.setPublishDate(timeUtil.dateTimetoStr(meeting.getPublishDate()));
        }
        req.setAttribute("list", list);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/html/meet/list.jsp").forward(req, resp);
    }

    /**
     * @param resp
     * @return void
     * @Description TODO:会议发布
     * @author 余俊锋
     * @date 2020/9/27 9:27
     * @params req
     */
    public void addMeeting(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        Meeting meeting = new Meeting();
        try {
            BeanUtils.populate(meeting, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishDate = dateFormat.format(new Date());
        meeting.setPublishDate(publishDate);
        String[] makeUsers = map.get("makeUsers");
        StringBuilder makeUser = new StringBuilder();
        for (int i = 0; i < makeUsers.length; i++) {
            if (i == (makeUser.length() - 1)) {
                makeUser.append(makeUsers[i]);
                continue;
            }
            makeUser.append(makeUsers[i] + ",");
        }
        meeting.setMakeUser(makeUser.toString());
        meeting.setStatus(0);
        meetingService.addMeeting(meeting);
        resp.sendRedirect("/meet/list");
    }

    /**
     * @param resp
     * @return void
     * @Description TODO:获取所有的部门  ajax
     * @author 余俊锋
     * @date 2020/9/27 9:27
     * @params req
     */
    public void getDept(HttpServletRequest req, HttpServletResponse resp) {
        List<Dept> deptList = deptService.getDeptList();
        try {
            JsonUtils.responseJSON(resp, deptList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param resp
     * @return void
     * @Description TODO:获取该部门的所有用户  ajax
     * @author 余俊锋
     * @date 2020/9/27 9:28
     * @params req
     */
    public void getUserByDeptId(HttpServletRequest req, HttpServletResponse resp) {
        String deptId = req.getParameter("deptId");
        List<User> userList = meetingService.getUserByDeptId(Integer.parseInt(deptId));
        try {
            JsonUtils.responseJSON(resp, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param response
     * @return void
     * @Description 去会议详情页面
     * @author 余俊锋
     * @date 2020/9/26 10:26
     * @params request
     */
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> needJoinPerson = new ArrayList<>();
        String id = request.getParameter("id");
        Meeting meeting = meetingService.getMeetingById(Integer.parseInt(id));
        meeting.setPublishDate(timeUtil.dateTimetoStander(meeting.getPublishDate()));
        meeting.setStartTime(timeUtil.dateTimetoStander(meeting.getStartTime()));
        meeting.setEndTime(timeUtil.dateTimetoStander(meeting.getEndTime()));
        String makeUser = meeting.getMakeUser();
        if (makeUser.contains("[")) {
            makeUser = makeUser.substring(1, makeUser.lastIndexOf("]"));
        }
        String[] strings = makeUser.split(",");
        for (String s : strings) {
            needJoinPerson.add(Integer.parseInt(s.trim()));
        }

        //需要参加会议
        //实到id
        List<Integer> actuallyList = meetingService.getUsersIdBymeetingId(meeting.getId());
        if (needJoinPerson.contains(baseUser.getId())) {
            //flag
            boolean flag = !actuallyList.contains(baseUser.getId());
            if (flag) {
                //参加
                map.put("flag", 2);
            } else {
                //取消
                map.put("flag", 3);
            }
        } else {
            //不需要参加会议
            map.put("flag", 1);
        }
        //应到
        int should = meeting.getMakeUser().split(",").length;
        //实到
        int actually = actuallyList.size();
        //未到
        int notJoin = should - actually;
        map.put("should", should);
        map.put("actually", actually);
        map.put("notJoin", notJoin);
        request.setAttribute("map", map);
        request.setAttribute("meet", meeting);
        request.getRequestDispatcher("/html/meet/detail.jsp").forward(request, response);
    }


    /**
     * @param response
     * @return void
     * @Description TODO:参加会议
     * @author 余俊锋
     * @date 2020/9/27 9:29
     * @params request
     */
    public void joinMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();
        String id = request.getParameter("meetId");
        int userId = baseUser.getId();
        int meetId = Integer.parseInt(id);
        Meeting meeting = meetingService.getMeetingById(meetId);
        Integer status = meeting.getStatus();
        if (status == 0) {
            meetingService.JoinTheMeet(userId, meetId);
            map.put("code", "200");
        } else {
            map.put("code", "400");
        }
        JsonUtils.responseJSON(response, map);
    }

    /***
     *@Description TODO:取消参加会议
     *@author 余俊锋
     *@date 2020/9/27 9:29
     *@params request
     * @param response
     *@return void
     */
    public void cancelMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();
        String id = request.getParameter("meetId");
        int userId = baseUser.getId();
        int meetId = Integer.parseInt(id);
        Meeting meeting = meetingService.getMeetingById(meetId);
        Integer status = meeting.getStatus();
        if (status == 0) {
            meetingService.cancleJoinMeeting(userId, meetId);
            map.put("code", "200");
        } else {
            map.put("code", "400");
        }
        JsonUtils.responseJSON(response, map);
    }

/**
 *@Description TODO:删除一个会议
 *@author 余俊锋
 *@date 2020/9/27 9:30
 *@params request
 * @param response
 *@return void
 */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageNumber = request.getParameter("pageCurrent");
        meetingService.deleteMeetingById(Integer.parseInt(id));
        response.sendRedirect("/meet/list?pageCurrent=" + pageNumber);
    }
/**
 *@Description TODO:修改会议
 *@author 余俊锋
 *@date 2020/9/27 9:30
 *@params request
 * @param response
 *@return void
 */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Meeting meeting = new Meeting();
        try {
            BeanUtils.populate(meeting, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] makeUsers = map.get("makeUsers");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < makeUsers.length; i++) {
            if (i == (makeUsers.length - 1)) {
                builder.append(makeUsers[i]);
                continue;
            }
            builder.append(makeUsers[i] + ",");
        }
        meeting.setMakeUser(builder.toString());

        String pageCurrent = request.getParameter("pageCurrent");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        meeting.setPublishDate(dateFormat.format(new Date()));
        meetingService.updateMeet(meeting);
        response.sendRedirect("/meet/list?pageCurrent=" + pageCurrent);
    }

    /**
     *@Description TODO:回显修改页面
     *@author 余俊锋
     *@date 2020/9/27 9:31
     *@params request
     * @param response
     *@return void
     */
    public void toupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String id = request.getParameter("id");
        String pageCurrent = request.getParameter("pageCurrent");
        request.setAttribute("pageCurrent", pageCurrent);
        Meeting meeting = meetingService.getMeetingById(Integer.parseInt(id));
        String startTime = meeting.getStartTime();
        if (startTime != null && !startTime.equals("")) {
            Date date = null;
            try {
                date = dateFormat.parse(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateTime = dateFormat.format(date).replace(" ", "T");
            meeting.setStartTime(dateTime);
        }
        String endTime = meeting.getEndTime();
        if (endTime != null && !endTime.equals("")) {
            Date date = null;
            try {
                date = dateFormat.parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateTime = dateFormat.format(date).replace(" ", "T");
            meeting.setEndTime(dateTime);
        }
        request.setAttribute("meet", meeting);
        request.getRequestDispatcher("/html/meet/update.jsp").forward(request, response);
    }

}
