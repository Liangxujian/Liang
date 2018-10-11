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
 * 集合一些操作汇总
 */
public class Test07Jihe {
	public void transformArrayAndList(){
		// 数组转List、Set
		// 基础类型数组不能使用asList方法，只能循环添加
		Integer[] nums = {1,2,3,4,5};
		List<Integer> numsl = Arrays.asList(nums);
		Set<Integer> numss = new HashSet<>(Arrays.asList(nums));
		
		// List和Set互转，Map的keySet和value同理
		numsl = new ArrayList<Integer>(numss);
		numss = new HashSet<Integer>(numsl);
		
		// List、Set转数组
		Integer[] numsa = new Integer[numsl.size()];
		numsl.toArray(numsa);
		numss.toArray(numsa);
	}
	
	/**
	 * 遍历Map集合汇总
	 */
	public void iterateMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		// 遍历Map的key
		for (String str : map.keySet()) {
			System.out.println(str);
		}
		// 遍历Map的value
		for(Object o : map.values()){
			System.out.println(o.toString());
		}
		// Map集合元素遍历
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	/**
	 * 取两个集合的交集，即保留相同的元素
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
				// sourceList减去substractionList
				addresultList.add(str);
			}
		}
		return addresultList;
	}
	
	/**
	 * 取两个集合的差集，即合并并去除相同元素
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
				// sourceList减去substractionList
				addresultList.add(str);
			}
		}
		return addresultList;
	}
	
	/**
	 * 取两个集合的并集，即合并并元素去重
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
		nums.add("炁體源流");
		nums.add("拘靈遣將");
		nums.add("通天箓");
		nums.add("風後奇門");
		nums.add("神機百煉");
		nums.add("六庫竊賊");
		nums.add("大羅洞觀");
		System.out.println(nums);
		nums.add(1, "雙全手");
		System.out.println(nums);
	}
	
	public Map<String, String> getMap(Map<String, String> map){
		map.put("不知道", "香蕉");
		return map;
	}
	
	public static void main(String[] args) {
		Test07Jihe test = new Test07Jihe();
		Map<String, String> map = new HashMap<>();
		map.put("一", "孙悟空");
		map.put("二", "贝吉塔");
		test.getMap(map);
		System.out.println(map);
	}
}