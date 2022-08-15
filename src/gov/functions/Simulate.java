package gov.functions;

import gov.model.Title;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Simulate {
	public static void simulation(double infos[], Title titulo) {
		String endDate = titulo.endDate;
		String[] separateDate = endDate.split("/");
		Calendar cal = GregorianCalendar.getInstance();
		int year = cal.get(Calendar.YEAR), month = cal.get(Calendar.MONTH);
		double amount, mensalT, exp = 0.0833333333333, liqTotal;
		DecimalFormat df = new DecimalFormat("#,##0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		
		int totalDif;
		int difY = Integer.parseInt(separateDate[2]) - year;
		int difM = Integer.parseInt(separateDate[1]);
		
		difM = difM - month;
		
		difY*=12;
		totalDif = difM + difY -2;
		mensalT = (1 + titulo.rent/100);
		
		mensalT = Math.pow(mensalT, exp);
		mensalT = (mensalT - 1);
		
		
		amount = infos[1] + (infos[1]*mensalT);
		
		for(int i = 0; i<(totalDif); i++) {
			amount+=100;
			amount+= mensalT*amount;
		}
		
		liqTotal = amount - (amount*0.02);
		
		System.out.println("Aplicando: R$" + infos[1] +" inicialmente");
		System.out.println("Fazendo aportes de: R$" + infos[2] +" mensais");
		System.out.println("Até o ano de: " + year);
		System.out.println("O valor bruto final será de: R$" + df.format(amount));
		System.out.println("Você pagará: R$" + df.format(amount*0.02));
		System.out.println("Considerando o Imposto de Renda, seu valor de resgate será de: R$" + df.format(liqTotal));
		
		System.out.print("\nPressione enter para voltar ao menu!");
		
		
	}
}