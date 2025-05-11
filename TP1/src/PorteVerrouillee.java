import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteVerrouillee extends ObjetJeu {

    private boolean estDeverrouillee = false;

    public PorteVerrouillee(int x, int y) {
        super("PorteVerrouillee", x, y, Etiquette.SORTIE);
    }

    // Ne fait rien (porte ne bouge pas).
    @Override
    protected void mettreAJour(long deltaTemps) {
    }

    //	Affiche la porte en rouge ou vert selon si elle est déverrouillée.
    @Override
    protected void dessiner() {
        EKOCouleur couleur = estDeverrouillee ? EKOCouleur.VERT : EKOCouleur.ROUGE;
        EKOConsole.afficher(getX(), getY(), "\u2503", couleur);
    }

    //Change l’état de la porte pour qu’elle s’affiche en vert.
    public void deverrouiller() {
        estDeverrouillee = true;
    }
}
