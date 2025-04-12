import eko.EKOConsole;

public class Clef extends ObjetJeu implements Collisionnable {

    public Clef(int x, int y) {
        super("Clef", x, y, Etiquette.ITEM);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        // Ne bouge pas
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "C");
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            this.detruire();
            // Ici tu pourras ajouter : ouvrir une porte, jouer un son...
        }
    }
}
