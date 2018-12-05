package com.pzh.www.view.student;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.pzh.www.constant.StudentFrameConstant;
import com.pzh.www.po.Course;
import com.pzh.www.po.SelectCourse;
import com.pzh.www.po.Student;
import com.pzh.www.po.Teacher;
import com.pzh.www.service.TeacherService;

/**
 * 我的课程窗口
 * @author Pan梓涵
 */
class MyCourseDialog extends JDialog {

	private StudentMainFrame studentMainFrame;
	private JScrollPane jscrollPane;
	private JTable table;//table表
	private DefaultTableModel tableModel;//表格模型
	
	private void initTable() {
		table = new JTable(tableModel);	//创建表格
		table.setRowHeight(25); 																		//设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //设置为单选
		table.setRowSorter(new TableRowSorter<>(tableModel));
	}
	
	private void initTableModel() {
		String[] columnName = StudentFrameConstant.MY_COURSE_DIALOG_TABLE_MODEL_COLUMN_NAME;
		//设置单元格不可被编辑
		tableModel = new DefaultTableModel(null,columnName) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
	}
	
	private void initJscrollPane() {
		jscrollPane = new JScrollPane(table);//创建面板
	}
	
	private void init() {
		this.initTableModel();
		this.initTable();
		this.initJscrollPane();
		this.setResizable(false);
	}

	MyCourseDialog(JFrame frame, Student s) {
		super(frame,"我的课程",true);
		if(frame instanceof StudentMainFrame) {
			this.studentMainFrame = (StudentMainFrame) frame;
		}
		this.init();
		this.add(jscrollPane,BorderLayout.CENTER);
		this.showTable(studentMainFrame.getSelectCourseList(),
				studentMainFrame.getCourseMap());
		this.setSize(480, 360);
		this.setLocation(350, 100);
	}
	
	/**
	 * 展示数据到表格中
	 */
	private void showTable(List<SelectCourse> list, Map<Integer, Course> courseMap) {
		List<Teacher> teacherList = TeacherService.getInstance().findAllTeacher();
		Map<Integer, Teacher> teacherMap = new HashMap<>(teacherList.size());
		for (Teacher teacher : teacherList) {
			teacherMap.put(teacher.getId(), teacher);
		}

		tableModel.setRowCount(0);
		for (SelectCourse selectCourse : list) {
			Course course = courseMap.get(selectCourse.getCourseId());
			String[] rowValues = {
					course.getCourseId()+"",
					course.getCourseName(),
					course.getCourseHour() + "",
					course.getCredit() + "",
					teacherMap.get(course.getGiveLensonTeacher()).getTeacherName(),
					course.getSite(),
					selectCourse.getGrade() + ""
					};
			tableModel.addRow(rowValues);
		}
		
	}
}
