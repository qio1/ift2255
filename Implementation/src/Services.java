import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * @author Quiao Wang
 * @author Maxime Lechasseur
 */
public class Services {

    private String titre;
    private String code;
    private String numPro;
    private String debut;
    private String fin;
    private String heure;
    private int jour;
    private int capacite;
    private double prix;
    private String comment;

	private HashMap<String, Seance> seances = new HashMap<String, Seance>();
    public Services(String code, String titre, String numPro, String debut, String fin, String heure, int jour,int capacite, double prix, String comment) {
    	this.code = code; // 3 chiffres
    	this.titre = titre;
        this.numPro = numPro;
        this.debut = debut;
        this.fin = fin;
        this.heure = heure;
        this.jour = jour;
        this.capacite = capacite;
        this.prix = prix;
        this.comment = comment;
    }
    
    public HashMap<String, Seance> addSeance() {
    	
    	List<LocalDate> dates = Temps.dateSeance(this.debut,this.fin,this.jour);
    	
        for(int i = 0; i < dates.size(); i++) {
        	String codeSeance = genCodeSeance(i);
        	Seance s = new Seance(dates.get(i),this.numPro,codeSeance);
        	this.seances.put(codeSeance, s);
        }
        return seances;
    }
   
    
   
    

    public String getContenu(String champ) {
        String result = null;
        switch (champ) {
            case "titre":
                result = this.titre;
                break;
            case "code":
                result = this.code;
                break;
            case "numPro":
                result = this.numPro;
                break;
            case "debut":
                result = this.debut;
                break;
            case "fin":
                result = this.fin;
                break;
            case "heure":
                result = this.heure;
            case "jour":
            	int day = this.jour;
                result = DayOfWeek.of(day).getDisplayName(TextStyle.FULL, Locale.FRENCH);
                break;
            case "prix":
                double cout = this.prix;
                result = Double.toString(cout);
                break;
            case "capacite":
                int espace = this.capacite;
                result = Integer.toString(espace);
                break;
            case "comment":
                result = this.comment;
                break;
        }
        return result;
    }
      
    /*
     * Le titre et le code de Service en 3 chiffres sont dépendantes de l'un à l'autre.
     * La date debut et fin, et la recurrence semaine sont aussi dépendantes de l'un à l'autre.
     * On ne les modifie pas individuellement.
     * Si l'utilisateur veut changer l'un de champ mentionné en haut, il doit supprimer le service et
     * en créer un nouveau pour que le système calcule de nouveau le nombre, la date et le numero de Seance.
     * */
    public void setContenu(String champ, String value) {
        switch (champ) {
            case "heure":
                this.heure = value;
                break;
            case "prix":
                this.prix = Double.parseDouble(value);
                break;
            case "capacite":
                this.capacite = Integer.parseInt(value);
                break;
            case "comment":
                this.comment = value;
                break;
        }
    }
    
    
    public String genCodeSeance(int i) {
        String codeService = getContenu("code");
        System.out.println(codeService);
        String numSeance = String.format("%02d",i);
        String numP2 = getContenu("numPro");
        String codeSeance = codeService + numSeance + numP2.substring(numP2.length()- 2);
        return codeSeance;
    }
    
    public String printService() {
    	String keys = printKeys();
    	String s =" Titre du service: "+getContenu("titre")+
        "\n Date de début du service: "+getContenu("debut")+
        "\n Date de fin du service: "+getContenu("fin")+
        "\n Heure du service: "+getContenu("heure")+
        "\n Récurrence hebdomadaire des séances: "+getContenu("jour")+
        "\n Capacité maximale: "+getContenu("capacite")+
        "\n Numéro du professionnel: "+getContenu("numPro")+
        "\n Code des seances: "+ keys +
        "\n Frais du service: "+getContenu("prix")+
        "\n Commentaires: "+getContenu("comment")+"\n";
    	return s;
    }
   
    public String printKeys() {
    	String result="";
    	
    	for ( String key : this.seances.keySet() ) {
    	    result += "\n"+key;
    	}
		return result;
    	
    }
}
   
