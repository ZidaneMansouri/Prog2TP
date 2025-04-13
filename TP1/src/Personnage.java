import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;
import eko.EKOChaine;

public class Personnage extends ObjetJeu implements Collisionnable {

    private int vieMax = 100; // Vie maximale
    private int vie = 100;    // Vie actuelle

    public Personnage(int x, int y) {
        super("Personnage", x, y, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        int nouveauX = getX();
        int nouveauY = getY();

        if (EKOTouche.FLECHE_GAUCHE.estEnfoncee()) {
            nouveauX--;
        } else if (EKOTouche.FLECHE_DROITE.estEnfoncee()) {
            nouveauX++;
        } else if (EKOTouche.FLECHE_HAUT.estEnfoncee()) {
            nouveauY--;
        } else if (EKOTouche.FLECHE_BAS.estEnfoncee()) {
            nouveauY++;
        }

        // Avant de bouger : vérifier si la case est libre (pas un mur)
        if (peutAller(nouveauX, nouveauY)) {
            position.x = nouveauX;
            position.y = nouveauY;
        }
    }

    @Override
    protected void dessiner() {
        afficherJaugeVie(); // 🔥 Toujours afficher la jauge de vie d'abord
        EKOConsole.afficher(getX(), getY(), "\uF4FF", EKOCouleur.CYAN); // Ensuite afficher ton personnage
    }


    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        // Exemple : tu pourrais perdre de la vie si collision avec un ennemi plus tard
        // Ex: if (autre instanceof Ennemi) { perdreVie(10); }
    }

    private boolean peutAller(int x, int y) {
        // Vérifie si la case (x, y) est libre (pas un mur)
        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == x && objet.getY() == y) {
                return false;
            }
        }
        return true;
    }

    private void afficherJaugeVie() {
        int nbCoeursTotal = 5; // 5 cœurs en tout
        int pointsDeVieParCoeur = vieMax / nbCoeursTotal; // 100 / 5 = 20 points par cœur
        int coeursPleins = vie / pointsDeVieParCoeur; // Combien de cœurs pleins

        // Centre la jauge de cœurs en haut de l'écran
        int centreX = (EKOConsole.largeur() - (nbCoeursTotal * 2)) / 2; // Ajuste pour cœurs
        int x = centreX;
        int y = 0;

        for (int i = 0; i < nbCoeursTotal; i++) {
            if (i < coeursPleins) {
                EKOConsole.afficher(x, y, new EKOChaine("\uEC04", EKOCouleur.ROUGE));
            } else {
                EKOConsole.afficher(x, y, new EKOChaine("\uF08A", EKOCouleur.GRIS_FONCE));
            }
            x += 2;
        }


    }



    public void perdreVie(int points) {
        vie -= points;
        if (vie < 0) {
            vie = 0;
        }
    }
}
