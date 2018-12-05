package com.pzh.www.service;

import java.util.List;

import com.pzh.www.constant.MyConstant;
import com.pzh.www.dao.TeacherDao;
import com.pzh.www.dao.impl.TeacherDaoImpl;
import com.pzh.www.po.Teacher;

/**
 * 对老师表的service类
 * @author Pan梓涵
 * 创建时间 2018/4/11 19:37
 */
public class TeacherService {

	private static final TeacherService INSTANCE = new TeacherService();

	private TeacherService() {}

	public static TeacherService getInstance() {
		return INSTANCE;
	}

	private TeacherDao teacherDao = new TeacherDaoImpl();

	private int pageSize = MyConstant.PAGE_SIZE; 				// 每页显示记录的条数
	private int currentPage=MyConstant.PAGE_CURRENT;	// 当前页
	private int allPage = MyConstant.PAGE_ALL; 					// 总页数
    
	public int getPageSize() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	/**
	 * 查询所有老师动作
	 * @return
	 */
	public List<Teacher> findAllTeacher(){
		return teacherDao.findAllTeacher();
	}

	/**
	 * 根据老师姓名的查询动作
	 * @param teacherName
	 * @return
	 */
	public List<Teacher> findTeacherByName(String teacherName){
		return teacherDao.findTeacherByName(teacherName);
	}

	/**
	 *  根据老师id的查询动作
	 * @param teacherNo
	 * @return
	 */
	public List<Teacher> findTeacherByNo(String teacherNo) {
		return teacherDao.findTeacherByNo(teacherNo);
	}

	/**
	 * 增加 老师 资料动作
	 * @param t
	 * @return
	 */
	public boolean addTeacher(Teacher t) {
		return teacherDao.addTeacher(t);
	}

	/**
	 * 更新 老师 资料动作
	 * @param t
	 * @return
	 */
	public boolean updateTeacher(Teacher t) {
		return teacherDao.updateTeacher(t);
	}
	

	/**
	 * 删除 老师 资料动作
	 * @param teacherNo
	 * @return
	 */
	public boolean deleteTeacher(String teacherNo) {
		return teacherDao.deleteTeacher(teacherNo);
	}

	/**
	 * 老师登录验证
	 * @param teacherNo
	 * @param teacherPassword
	 * @return
	 */
	public boolean teacherLogin(String teacherNo,String teacherPassword) {
		Teacher t = new Teacher();
		t.setTeacherNo(teacherNo);
		t.setTeacherPassword(teacherPassword);
		return teacherDao.teacherLogin(t);
	}
}
