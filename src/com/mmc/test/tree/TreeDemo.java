package com.mmc.test.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class TreeDemo {
	TreeNode parent = null; // Ҫ�����Ķ���ڵ�
	
	public void deepTraversal(){
		List<TreeNode> childs = getChilds(parent); // parent������ֱ���ӽڵ�  
	    if (childs != null) {  
	        for (TreeNode child : childs) {  
	            // ------------  
	            // ��ȡchild���ݣ�����������  
	            // ------------  
//	            traverse(child);  
	        }     
	    }  
	}
	
	public void weithTraversal(){
		// ��ȱ�������  
		List<TreeNode> childs = getChilds(parent); // parent������ֱ���ӽڵ�  
		while (childs != null) {  
		    List<TreeNode> tempChilds = new ArrayList<TreeNode>();  
		    for (TreeNode child : childs) { // �����ӽڵ�  
		        // ------------  
		        // ��ȡchild���ݣ�����������  
		        // ------------  
		        if (getChilds(child) != null) {  
		            tempChilds.add(child);  
		        }  
		    }  
		    childs = tempChilds;  
		}
	}
	
	private List<TreeNode> getChilds(TreeNode parent) {  
	    // �������ڵ��ȡֱ���ӽڵ�  
		return null;
	}  
}
