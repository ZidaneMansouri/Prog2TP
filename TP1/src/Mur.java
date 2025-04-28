import eko.EKOConsole;
import eko.EKOCouleur;

public class Mur extends ObjetJeu {

    public Mur(int x, int y) {
        super("Mur", x, y, Etiquette.MUR);
    }
   //Ne fait rien.
    @Override
    protected void mettreAJour(long deltaTemps) {

    }
//Affiche le mur ou la porte condamnée (grise ou rouge).
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\u2588", EKOCouleur.GRIS_PALE);
    }
}
