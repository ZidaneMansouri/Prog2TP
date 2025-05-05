import eko.EKOConsole;
import eko.EKOCouleur;

public class Insecte extends ObjetJeu implements Collisionnable {
    public Insecte(int x, int y) {
        super("Insecte", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        position.x--;
        if (position.x < 0) position.x = EKOConsole.largeur() - 1;
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF47F", EKOCouleur.VERT);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(15);
        }
    }
}
