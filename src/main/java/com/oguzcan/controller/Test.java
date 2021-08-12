package com.oguzcan.controller;

public class Test {

	InputController input = new InputController();
	public static void main(String[] args) {
		
		
//		Menu value = Menu.values()[input.nextInt()];
//		System.out.println(value.name());
//		System.out.println(value.ordinal());
//		System.out.println(value.getClass());
	
//		while (true) {  // public enum Menu {CREATE,UPDATE,BACK;}
//			try {
//				switch (Menu.values()[input.nextInt()-1]){
//					case CREATE:System.out.println("Oluştur"); break;		// 1
//					case UPDATE: System.out.println("Güncelle"); break;	// 2
//					case BACK: System.out.println("GERİ"); break;			// 3
//				}
//			} catch (ArrayIndexOutOfBoundsException ex) {
//				System.out.println("OLMADI");
//			}
//		}
		
		Test test = new Test();
		test.interestingTest();
	}
	public void test5 () {
		while (true) { // public enum Menu {CREATE,UPDATE,BACK,MISMATCH;}
			switch (getEnum(input.nextInt())) {
				case CREATE:System.out.println("Oluştur"); break;		// 1
				case UPDATE: System.out.println("Güncelle"); break;	// 2
				case BACK: System.out.println("GERİ"); break;			// 3
				case MISMATCH: System.out.println("Hata"); break;
			}
		}
	}
	public Menu getEnum(int index) {
		Menu[] enumArray = Menu.values();
		if(enumArray.length < index) {
			return Menu.MISMATCH;
		}
		return enumArray[index-1];
	}
	
	public void interestingTest() {
		String x = "3.5";
		
		double y;
		
		System.out.println(Double.parseDouble(x));
	}
}
