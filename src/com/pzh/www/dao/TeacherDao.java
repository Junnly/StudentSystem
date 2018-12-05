package com.pzh.www.dao;

import java.util.List;

import com.pzh.www.po.Teacher;

/**
 * 学生的业务逻辑接口
 *
 * @author Pan梓涵 创建时间 2018/4/11 19:08
 */
public interface TeacherDao {

    /**
     * 老师登录方法
     * @param t
     * @return
     */
    boolean teacherLogin(Teacher t);

    /**
     * 查询所有老师资料
     * @return
     */
    List<Teacher> findAllTeacher();

    /**
     * 根据 老师编号 查询老师资料
     * @param teacherNo
     * @return
     */
    List<Teacher> findTeacherByNo(String teacherNo);

    /**
     * 根据 姓名 查询老师资料
     * @param teacherName
     * @return
     */
    List<Teacher> findTeacherByName(String teacherName);

    /**
     * 添加 老师 资料
     * @param t
     * @return
     */
    boolean addTeacher(Teacher t);

    /**
     * 更新 老师 资料
     * @param t
     * @return
     */
    boolean updateTeacher(Teacher t);

    /**
     * 删除 老师 资料
     * @param teacherNo
     * @return
     */
    boolean deleteTeacher(String teacherNo);
}
