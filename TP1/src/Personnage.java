import eko.*;

public class Personnage extends ObjetJeu implements Collisionnable {

    private int vieMax = 100; // Vie maximale
    private int vie = 100;    // Vie actuelle
    private boolean aCle = false;
    private static int numSalle = 1;

    // Constructeur : crée le personnage à une position donnée
    public Personnage(int x, int y) {
        super("Personnage", x, y, Etiquette.SOL);
    }

    // Cette méthode est appelée à chaque trame pour gérer les touches et le déplacement
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
        // Change la position si possible (pas un mur)
        if (peutAller(nouveauX, nouveauY)) {
            position.x = nouveauX;
            position.y = nouveauY;
        }
    }

    // Dessine le personnage à l'écran avec sa jauge de vie et le numéro de salle
    @Override
    protected void dessiner() {
        afficherJaugeVie();
        EKOConsole.afficher(getX(), getY(), "\uF4FF", EKOCouleur.CYAN);
        String SalleTxt = "Salle:" + numSalle;
        int SalleX = EKOConsole.largeur() - (SalleTxt.length() + 2);
        EKOConsole.afficher(SalleX, 0, SalleTxt, EKOCouleur.JAUNE);

    }

    // Gère les collisions avec les objets (clé, porte, ennemis)
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
// Si le joueur touche la porte verrouillée (et a la clé)
        if (autre instanceof PorteVerrouillee) {
            if (aCle && autre.getX() == getX() && autre.getY() == getY()) {
                GestionnaireObjetsJeu.obtenir().viderSalle();
// Charge la prochaine salle selon le numéro actuel
                switch (numSalle) {
                    case 1:
                        numSalle++;
                        ChargeurSalle.chargerSalle("salle2.txt");

                        break;
                    case 2:
                        numSalle++;
                        ChargeurSalle.chargerSalle("salle3.txt");

                        break;
                    case 3:
                        numSalle++;
                        ChargeurSalle.chargerSalle("salle4.txt");

                        break;
                    case 4:
                    default:
                        new EcranVictoire();
                        this.desactiver();
                        break;
                }
            }
        }
        // Si le joueur touche un ennemi
        if (autre.etiquette == Etiquette.ENNEMI) {
            perdreVie(20); // chaque contact = 1 cœur perdu
            EKOSon Ouch = EKOAudio.charger("audio/Ouch_Bobo.wav");
            EKOAudio.jouer(Ouch, false);

            // Si la vie tombe à 0, écran de défaite
            if (vie <= 0) {
                GestionnaireObjetsJeu.obtenir().viderSalle();
                new EcranDefaite(); // Tu dois avoir cette classe créée
            } else {
                String SalleTxt = "salle" + numSalle + ".txt";
                Position spawn = ChargeurSalle.trouverSpawn(SalleTxt);
                position.x = spawn.x;
                position.y = spawn.y;
            }
        }
    }

    // Vérifie si le joueur peut aller à une position (pas un mur)
    private boolean peutAller(int x, int y) {
        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == x && objet.getY() == y) {
                return false;
            }
        }
        return true;
    }

    // Affiche les cœurs de vie du joueur (plein ou vides)
    private void afficherJaugeVie() {
        int nbCoeursTotal = 5;
        int pointsDeVieParCoeur = vieMax / nbCoeursTotal;
        int coeursPleins = vie / pointsDeVieParCoeur;
        int x = 2;
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

    // Enlève des points de vie
    public void perdreVie(int points) {
        vie -= points;
        if (vie < 0) {
            vie = 0;
        }
    }

    // Remet les points de vie à 100 (utilisé pour la potion)
    public void RegenererVie() {
        this.vie = vieMax;
    }
}