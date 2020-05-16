package pankaj.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class HeapSortImpl {

	public static void main(String[] args) {
		IntStream intStream = new Random().ints(1, 1000);
		int[] data = intStream.limit(10).toArray();
		printArray(data);
		heapSort(data);
		printArray(data);
	}

	public static void printArray(int[] data) {
//		System.out.println(Arrays.toString(data).replaceAll("[\\[\\]]", "").replaceAll(", ", " "));
		System.out.println(Arrays.toString(data));
	}

	public static void heapSort(int[] data) {
		int size = data.length - 1;
		
		for (int i = size / 2; i >= 0; i--) {
			heapify(data, i, size);
		}
		
		for (int i = size; i > 0; i--) {
			swap(data, 0, i);
			size = size - 1;
			heapify(data, 0, size);
		}
	}

	private static void heapify(int[] data, int root, int size) {
		int left = root * 2 + 1;
		int right = root * 2 + 2;
		int max = root;
		if (left <= size && data[root] < data[left]) {
			max = left;
		}
		if (right <= size && data[max] < data[right]) {
			max = right;
		}
		if (max != root) {
			swap(data, root, max);
			heapify(data, max, size);
		}
	}

	private static void swap(int[] data, int root, int max) {
		int temp = data[root];
		data[root] = data[max];
		data[max] = temp;
	}
}
