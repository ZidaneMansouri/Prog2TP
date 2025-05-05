import eko.EKOConsole;
import eko.EKOCouleur;

public class Insecte extends ObjetJeu implements Collisionnable {

    private int directionX = 1;
    private int directionY = 0;

    public Insecte(int x, int y) {
        super("Insecte", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        int prochainX = getX() + directionX;
        int prochainY = getY() + directionY;

        if (estMurOuPorte(prochainX, prochainY)) {
            tournerADroite(); // change de direction si obstacle
        } else {
            position.x = prochainX;
            position.y = prochainY;
        }
    }

    private void tournerADroite() {
        int temp = directionX;
        directionX = -directionY;
        directionY = temp;
    }

    private boolean estMurOuPorte(int x, int y) {
        for (ObjetJeu obj : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (obj.getX() == x && obj.getY() == y) return true;
        }
        for (ObjetJeu obj : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.SORTIE)) {
            if (obj instanceof PorteVerrouillee && obj.getX() == x && obj.getY() == y) return true;
        }
        return false;
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF188", EKOCouleur.VERT);  // 🐞 symbole insecte
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(10);
        }
    }
}
