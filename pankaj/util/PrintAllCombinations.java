package pankaj.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintAllCombinations {

	public static void main(String[] args){
		String sourceString = "ABC";
		Object[] arr = sourceString.chars().mapToObj(x->new Character((char)x)).toArray(Object[]::new);
//		arr = new Object[]{10.5, 20.1};
		
		Object[] combinations = getAllPermutationsCombinations(arr);
		Stream.of(combinations).forEachOrdered(System.out::println);
	}
	
	private static Object[] getAllPermutationsCombinations(Object[] arr) {
		Boolean[] flags = new Boolean[arr.length];
		IntStream.range(0,  flags.length).forEach(x->flags[x]=Boolean.FALSE);
		int length = arr.length; 
		List<Object>		ans = new LinkedList<Object>(); 
		List<Object>		curr = new LinkedList<Object>(); 
		traverse(arr, length, 0, flags, curr, ans);
		Set<Object> res = new TreeSet<Object>(ans);
		return res.toArray();
	}

	public static void traverse(Object[] arr, int length, int depth, Boolean[] used, List<Object> curr, List<Object> ans){
		  if(curr.size()>0){
			  Object obj = curr.stream().map(Object::toString).collect(Collectors.joining(" "));
			  ans.add(obj);
		  }
			    
		  for(int i = 0; i < length; i++){
			  if(!used[i]){
				  curr.add(arr[i]);
				  used[i] = Boolean.TRUE;
				  traverse(arr, length, depth+1, used, curr, ans);
				  curr.remove(curr.size()-1);
				  used[i] = Boolean.FALSE;
			  }
		  }
	}
}
