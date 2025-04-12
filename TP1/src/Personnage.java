import eko.EKOConsole;
import eko.EKOTouche;

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
        EKOConsole.afficher(getX(), getY(), "P"); // Ensuite afficher ton personnage
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
                return false; // 🚫 Il y a un mur ici, interdit d'aller
            }
        }
        return true; // ✅ Pas de mur, on peut y aller
    }

    private void afficherJaugeVie() {
        int nbCoeursTotal = 5; // 5 cœurs en tout
        int pointsDeVieParCoeur = vieMax / nbCoeursTotal; // 100 / 5 = 20 points par cœur
        int coeursPleins = vie / pointsDeVieParCoeur; // Combien de cœurs pleins

        StringBuilder jauge = new StringBuilder();
        for (int i = 0; i < nbCoeursTotal; i++) {
            if (i < coeursPleins) {
                jauge.append("❤️ ");
            } else {
                jauge.append("🤍 ");
            }
        }

        // Centre la jauge de cœurs en haut de l'écran
        int centreX = (EKOConsole.largeur() - (nbCoeursTotal * 2)) / 2; // Ajuste pour cœurs
        EKOConsole.afficher(centreX, 0, jauge.toString());
    }



    public void perdreVie(int points) {
        vie -= points;
        if (vie < 0) {
            vie = 0;
        }
    }
}
