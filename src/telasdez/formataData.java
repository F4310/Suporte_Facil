package telasdez;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class formataData {

	public static String MostraData(){
	     
	       Date data = new Date();
	       SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	       
	       String sData = formatador.format(data);

	        return sData;
	    }
	    public static String MostraHora(){
	       
	       Date data = new Date();
	       SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");

	       String sHora = formatador.format(data);
	       return sHora;
	    }
	    
	    public String dataAtual(){
	    	DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	Calendar cal = Calendar.getInstance();
	    	String r = formata.format(cal.getTime());
			return r;
	    }

	}


