package com.pzh.www.view.student;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.pzh.www.constant.StudentFrameConstant;
import com.pzh.www.po.Student;
import com.pzh.www.service.MyClassService;
import com.pzh.www.service.StudentService;

/**
 * 班级通讯录窗体
 * @author Pan梓涵
 * 创建时间：2018/4/21
 */
class MyClassDialog extends JDialog{

	private JLabel titleLabel;
	private JScrollPane jscrollPane;
	private JTable table;//table表
	private DefaultTableModel tableModel;//表格模型
	
	
	private void initTitleLabel(int classNo) {
		titleLabel = new JLabel(MyClassService.getInstance().getClassNameByClassNo(classNo) + " 通讯录",JLabel.CENTER);
	}

	private void initTable() {
		table = new JTable(tableModel);//创建表格
		table.setRowHeight(25); //设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //设置为单选
	}
	
	private void initTableModel() {
		String[] columnName = StudentFrameConstant.MY_CLASS_DIALOG_TABLE_MODEL_COLUMN_NAME;
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
		initTableModel();
		initTable();
		initJscrollPane();
		setResizable(false);
	}

	MyClassDialog(JFrame frame, Student s) {
		super(frame,"班级通讯录",true);
		this.init();
		this.initTitleLabel(s.getClassId());
		this.add(titleLabel,BorderLayout.NORTH);
		this.add(jscrollPane,BorderLayout.CENTER);
		this.setSize(480, 360);
		this.setLocation(350, 100);
		this.showTable(StudentService.getInstance().findStudentByClassNo(s.getClassId()));
	}
	
	/**
	 * 展示数据到表格中
	 */
	private void showTable(List<Student> list) {
		//清除原先表格数据
		tableModel.setRowCount(0);
		for (Student student : list) {
			String[] rowValues = {
					student.getStudentName(),
					student.getStudentPhone(),
					student.getStudentEmail()
			};
			tableModel.addRow(rowValues);			
		}	
	}
	
	
}
