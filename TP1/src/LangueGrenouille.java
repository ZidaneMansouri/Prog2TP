import eko.EKOConsole;
import eko.EKOCouleur;

public class LangueGrenouille extends ObjetJeu implements Collisionnable {
    private int direction;

    public LangueGrenouille(int x, int y, int direction) {
        super("Langue", x, y, Etiquette.ENNEMI);
        this.direction = direction;
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        position.x += direction;
        if (position.x < 0 || position.x >= EKOConsole.largeur()) {
            this.detruire();
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "-", EKOCouleur.ROUGE);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(25);
            this.detruire();
        }
    }
}