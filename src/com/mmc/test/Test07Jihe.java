package com.mmc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

/**
 * ����һЩ��������
 */
public class Test07Jihe {
	public void transformArrayAndList(){
		// ����תList��Set
		// �����������鲻��ʹ��asList������ֻ��ѭ�����
		Integer[] nums = {1,2,3,4,5};
		List<Integer> numsl = Arrays.asList(nums);
		Set<Integer> numss = new HashSet<>(Arrays.asList(nums));
		
		// List��Set��ת��Map��keySet��valueͬ��
		numsl = new ArrayList<Integer>(numss);
		numss = new HashSet<Integer>(numsl);
		
		// List��Setת����
		Integer[] numsa = new Integer[numsl.size()];
		numsl.toArray(numsa);
		numss.toArray(numsa);
	}
	
	/**
	 * ����Map���ϻ���
	 */
	public void iterateMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		// ����Map��key
		for (String str : map.keySet()) {
			System.out.println(str);
		}
		// ����Map��value
		for(Object o : map.values()){
			System.out.println(o.toString());
		}
		// Map����Ԫ�ر���
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	/**
	 * ȡ�������ϵĽ�������������ͬ��Ԫ��
	 * @param aList
	 * @param bList
	 * @return
	 */
	public List<String> intersectionList(List<String> aList, List<String> bList) {
		List<String> addresultList = new ArrayList<String>();
		Map<String, String> sourceMap = new HashMap<String, String>();
		for (String str : aList) {
			sourceMap.put(str, "a");
		}
		for (String str : bList) {
			if (sourceMap.get(str) != null) {
				// sourceList��ȥsubstractionList
				addresultList.add(str);
			}
		}
		return addresultList;
	}
	
	/**
	 * ȡ�������ϵĲ�����ϲ���ȥ����ͬԪ��
	 * @param aList
	 * @param bList
	 * @return
	 */
	public List<String> differenceSetList(List<String> aList, List<String> bList) {
		List<String> addresultList = new ArrayList<String>();
		Map<String, String> sourceMap = new HashMap<String, String>();
		for (String str : bList) {
			sourceMap.put(str, "a");
		}
		for (String str : aList) {
			if (sourceMap.get(str) == null) {
				// sourceList��ȥsubstractionList
				addresultList.add(str);
			}
		}
		return addresultList;
	}
	
	/**
	 * ȡ�������ϵĲ��������ϲ���Ԫ��ȥ��
	 * @param aList
	 * @param bList
	 * @return
	 */
	public List<String> mergerList(List<String> aList, List<String> bList){
		Set<String> set = new HashSet<>(aList);
		for(String str : bList){
			set.add(str);
		}
		return new ArrayList<>(set);
	}
	
	public void vectorDemo() {
		Vector<String> nums = new Vector<>();
		nums.add("���wԴ��");
		nums.add("���`ǲ��");
		nums.add("ͨ�칂");
		nums.add("�L�����T");
		nums.add("��C�ٟ�");
		nums.add("����`�\");
		nums.add("���_���^");
		System.out.println(nums);
		nums.add(1, "�pȫ��");
		System.out.println(nums);
	}
	
	public Map<String, String> getMap(Map<String, String> map){
		map.put("��֪��", "�㽶");
		return map;
	}
	
	public static void main(String[] args) {
		Test07Jihe test = new Test07Jihe();
		Map<String, String> map = new HashMap<>();
		map.put("һ", "�����");
		map.put("��", "������");
		test.getMap(map);
		System.out.println(map);
	}
}