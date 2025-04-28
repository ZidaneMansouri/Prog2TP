import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteCondamnee extends ObjetJeu {

    public PorteCondamnee(int x, int y) {
        super("PorteCondamnee", x, y, Etiquette.MUR);
    }
//Ne fait rien.
    @Override
    protected void mettreAJour(long deltaTemps) {

    }
//Affiche le mur ou la porte condamnée (grise ou rouge).
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\u2503", EKOCouleur.ROUGE);
    }
}
