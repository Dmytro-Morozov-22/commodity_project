package ua.lviv.lgs.commodity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void menu() {
		System.out.println("1 - додати товар");
		System.out.println("2 - видалити товар");
		System.out.println("3 - замінити товар");
		System.out.println("4 - сортувати за назвою");
		System.out.println("5 - сортувати за довжиною");
		System.out.println("6 - сортувати за шириною");
		System.out.println("7 - сортувати за вагою");
		System.out.println("8 - вивести певний елемент");
		System.out.println("9 - вийти з програми");
	}

	static ArrayList<Commodity> store = new ArrayList<>();

	public static void main(String[] args) {

		while (true) {
			menu();
			Scanner sc = new Scanner(System.in);
			switch (sc.next()) {

//додати товар
			case "1":
				addItem();
				getInfo();
				break;

//видалити товар
			case "2":
				removeItem();
				getInfo();
				break;

//замінити товар
			case "3":
				replaceItem();
				break;

//сортувати за назвою
			case "4":
				sortByName();
				getInfo();
				break;

//сортувати за довжиною
			case "5":
				sortByLength();
				getInfo();
				break;

//сортувати за шириною
			case "6":
				sortByWidth();
				getInfo();
				break;

//сортувати за вагою
			case "7":
				sortByWeight();
				getInfo();
				break;

//вивести певний елемент
			case "8":
				getByIndex();
				break;

//вийти з програми
			case "9":
				sc.close();
				exitTheProgram();
				break;

			default:
				System.err.println("Введіть числа від 1 до 9");
//				sc.close();
				break;
			}//switch

		} //while

	}//main

//МЕТОДИ:
//Отримати інформацію	
	static void getInfo() {
		for (Commodity c : store)
			System.out.println(c);
	}

//Додати товар	
	static void addItem() {
		store.add(new Commodity(getRandString(), getRandRange(30, 120),
				getRandRange(30, 120), getRandRange(30, 120)));
	}

//Видалити товар
	static void removeItem() {
		Iterator<Commodity> iterator = store.iterator();
		if (iterator.hasNext()) {
			store.remove(0);
			System.out.println("Товар під індексом 0 видалено");
		} else {
			System.err.println("Додайте спочатку товар");
		}
	}

//Замінити товар	
	static void replaceItem() {
		Iterator<Commodity> iterator = store.iterator();
		if (iterator.hasNext()) {
			removeItem();
			addItem();
			getInfo();
		} else {
			System.err.println("Додайте спочатку товар");
		}
	}
		
//Сортувати за назвою
	static void sortByName() {
		Iterator<Commodity> iterator = store.iterator();
		if (iterator.hasNext()) {
			Collections.sort(store, new NameSortComparator());
		} else {
			System.err.println("Додайте спочатку товар");
		}	
	}

//Сортувати за довжиною
	static void sortByLength() {
		Iterator<Commodity> iterator = store.iterator();
		if (iterator.hasNext()) {
			Collections.sort(store, new LengthSortComparator());
		} else {
			System.err.println("Додайте спочатку товар");
		}		
	}

//Сортувати за шириною	
	static void sortByWidth() {
		Iterator<Commodity> iterator = store.iterator();
		if (iterator.hasNext()) {
			Collections.sort(store, new WidthSortComparator());
		} else {
			System.err.println("Додайте спочатку товар");
		}	
	}

//Сортувати за вагою
	static void sortByWeight() {
		Iterator<Commodity> iterator = store.iterator();
		if (iterator.hasNext()) {
			Collections.sort(store, new WeightSortComparator());
		} else {
			System.err.println("Додайте спочатку товар");
		}		
	}

//Виводимо і-й елемент колекції(який вводимо з консолі(використовуємо Scanner))	
	static void getByIndex() {
if(!store.isEmpty()) {
		System.out.println("Введіть індекс необхідного об'єкта від нуля");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);

		if (scn.hasNextInt()) {
			int obtainedValue = scn.nextInt();
			int count = 0;

			ListIterator<Commodity> li = store.listIterator();
			while (li.hasNext()) {
				Commodity next = li.next();

				if (li.nextIndex() - 1 == obtainedValue) {
					System.out.println(next);
				} else {
					count++;
				}

				if (count == store.size()) {
					System.err.println("Такого індексу немає у списку");
				}

			}
		} else {
			System.err.println("Введіть числовий індекс");
		}
}else {
	System.err.println("Додайте спочатку товар");
}
	}

//Вийти з програми
	static void exitTheProgram() {
		System.out.println("Програму завершено!");
		System.exit(0);
	}

//RANDOM METHODS:	
	// Random string
	private static String getRandString() {
		String[] itemList = { "Валіза", "Стіл", "Шафа", "Телевізор", "Крісло",
				"Диван", "Дзеркало", "Килим", "Велосипед", "Полиця", "Праска" };
		return itemList[getRandRange(0, itemList.length - 1)];
	}//RandString

	// Random range
	private static int getRandRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException(
					"min value must be smaller than max value");
		}

		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}//RandRange

}//Main