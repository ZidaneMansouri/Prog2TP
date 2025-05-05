import eko.EKOConsole;
import eko.EKOCouleur;

public class Fantome extends ObjetJeu implements Collisionnable {
    private boolean vaDroite = true;

    public Fantome(int x, int y) {
        super("Fantome", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        if (vaDroite) {
            position.x++;
            if (position.x > 55) vaDroite = false;
        } else {
            position.x--;
            if (position.x < 5) vaDroite = true;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF47B", EKOCouleur.BLANC);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(10);
        }
    }
}
