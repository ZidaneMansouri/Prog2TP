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
//Gère les déplacements avec les flèches (haut, bas, gauche, droite).
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


        if (peutAller(nouveauX, nouveauY)) {
            position.x = nouveauX;
            position.y = nouveauY;
        }
    }
//	Affiche le personnage + la jauge de vie + le numéro de salle.
    @Override
    protected void dessiner() {
        afficherJaugeVie();
        EKOConsole.afficher(getX(), getY(), "\uF4FF", EKOCouleur.CYAN);
        String SalleTxt = "Salle:" + numSalle ;
        int SalleX = EKOConsole.largeur() - (SalleTxt.length() + 2);
        EKOConsole.afficher(SalleX, 0, SalleTxt, EKOCouleur.JAUNE);

    }

//Gère ce qui se passe quand le personnage touche une clé ou une porte.
@Override
public void gererCollisionAvec(ObjetJeu autre) {
    if (autre instanceof Clef) {
        aCle = true;

        for (ObjetJeu obj : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.SORTIE)) {
            if (obj instanceof PorteVerrouillee) {
                ((PorteVerrouillee) obj).deverrouiller();
            }
        }
    }

    if (autre instanceof PorteVerrouillee) {
        if (aCle && autre.getX() == getX() && autre.getY() == getY()) {
            GestionnaireObjetsJeu.obtenir().viderSalle();

            numSalle++;

            switch (numSalle) {
                case 2:
                    ChargeurSalle.chargerSalle("salle2.txt");
                    new Personnage(3, 5);
                    break;
                case 3:
                    ChargeurSalle.chargerSalle("salle3.txt");
                    new Personnage(3, 5);
                    break;
                case 4:
                    ChargeurSalle.chargerSalle("salle4.txt");
                    new Personnage(3, 5);
                    break;
                default:
                    String message1 = "Bravo ! Vous avez terminé toutes les salles !";
                    String message2 = "Appuyez sur ÉCHAPPE pour quitter.";
                    int x1 = (EKOConsole.largeur() - message1.length()) / 2;
                    int x2 = (EKOConsole.largeur() - message2.length()) / 2;
                    int y = EKOConsole.hauteur() / 2;

                    EKOConsole.afficher(x1, y, message1, EKOCouleur.VERT);
                    EKOConsole.afficher(x2, y + 2, message2, EKOCouleur.GRIS_PALE);
                    this.desactiver();
                    break;
            }
        }
    }
}
    //Vérifie si la case visée n’est pas un mur.
    private boolean peutAller(int x, int y) {

        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == x && objet.getY() == y) {
                return false;
            }
        }
        return true;
    }
//Affiche les 5 cœurs (rouges ou gris) en haut de l'écran.
    private void afficherJaugeVie() {
        int nbCoeursTotal = 5;
        int pointsDeVieParCoeur = vieMax / nbCoeursTotal;
        int coeursPleins = vie / pointsDeVieParCoeur;
        int x= 2;
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

//Fait baisser les points de vie du joueur.

    public void perdreVie(int points) {
        vie -= points;
        if (vie < 0) {
            vie = 0;
        }
    }
}
