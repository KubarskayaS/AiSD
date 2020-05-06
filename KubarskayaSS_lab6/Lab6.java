import java.util.Scanner;

 public class Lab6{	
	public static void firstQueuePrint(){
		System.out.println();
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\t1 - добавление студента в очередь на повторную сдачу ");
		System.out.println("\t2 - отправление студента для повторной сдачи ");
		System.out.println("\t3 - очередь печати");
		System.out.println("\t4 - добавить билет на экзамен вверх ");
		System.out.println("\t5 - добавить билет на экзамен вниз ");
		System.out.println("\t6 - добавить задачу ");
		System.out.println("\t9 - закрытие программы ");
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
	public static void examTicketsPrint(){
		System.out.println();
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\t1 - выбрать верхний билет");
		System.out.println("\t2 - выбрать нижний билет");
		System.out.println("\t9 - закрытие программы ");
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
	public static void examTaskPrint(){
		System.out.println();
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\t1 - выбрать задачу ");
		System.out.println("\t9 - закрытие программы ");
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
	public static void examPrint(){
		System.out.println();
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\t1 - студент сдал экзамен ");
		System.out.println("\t2 - отправить на пересдачу ");
		System.out.println("\t9 - закрытие программы ");
		System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
	public static void main(String[] args){
		Queue queue = new Queue();
		Stack stack = new Stack();
		Deck  deck  = new Deck();

		String tempStack = "";
		String tempDeck = "";
		String tempQueue = "";

		Scanner in = new Scanner(System.in);
		char controlButton;  

		while(true){
			firstQueuePrint();
			System.out.print(":>> ");
			controlButton = in.next().charAt(0);


			switch (controlButton) {
           	case  ('1'):
           		System.out.print("Введите имя студента: ");
               	queue.insert(in.next());
				          
				System.out.println("\n\tСтудент успешно добавлен");
               	break;
           	case ('2'):
           		tempQueue = queue.remove();
           		Boolean run = true;
           		while(run){
					examTicketsPrint();
					System.out.print(":>> ");
					controlButton = in.next().charAt(0);
					switch (controlButton){
					case  ('1'):
						tempDeck = deck.removeRight();
						System.out.println("\nвыбранный билет: " + tempDeck);
						System.out.println("\n\tбилет успешно выбран");
						run = false;          
               			break;
           			case ('2'):	
           				tempDeck = deck.removeLeft();    
						System.out.println("\nвыбранный билет: " + tempDeck);
						System.out.println("\n\tбилет успешно выбран");
           				run = false;      
		           		break;
		           	case ('9'):
		           	    System.out.println("\tЗакрытие программы\n");
		           		System.exit(0);
		              	break;
		           	default:
		               	System.out.println("\tошибка, введены неизвестные данные");
		               break;
					}
				}
				run = true;
				while(run){
					examTaskPrint();
					System.out.print(":>> ");
					controlButton = in.next().charAt(0);
					switch (controlButton){
					case  ('1'):
						tempStack = stack.pop();
						System.out.println("\nвыбранная задача: " + tempStack);
						System.out.println("\n\tзадача успешно выбрана");
						run = false;
               			break;
		           	case ('9'):
		           		System.out.println("\tЗакрытие программы\n");
		           		System.exit(0);
		              	break;
		           	default:
		               	System.out.println("\tошибка, введены неизвестные данные");
		               break;
					}
				}
				run = true;
				while(run){
					examPrint();
					System.out.print(":>> ");
					controlButton = in.next().charAt(0);
					switch (controlButton){
					case  ('1'):
						System.out.println("поздравляем, экзамен сдан");
						run = false;
               			break;
					case  ('2'):
						System.out.println("встретимся на пересдаче)");
						queue.insert(tempQueue);
						run = false;
               			break;
		           	case ('9'):
		           	    System.out.println("\tЗакрытие программы\n");
		           		System.exit(0);
		              	break;
		           	default:
		               	System.out.println("\tошибка, введены неизвестные данные");
		               break;
					}
					stack.push(tempStack);
					deck.insertLeft(tempDeck);
				}
               break;
           	case ('3'):
				System.out.print("first: "); queue.printFirst();
				System.out.print("\nlast: ");  queue.printLast();
               	break;
           	case ('4')://выбор
           		System.out.print("Введите билет ");
               	deck.insertRight(in.next());
				          
				System.out.print("верхний билет: "); deck.printRight();
				System.out.print("\nнижний билет: ");  deck.printLeft();
				System.out.println("\n\tбилет успешно добавлен");
           		break;
           	case ('5'):// вставка билета
           		System.out.print("Введите билет: ");
               	deck.insertLeft(in.next());
				          
				System.out.print("\nсверху: "); deck.printRight();
				System.out.print("\nснизу: ");  deck.printLeft();
				System.out.println("\n\tбилет успешно добавлен");
           		break;
           	case ('6'):// пузырь
				System.out.print("Введите задачу: ");
               	stack.push(in.next());
				          
				System.out.print("\n\tверхняя задача: ");  stack.print();
				System.out.println("\n\tзадача успешно добавлена");
           		break;
           	case ('9'):
           	    System.out.println("\tЗакрытие программы\n");
           		System.exit(0);
              	break;
           	default:
               	System.out.println("\tошибка, введены неизвестные данные");
               break;
       		}
		}
	}
}