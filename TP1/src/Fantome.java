import eko.*;

public class Fantome extends ObjetJeu {
    private int dx;
    private int dy;
    // Constructeur : crée un fantôme horizontal ou vertical
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
    // Met à jour la position du fantôme à chaque trame
    @Override
    protected void mettreAJour(long deltaTemps) {
        position.x += dx;
        position.y += dy;
        // Si le fantôme atteint le bord de l'écran, il change de direction
        if (position.x <= 0 || position.x >= EKOConsole.largeur() - 1) dx *= -1;
        if (position.y <= 0 || position.y >= EKOConsole.hauteur() - 1) dy *= -1;
    }
    // Affiche le fantôme à l'écran avec un symbole blanc
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEEFE", EKOCouleur.BLANC);
    }
}
