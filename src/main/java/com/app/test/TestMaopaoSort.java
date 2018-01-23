package com.app.test;

public class TestMaopaoSort {
	public static void main(String[] args) {
//		getSort();
		cfkj();
	}
	
	public static void getSort(){
		int arr[] = {5,4,12,2,1};  //4{4,5,2,1,12} 3{4,2,1,5,12} 2{2,1,4,5,12} 1{1,2,4,5,12}
		int temp=0;
		for (int i = 1; i < arr.length; i++) {
			System.err.println("第一次循环"+0+i);
			for (int j =0; j < arr.length-i; j++) {
				System.out.println("第二次循环"+0+j+"长度"+(arr.length-i));
				if(arr[j]>arr[j+1]){
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		 for(int i=0;i<arr.length;i++)  
		 System.out.println(arr[i]);     
	} 
	
	public static void cfkj(){
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j<=i; j++) {
				System.err.println(i+"*"+j+"="+i*j+"\t");
			}
			System.out.println("");
		}
	}

}
