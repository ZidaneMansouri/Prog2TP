import eko.*;

public class Fantome extends ObjetJeu {
    private int dx;
    private int dy;

    public Fantome(int x, int y, boolean horizontal) {
        super("Fantome", x, y, Etiquette.ENNEMI);
        if (horizontal) {
            dx = 1;
            dy = 0;
        } else {
            dx = 0;
            dy = 1;
        }
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        position.x += dx;
        position.y += dy;

        if (position.x <= 0 || position.x >= EKOConsole.largeur() - 1) dx *= -1;
        if (position.y <= 0 || position.y >= EKOConsole.hauteur() - 1) dy *= -1;
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEEFE", EKOCouleur.BLANC);
    }
}
