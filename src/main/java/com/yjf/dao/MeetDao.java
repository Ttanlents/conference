package com.yjf.dao;

import com.yjf.entity.Meeting;
import com.yjf.entity.Page;
import com.yjf.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/9/25 10:48
 * @Description
 */
public class MeetDao extends BaseDao {

    public List<Meeting> listMeet(Page<List<Meeting>> page) {
        String sql = "SELECT\n" +
                "\tm.id,\n" +
                "\tm.title,\n" +
                "\tm.content,\n" +
                "\tm.publish_date publishDate,\n" +
                "\tm.start_time startTime,\n" +
                "\tm.end_time endTime,\n" +
                "\tm.`status`,\n" +
                "\tm.make_user makeUser,\n" +
                "\td.NAME deptName \n" +
                "FROM\n" +
                "\tmeeting m\n" +
                "\tLEFT JOIN dept d ON d.id = m.dept_id limit ?,?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class), (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public List<Meeting> listMeet() {
        String sql = "SELECT\n" +
                "\tm.id,\n" +
                "\tm.title,\n" +
                "\tm.content,\n" +
                "\tm.publish_date publishDate,\n" +
                "\tm.start_time startTime,\n" +
                "\tm.end_time endTime,\n" +
                "\tm.`status`,\n" +
                "\tm.make_user makeUser,\n" +
                "\td.NAME deptName \n" +
                "FROM\n" +
                "\tmeeting m\n" +
                "\tLEFT JOIN dept d ON d.id = m.dept_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class));
    }

    public Integer getMeetCount() {
        String sql = "select count(*) from meeting";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void addMeet(Meeting meeting) {
        String sql = "INSERT INTO meeting (title,content,publish_date,start_time,end_time,`status`,make_user,dept_id ) " +
                "VALUES" +
                "(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, meeting.getTitle(), meeting.getContent(), meeting.getPublishDate(), meeting.getStartTime(),
                meeting.getEndTime(), meeting.getStatus(), meeting.getMakeUser(), meeting.getDeptId());
    }

    public List<User> getUserByDeptId(int deptId) {
        String sql = "SELECT " +
                "u.id," +
                "u.username," +
                "u.real_name " +
                "FROM " +
                "USER u " +
                "WHERE " +
                "dept_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), deptId);
    }

    public void updateStatusTimer(int status, int meetId) {
        String sql = "update meeting set status=? where id=?";
        jdbcTemplate.update(sql, status, meetId);
    }

    public Meeting getMeetingById(int id) {
        String sql = "SELECT\n" +
                "\tm.id,\n" +
                "\tm.title,\n" +
                "\tm.content,\n" +
                "\tm.publish_date publishDate,\n" +
                "\tm.start_time startTime,\n" +
                "\tm.end_time endTime,\n" +
                "\tm.`status`,\n" +
                "\tm.make_user makeUser,\n" +
                "\td.NAME deptName \n" +
                "FROM meeting m LEFT JOIN dept d ON d.id = m.dept_id where m.id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Meeting.class), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteByMeetId(int id){
        String sql="delete from meeting where id=?";
        String sql2="delete from meeting_join where m_id=?";
        jdbcTemplate.update(sql,id);
        jdbcTemplate.update(sql2,id);
    }

    public void updateMeet(Meeting meeting){
        String sql="UPDATE meeting \n" +
                "SET title =?,\n" +
                "content =?,\n" +
                "publish_date =?,\n" +
                "start_time =?,\n" +
                "end_time =?,\n" +
                "make_user=?,\n" +
                "dept_id =? \n" +
                "WHERE\n" +
                "id =?";
        jdbcTemplate.update(sql,meeting.getTitle(),meeting.getContent(),meeting.getPublishDate(),meeting.getStartTime()
                ,meeting.getEndTime(),meeting.getMakeUser(),meeting.getDeptId(),meeting.getId());
    }
}
