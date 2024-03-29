/**
 * @author Han Zhang
 */

public class Numero {

    /**
     * Générer une chaine de numéro aléatoire de longuer spécifiee
     *
     * @param longueur la longuer du numéro
     * @return la chaine de numéro aléatoire de longuer spécifiée
     */
    public static String genererNum(int longueur) {
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < longueur; i++) {
            int random = (int) (Math.random() * 10);
            num.append(random);
        }
        return num.toString();
    }
}
