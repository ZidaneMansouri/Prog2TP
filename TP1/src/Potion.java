import eko.*;
public class Potion extends ObjetJeu implements Collisionnable{
    protected Potion( int x, int y) {
        super("Potion", x, y, Etiquette.ITEM);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
    if(autre instanceof Personnage p) {
        p.RegenererVie();
        this.detruire();
    }
    }

    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    @Override
    protected void dessiner() {
    EKOConsole.afficher(getX(), getY(), "\uF0C3", EKOCouleur.ROUGE);
    }
}
