import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;
import eko.EKOChaine;

public class Personnage extends ObjetJeu implements Collisionnable {

    private int vieMax = 100; // Vie maximale
    private int vie = 100;    // Vie actuelle
    private boolean aCle = false;
    private static int numSalle = 1;

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
        afficherJaugeVie();
        EKOConsole.afficher(getX(), getY(), "\uF4FF", EKOCouleur.CYAN); // Ensuite afficher ton personnage
        String SalleTxt = "Salle: " + numSalle ;
        int SalleX = EKOConsole.largeur() - SalleTxt.length();
        EKOConsole.afficher(SalleX, 0, SalleTxt, EKOCouleur.JAUNE);

    }


    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Clef) {
            aCle = true;
        }

        if (autre instanceof PorteVerrouillee) {
            if (aCle && autre.getX() == getX() && autre.getY() == getY()) {
                GestionnaireObjetsJeu.obtenir().viderSalle();
                ChargeurSalle.chargerSalle("salle2.txt");
                new Personnage(3, 5); //
                Personnage.numSalle++;
            }
        }
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
        int x= 0;
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
