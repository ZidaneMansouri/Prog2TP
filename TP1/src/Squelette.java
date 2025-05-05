import eko.*;

public class Squelette extends ObjetJeu {
    public Squelette(int x, int y) {
        super("Squelette", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        ObjetJeu joueur = GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
        if (joueur != null) {
            if (joueur.getX() > getX()) position.x++;
            else if (joueur.getX() < getX()) position.x--;
            if (joueur.getY() > getY()) position.y++;
            else if (joueur.getY() < getY()) position.y--;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEE15", EKOCouleur.BLANC);
    }
}
