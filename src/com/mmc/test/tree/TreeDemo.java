package com.mmc.test.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class TreeDemo {
	TreeNode parent = null; // 要遍历的顶层节点
	
	public void deepTraversal(){
		List<TreeNode> childs = getChilds(parent); // parent的所有直接子节点  
	    if (childs != null) {  
	        for (TreeNode child : childs) {  
	            // ------------  
	            // 获取child内容，或其他处理  
	            // ------------  
//	            traverse(child);  
	        }     
	    }  
	}
	
	public void weithTraversal(){
		// 宽度遍历代码  
		List<TreeNode> childs = getChilds(parent); // parent的所有直接子节点  
		while (childs != null) {  
		    List<TreeNode> tempChilds = new ArrayList<TreeNode>();  
		    for (TreeNode child : childs) { // 遍历子节点  
		        // ------------  
		        // 获取child内容，或其他处理  
		        // ------------  
		        if (getChilds(child) != null) {  
		            tempChilds.add(child);  
		        }  
		    }  
		    childs = tempChilds;  
		}
	}
	
	private List<TreeNode> getChilds(TreeNode parent) {  
	    // 给定父节点获取直接子节点  
		return null;
	}  
}
