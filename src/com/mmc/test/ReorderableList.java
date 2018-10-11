package com.mmc.test;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * ��ȡEXCEL�ļ����ݣ�д�뵽TXT�ļ��У���������ʾ
 */
public class ReorderableList extends JList implements DragSourceListener, DropTargetListener, DragGestureListener {
	/**
	 * ��Դ
	 */
	DragSource dragSource;
	/**
	 * ����Ŀ��
	 */
	DropTarget dropTarget;
	/**
	 * ���ö���
	 */
	Object dropTargetCell;
	/**
	 * ���϶���index
	 */
	int draggedIndex = -1;

	/**
	 * ��ȡ���ö���
	 * 
	 * @return
	 */
	public Object getDropTargetCell() {
		return dropTargetCell;
	}

	public ReorderableList() {
		super();
		dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_MOVE, this);
		dropTarget = new DropTarget(this, this);
	}

	public void dragGestureRecognized(DragGestureEvent dge) {
		Point clickPoint = dge.getDragOrigin();
		int index = locationToIndex(clickPoint);
		if (index == -1)
			return;
		Object target = getModel().getElementAt(index);
		Transferable trans = new ReportObjTransferable(target);
		draggedIndex = index;
		dragSource.startDrag(dge, Cursor.getDefaultCursor(), trans, this);
	}

	public void dragEnter(DropTargetDragEvent dtde) {
		if (dtde.getSource() != dropTarget)
			dtde.rejectDrag();
		else {
			dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
		}
	}

	public void dragOver(DropTargetDragEvent dtde) {

		if (dtde.getSource() != dropTarget)
			dtde.rejectDrag();
		// �õ��Ϸŵ�Ŀ��λ��
		Point dragPoint = dtde.getLocation();
		int index = locationToIndex(dragPoint);
		if (index == -1)
			dropTargetCell = null;
		else
			dropTargetCell = getModel().getElementAt(index);
		repaint();
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	public void dragExit(DropTargetEvent dte) {
	}

	public void drop(DropTargetDropEvent dtde) {
		if (dtde.getSource() != dropTarget) {
			dtde.rejectDrop();
			return;
		}
		Point dropPoint = dtde.getLocation();
		int index = locationToIndex(dropPoint);
		boolean dropped = false;
		try {
			DefaultListModel mod = (DefaultListModel) getModel();
			Object dragged = dtde.getTransferable().getTransferData(
					ReportDataFlavor.localObjectFlavor);
			// �ж���ק�Ķ����Ƿ���JList���Լ��Ƿ��Ѻ��е�ǰ��ק���󣬲������������������
			if ((index == -1 && mod.size() > 0)
					|| (index == draggedIndex && index != -1)
					|| (draggedIndex == -1 && mod.contains(dragged))) {
				dtde.rejectDrop();
				return;
			}
			dtde.acceptDrop(DnDConstants.ACTION_MOVE);
			// ���Ǵӵ�ǰ�����ק�Ķ��������
			if (draggedIndex == -1) {
				mod.add(mod.getSize(), dragged);
			} else {
				// �ӵ�ǰ�����ק�Ķ��������˳�����
				boolean sourceBeforeTarget = (draggedIndex < index);
				mod.remove(draggedIndex);
				mod.add((sourceBeforeTarget ? index - 1 : index), dragged);
			}
			dropped = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		dtde.dropComplete(dropped);
	}

	public void add(Object obj) {
		DefaultListModel mod = (DefaultListModel) getModel();
		mod.addElement(obj);
	}

	public void dragEnter(DragSourceDragEvent dsde) {
	}

	public void dragOver(DragSourceDragEvent dsde) {
	}

	public void dropActionChanged(DragSourceDragEvent dsde) {
	}

	public void dragExit(DragSourceEvent dse) {
	}

	public void dragDropEnd(DragSourceDropEvent dsde) {
		dropTargetCell = null;
		draggedIndex = -1;
		repaint();
	}

}