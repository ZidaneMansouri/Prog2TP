import eko.*;

public class Potion extends ObjetJeu implements Collisionnable {

    // Constructeur : crée une potion à une position donnée
    protected Potion(int x, int y) {
        super("Potion", x, y, Etiquette.ITEM);
    }

    // Cette méthode est appelée quand un autre objet entre en collision avec la potion
    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage p) {
            p.RegenererVie();
            this.detruire();
// Joue un son pour indiquer que la potion a été capturée
            EKOSon PotionSon = EKOAudio.charger("audio/PotionSon.wav");
            EKOAudio.jouer(PotionSon);
        }
    }

    // La potion ne fait rien à chaque mise à jour
    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    // Affiche la potion à l'écran avec une icône et une couleur rouge
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF0C3", EKOCouleur.ROUGE);
    }
}
