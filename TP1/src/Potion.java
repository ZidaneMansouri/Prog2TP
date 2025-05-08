import eko.*;
public class Potion extends ObjetJeu implements Collisionnable{
    protected Potion( int x, int y) {
        super("Potion", x, y, Etiquette.ITEM);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {

    }

    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    @Override
    protected void dessiner() {

    }
}
