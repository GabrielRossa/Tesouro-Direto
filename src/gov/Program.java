package gov;

import gov.model.*;
import gov.functions.*;
import java.util.*;


public class Program {
	static Scanner in = new Scanner(System.in);

	public static Title add() {
		Title newTitle = new Title();
		
		System.out.println(" --------------------------- ADICIONAR TÍTULO --------------------------- ");
		System.out.println("" );
		System.out.print("Insira o nome do título: ");
		newTitle.name = in.nextLine();
		System.out.print("Insira a rentabilidade anual do título(%):  ");
		newTitle.rent = in.nextDouble();
		in.nextLine();
		System.out.print("Insira a data de encerramento do título (dd/mm/yyyy): ");
		newTitle.endDate = in.nextLine();
		System.out.print("Insira o valor de investimento mínimo do título: ");
		newTitle.unit = in.nextDouble();
		in.nextLine();
		System.out.print("Insira o valor unitário do título: ");
		newTitle.unit = in.nextDouble();
		in.nextLine();
		
		System.out.println("\n ------------------------------ FINALIZADO ------------------------------ ");
		
		System.out.println("Pressione enter para voltar ao menu!");
		in.nextLine();
		
		return newTitle;
	}
	
	public static int delete() {
		int select = 0;

		System.out.println(" ---------------------------- DELETAR TÍTULO ---------------------------- ");
		System.out.println("" );
		System.out.print("Selecione o número do título que deseja deletar: ");
		select = in.nextInt();
		in.nextLine();
		System.out.println("\n ------------------------------ FINALIZADO ------------------------------ ");
		return select;
	}
	
	public static double[] simulateData() {
		double[] infos = new double[3];
		
		System.out.println(" ---------------------------- SIMULAR TÍTULO ---------------------------- ");
		System.out.println("Selecione o título que deseja simular: ");
		infos[0] = in.nextDouble();
		in.nextLine();
		System.out.println("Informe o valor de aporte inicial: ");
		infos[1] = in.nextDouble();
		in.nextLine();
		System.out.println("Informe o valor de aplicação mensal: ");
		infos[2] = in.nextDouble();
		in.nextLine();
		
		return infos;
		}
	
	public static void main(String[] args) {
		
		int opt = 0;		
		boolean check = false;
		String[] c = Login.getLoginData();
		
		do {
			System.out.print("Informe seu login: ");
			String nick = in.nextLine();
			System.out.print("Informe sua senha: ");
			String pass = in.nextLine();
			
			check = Login.loginCheck(c, nick, pass);
			
		}while(check == false);
		
		//Criando a classe de armazenamento dos títulos, puxados do bd

		do{
			Title[] titulo = TitlesAccess.getTitles();
			Menu.menu1();
			opt = in.nextInt();
			in.nextLine();
			
			switch(opt) {
			case 0:
				break;				
			case 1:
				TitlesAccess.showTitles(titulo);
				in.nextLine();
				break;
			case 2:
				Title addTitle = add();
				TitlesAccess.insertTitle(addTitle);
				break;
			case 3:
				TitlesAccess.listTitlesBd(titulo);
				int select = delete();
				if(select != 0) {
					TitlesAccess.deleteTitle(select);					
				}
				break;
			case 4:
				TitlesAccess.listTitles(titulo);
				double[] simInfo = simulateData();
				double cvrt = (simInfo[0]-1);
				String convert =  String.valueOf(cvrt);
				convert = convert.substring(0, 1);
				int titleSelect = Integer.parseInt(convert);
				Simulate.simulation(simInfo, titulo[titleSelect]);
				in.nextLine();
				break;
			default:
				System.out.println("-------> Opção inválida!");
			}
			
		}while(opt!=0);
		
		System.out.println("Programa encerrado!");
		
		in.close();
	}
}
