import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteCondamnee extends ObjetJeu {
    // Constructeur : crée une porte condamnée à la position (x, y)
    public PorteCondamnee(int x, int y) {
        super("PorteCondamnee", x, y, Etiquette.MUR);
    }

    // Cette porte ne fait rien à chaque mise à jour (elle ne bouge pas, ne change pas)
    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    // Dessine la porte condamnée à l'écran (symbole rouge vertical)
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\u2503", EKOCouleur.ROUGE);
    }
}
