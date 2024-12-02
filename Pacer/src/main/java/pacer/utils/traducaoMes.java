package pacer.utils;

import java.time.Month;

public class traducaoMes {
    public static String traduzirMes(Month month) {
        String monthTraduzido = null;
        
        switch (String.valueOf(month).toLowerCase()) {
            case "january":
                monthTraduzido = "Janeiro";
                break;

            case "february":
                monthTraduzido = "Fevereiro";
                break;

            case "march":
                monthTraduzido = "Março";
                break;

            case "april":
                monthTraduzido = "Abril";
                break;

            case "may":
                monthTraduzido = "Maio";
                break;

            case "june":
                monthTraduzido = "Junho";
                break;

            case "july":
                monthTraduzido = "Julho";
                break;

            case "august":
                monthTraduzido = "Agosto";
                break;

            case "september":
                monthTraduzido = "Setembro";
                break;

            case "october":
                monthTraduzido = "Outubro";
                break;

            case "november":
                monthTraduzido = "Novembro";
                break;

            case "december":
                monthTraduzido = "Dezembro";
                break;

        }
        return monthTraduzido;
    }
}
