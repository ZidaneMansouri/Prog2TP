import eko.EKOConsole;
import eko.EKOCouleur;

public class Mur extends ObjetJeu {
    // Constructeur : crée un mur à la position (x, y)
    public Mur(int x, int y) {
        super("Mur", x, y, Etiquette.MUR);
    }

    // Le mur ne fait rien chaque trame (il ne bouge pas, ne change pas)
    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    // Affiche le mur à l’écran avec une couleur grise
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\u2588", EKOCouleur.RVB(107, 107, 108));
    }
}
