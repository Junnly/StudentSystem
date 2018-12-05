package com.pzh.www.view.teacher;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.pzh.www.po.MyClass;

/**
 * 表格模型
 * @author Pan梓涵
 */
public class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String>{
	
	private String selectedItem = null;
	private String[] myClass;
	
	public MyComboBox() {
		
	}
	
	public MyComboBox(List<MyClass> myClassList) {
		myClass = new String[myClassList.size()];
		for(int i = 0;i < myClass.length;i++) {
			MyClass mc = myClassList.get(i);
			myClass[i] = mc.getClassName();
		}
	}
	
	@Override
	public int getSize() {
		return myClass.length;
	}

	@Override
	public String getElementAt(int index) {
		return myClass[index];
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedItem = (String) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	} 
	

}
