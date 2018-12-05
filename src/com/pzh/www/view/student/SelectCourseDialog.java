package com.pzh.www.view.student;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.pzh.www.constant.StudentFrameConstant;
import com.pzh.www.po.Course;
import com.pzh.www.po.SelectCourse;
import com.pzh.www.po.Student;
import com.pzh.www.view.CheckDialog;

/**
 * 选课窗口
 * @author Pan梓涵
 */
class SelectCourseDialog extends JDialog{
	
	private JScrollPane jscrollPane;
	private JPanel southPanel;//南部面板
	private JTable table;//table表
	private DefaultTableModel tableModel;//表格模型
	private JButton comfirmButton;//确定选课按钮
	private JButton cancelButton;//退选
	private int selectedRow = -1;//表格选中行的索引，默认值为-1
	private StudentMainFrame studentMainFrame;
	private Student student;
	
	private void initTable() {
		table = new JTable(tableModel);	//创建表格
		table.setRowHeight(25); //设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); //设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //设置为单选
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = table.getSelectedRow();
				if(tableModel.getValueAt(selectedRow, 4).equals(StudentFrameConstant.SELECT_COURSE_DIALOG_SELECT)) {
					selectedRow = -1;
				}
			}
		});
	}
	
	private void initTableModel() {
		String[] columnName = StudentFrameConstant.SELECT_COURSE_DIALOG_TABLE_MODEL_COLUMN_NAME;
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
	
	private void initSouthPanel() {
		this.southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 2));
		southPanel.add(comfirmButton);
		southPanel.add(cancelButton);
	}

	private void initComfirmButton() {
		comfirmButton = new JButton(StudentFrameConstant.SELECT_COURSE_DIALOG_CONFIRM_BUTTON);
		comfirmButton.setFont(StudentFrameConstant.SELECT_COURSE_DIALOG_BUTTON_FONT);
		comfirmButton.addActionListener(e -> {

			if(selectedRow == -1 || StudentFrameConstant.SELECT_COURSE_DIALOG_SELECT.equals(
					tableModel.getValueAt(selectedRow, 6))) {
				new CheckDialog(getSelectCourseDialog(), "未正确点击课表或已选了此课程").setVisible(true);
			}else {

				if(studentMainFrame.getSelectCourseService().addSelectCourse(
						student.getId(), Integer.parseInt(tableModel.getValueAt(selectedRow, 0) + ""))) {
					studentMainFrame.setSelectCourseList(
							studentMainFrame.getSelectCourseService().
							findSelectCourseByStudentId(student.getId())
							);
					getSelectCourseDialog().showTable(
							studentMainFrame.getCourseMap(),
							studentMainFrame.getSelectCourseList()
							);
					new CheckDialog(getSelectCourseDialog(), "选课成功！").setVisible(true);
				}else {
					new CheckDialog(getSelectCourseDialog(), "选课失败！").setVisible(true);
				}

			}
			selectedRow = -1;
		});
	}
	
	private void initCancelButton() {
		this.cancelButton = new JButton(StudentFrameConstant.SELECT_COURSE_DIALOG_CANCEL_BUTTON);
		cancelButton.setEnabled(false);
		cancelButton.setFont(StudentFrameConstant.SELECT_COURSE_DIALOG_BUTTON_FONT);
	}

	private void init() {
		this.initTableModel();
		this.initTable();
		this.initComfirmButton();
		this.initCancelButton();
		this.initJscrollPane();
		this.initSouthPanel();
	}

	SelectCourseDialog(JFrame frame, Student s) {
		super(frame,"选课报名",true);
		if(frame instanceof StudentMainFrame) {
			studentMainFrame = (StudentMainFrame) frame;
		}
		this.student = s;
		this.init();
		this.add(jscrollPane,BorderLayout.CENTER);
		this.add(southPanel,BorderLayout.SOUTH);
		this.showTable(studentMainFrame.getCourseMap(),studentMainFrame.getSelectCourseList());
		this.setSize(480, 360);
		this.setLocation(350, 100);
		this.setResizable(false);
	}
	
	/**
	 * 展示数据到表格中
	 */
	private void showTable(Map<Integer, Course> map, List<SelectCourse> selectCourseList) {
		//获取所有的课程编号
		int[] courseIds = new int[selectCourseList.size()];
		int i = 0;
		for (SelectCourse sc : selectCourseList) {
			courseIds[i] = sc.getCourseId();
			i++;
		}
		//设置结果集
		tableModel.setRowCount(0);
		Collection<Course> coll = map.values();

		for (Course c : coll) {
			String[] rowValues = {
					c.getCourseId() + "",
					c.getCourseName() + "",
					c.getCourseHour() + "",
					c.getCredit() + "",
					c.getSite(),
					studentMainFrame.getTeacherMap().get(c.getGiveLensonTeacher()).getTeacherName(),
					StudentFrameConstant.SELECT_COURSE_DIALOG_NOT_SELECT
			};
			for (int courseId : courseIds) {
				if (c.getCourseId() == courseId) {
					rowValues[6] = StudentFrameConstant.SELECT_COURSE_DIALOG_SELECT;
				}

			}
			tableModel.addRow(rowValues);
		}
	}
	
	private SelectCourseDialog getSelectCourseDialog() {
		return this;
	}
}
