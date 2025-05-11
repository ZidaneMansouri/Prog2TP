import eko.EKOConsole;
import eko.EKOCouleur;

public class LangueGrenouille extends ObjetJeu implements Collisionnable {
    // La direction dans laquelle la langue va (1 = droite, -1 = gauche)
    private int direction;

    // Constructeur : crée la langue à la position (x, y) et lui donne une direction
    public LangueGrenouille(int x, int y, int direction) {
        super("Langue", x, y, Etiquette.ENNEMI);
        this.direction = direction;
    }

    // Cette méthode est appelée à chaque trame (frame) du jeu
    @Override
    protected void mettreAJour(long deltaTemps) {
        position.x += direction;
        // Si la langue sort de l’écran, on la supprime
        if (position.x < 0 || position.x >= EKOConsole.largeur()) {
            this.detruire();
        }
    }

    // Affiche la langue sur l’écran
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "-", EKOCouleur.ROUGE);
    }

    // Si la langue touche le joueur
    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(25);
            this.detruire();
        }
    }
}