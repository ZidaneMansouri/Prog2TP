import eko.EKOConsole;
import eko.EKOCouleur;

public class Insecte extends ObjetJeu implements Collisionnable {
    // La direction actuelle de l’insecte : ici il commence en allant vers la droite
    private int directionX = 1;
    private int directionY = 0;

    // Constructeur : crée un insecte à une position donnée
    public Insecte(int x, int y) {
        super("Insecte", x, y, Etiquette.ENNEMI);
    }

    // Cette méthode est appelée à chaque trame pour faire avancer l’insecte
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

    // Vérifie s’il y a un mur ou une porte verrouillée à la position donnée
    private boolean estMurOuPorte(int x, int y) {
        for (ObjetJeu obj : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (obj.getX() == x && obj.getY() == y) return true;
        }
        for (ObjetJeu obj : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.SORTIE)) {
            if (obj instanceof PorteVerrouillee && obj.getX() == x && obj.getY() == y) return true;
        }
        return false;
    }

    // Affiche l’insecte à l’écran avec un symbole spécial
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF188", EKOCouleur.VERT);  // 🐞 symbole insecte
    }

    // Quand l’insecte entre en collision avec le joueur
    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            ((Personnage) autre).perdreVie(10);
        }
    }
}
