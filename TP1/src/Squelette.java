import eko.EKOConsole;
import eko.EKOCouleur;

public class Squelette extends ObjetJeu implements Collisionnable {
    public Squelette(int x, int y) {
        super("Squelette", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        Personnage p = (Personnage) GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
        if (p != null) {
            if (p.getX() > getX()) position.x++;
            else if (p.getX() < getX()) position.x--;
            if (p.getY() > getY()) position.y++;
            else if (p.getY() < getY()) position.y--;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF49C", EKOCouleur.GRIS_PALE);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(30);
        }
    }
}
