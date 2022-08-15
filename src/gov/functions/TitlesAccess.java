package gov.functions;

import java.sql.*;
import gov.model.*;

public class TitlesAccess {
	static String url = "jdbc:sqlite:D:\\Dev\\Projects\\Eclipse\\Java\\Treasure\\src\\db\\treasure.db";

	public static Title[] getTitles() {

		Title[] titulo = new Title[40];

		try {
			//Estabelece conexão com banco
			Connection con = DriverManager.getConnection(url);
			
			//Criando seletor para tabela de títulos e colocando os dados em um resultSet
			String titleSelect = "select * from Titles";
			Statement stmtSelect = con.createStatement();
			ResultSet titleData = stmtSelect.executeQuery(titleSelect);
			
			//Lendo os dados e colocando dentro da classe
			int i = 0;
			while(titleData.next()) {
				Title tituloTemp = new Title();
				
				tituloTemp.id = titleData.getInt(1);
				tituloTemp.name = titleData.getString(6);
				tituloTemp.rent = titleData.getDouble(2);
				tituloTemp.endDate = titleData.getString(3);
				tituloTemp.invMin = titleData.getDouble(4);
				tituloTemp.unit = titleData.getDouble(5);
				
				titulo[i] = tituloTemp;
						
				i++;
			}
			
					
		}catch (SQLException e) {
			System.out.println("------------> ERRO: Falha na conexão!");
			System.out.println(e.getMessage());
		}
		
		return titulo;
		
	}
	
	public static void showTitles(Title titulo[]) {
		int i = 0;
		while(i<titulo.length) {
			if(titulo[i] == null) {
				i++;
			}else {
				System.out.println(" ------------------------------------------------------------------------");
				System.out.println("| " + titulo[i].name);
				System.out.println("|\n| Rentabilidade anual: " + titulo[i].rent + "%");
				System.out.println("| Data de encerramento do título: " + titulo[i].endDate);
				System.out.println("| Investimento mínimo: " + titulo[i].invMin);
				System.out.println("| Valor unitário: " + titulo[i].unit);
				
				i++;
			}
		}
		System.out.println(" ------------------------------------------------------------------------");
		System.out.println("Pressione enter para sair.");
	}
	
	public static void insertTitle(Title insert) {
		try {
			//Estabelece conexão com banco
			Connection con = DriverManager.getConnection(url);
			
			//Criando seletor para tabela de títulos e colocando os dados em um resultSet
			String titleInsert = "INSERT INTO Titles (name, rent, endDate, invMin, unit) values ('"+insert.name+"', '"+insert.rent+"', '"+insert.endDate+"', '"+insert.invMin+"', '"+insert.unit+"')";
			Statement stmtInsert = con.createStatement();
			stmtInsert.execute(titleInsert);
					
		}catch (SQLException e) {
			System.out.println("------------> ERRO: Falha na conexão!");
			System.out.println(e.getMessage());
		}
	}
	
	public static void listTitles(Title titulo[]) {
		int i = 0;
		System.out.println(" ------------------------------------------------------------------------");
		while(i<titulo.length) {
			if(titulo[i] == null) {
				i++;
			}else {
				System.out.println("| " + (i+1) + ". " + titulo[i].name);
				i++;
			}
		}
		System.out.println(" ------------------------------------------------------------------------");
	}
	
	public static void listTitlesBd(Title titulo[]) {
		int i = 0;
		System.out.println(" ------------------------------------------------------------------------");
		while(i<titulo.length) {
			if(titulo[i] == null) {
				i++;
			}else {
				System.out.println("| " + titulo[i].id + ". " + titulo[i].name);
				i++;
			}
		}
		System.out.println(" ------------------------------------------------------------------------");
	}

	public static void deleteTitle(int select) {		
		String selectS = Integer.toString(select);
		
		try {
			//Estabelece conexão com banco
			Connection con = DriverManager.getConnection(url);
			
			//Criando seletor para tabela de títulos e colocando os dados em um resultSet
			String titleDelete = "delete from Titles where id = "+selectS;
			Statement stmtDelete = con.createStatement();
			stmtDelete.execute(titleDelete);
			
		}catch (SQLException e) {
			System.out.println("------------> ERRO: Falha na conexão!");
			System.out.println(e.getMessage());
		}		
		
	}
}
