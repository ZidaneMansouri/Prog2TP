import eko.EKOConsole;
import eko.EKOCouleur;
import java.util.Random;

public class Feu extends ObjetJeu {
    private boolean versGauche = true;
    private long tempsDepuisDernierChangement = 0;
    private long delaiChangement = 500 + new Random().nextInt(1000); // entre 500ms et 1500ms

    public Feu(int x, int y) {
        super("Feu", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        tempsDepuisDernierChangement += deltaTemps;
        if (tempsDepuisDernierChangement > delaiChangement) {
            versGauche = !versGauche;
            tempsDepuisDernierChangement = 0;
        }
    }

    @Override
    protected void dessiner() {
        String symbole = versGauche ? "<" : ">";
        EKOConsole.afficher(getX(), getY(), symbole, EKOCouleur.ROUGE);
    }
}
