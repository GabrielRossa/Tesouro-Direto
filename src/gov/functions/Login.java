package gov.functions;

import java.sql.*;

public class Login {

	public static String[] getLoginData(){

		String url = "jdbc:sqlite:D:\\Dev\\Projects\\Eclipse\\Java\\Treasure\\src\\db\\treasure.db";
		String[] correct = new String[2];
		
		try{
			// Estabelece conexão com banco
			Connection con = DriverManager.getConnection(url);
			// Criando seletor para tabela de títulos e colocando os dados em um resultSet
			String userSelect = "select * from UserData";
			Statement stmtSelect = con.createStatement();
			ResultSet userData = stmtSelect.executeQuery(userSelect);


			while(userData.next()){
				correct[0] = userData.getString(2);
				correct[1] = userData.getString(3);
			}

		}catch(SQLException e){
			System.out.println("------------> ERRO: Falha na conexão!");
			System.out.println(e.getMessage());
		}
		return correct;		
	}
	
	public static boolean loginCheck(String c[], String nick, String pass){
		
		boolean log = false;
		
		if (c[0].equals(nick) && c[1].equals(pass)){
			log = true;
		}else {
			System.out.println("\nUsuário ou senha incorretos!\n\nDigite novamente!");
		}
		
		return log;
	}
	
}

