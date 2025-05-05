import eko.EKOConsole;
import eko.EKOCouleur;

public class Feu extends ObjetJeu implements Collisionnable {
    public Feu(int x, int y) {
        super("Feu", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {}

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF46A", EKOCouleur.ROUGE);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(20);
        }
    }
}
