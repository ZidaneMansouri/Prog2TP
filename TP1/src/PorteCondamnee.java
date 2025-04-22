import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteCondamnee extends ObjetJeu {

    public PorteCondamnee(int x, int y) {
        super("PorteCondamnee", x, y, Etiquette.MUR);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\u2503", EKOCouleur.ROUGE);
    }
}
