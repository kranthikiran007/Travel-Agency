package com.kk.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new  ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(4);
		HashSet<Integer> set = new HashSet<Integer>();
		
		
		for (Integer integer : list) {
			if(!set.add(integer))
			{
				System.out.println(integer);
			}
		}
//		System.out.println(set);
	
	}

}
