import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteVerrouillee extends ObjetJeu {

    private boolean estDeverrouillee = false;

    public PorteVerrouillee(int x, int y) {
        super("PorteVerrouillee", x, y, Etiquette.SORTIE);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
    }

    @Override
    protected void dessiner() {
        EKOCouleur couleur = estDeverrouillee ? EKOCouleur.VERT : EKOCouleur.ROUGE;
        EKOConsole.afficher(getX(), getY(), "\u2503", couleur);
    }

    public void deverrouiller() {
        estDeverrouillee = true;
    }
}
