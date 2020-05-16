package test;

import java.util.Arrays;

public class MyHeapSort {
	public static void buildheap(int[] arr) {
		/*
		 * As last non leaf node will be at (arr.length-1)/2 so we will start
		 * from this location for heapifying the elements
		 */
		for (int i = (arr.length - 1) / 2; i >= 0; i--) {
			heapify(arr, i, arr.length - 1);
		}
	}

	public static void heapify(int[] arr, int i, int size) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max;
		if (left <= size && arr[left] > arr[i]) {
			max = left;
		} else {
			max = i;
		}

		if (right <= size && arr[right] > arr[max]) {
			max = right;
		}
		// If max is not current node, exchange it with max of left and right
		// child
		if (max != i) {
			swap(arr, i, max);
			heapify(arr, max, size);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static int[] heapSort(int[] arr) {

		buildheap(arr);
		int sizeOfHeap = arr.length - 1;
		for (int i = sizeOfHeap; i > 0; i--) {
			swap(arr, 0, i);
			sizeOfHeap = sizeOfHeap - 1;
			heapify(arr, 0, sizeOfHeap);
		}
		return arr;
	}

	public static void main(String[] args) {
//		int[] data = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] data = { 1, 10, 16, 19, 3, 5 };

		printArray(data);
		heapSort(data);
		printArray(data);
	}

	public static void printArray(int[] data) {
		System.out.println(Arrays.toString(data).replaceAll("[\\[\\]]", "").replaceAll(",", " "));
	}
	
}
