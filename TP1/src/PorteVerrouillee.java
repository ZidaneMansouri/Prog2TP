import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteVerrouillee extends ObjetJeu {

    public PorteVerrouillee(int x, int y) {
        super("PorteVerrouillee", x, y, Etiquette.SORTIE);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\u2503", EKOCouleur.ROUGE);
    }
}
