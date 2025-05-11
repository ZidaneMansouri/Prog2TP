import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOTouche;
import eko.EKOCouleur;

public class EcranTitre extends ObjetJeu {

    private long tempsEcouleDepuisCreation = 0;

    // Constructeur : crée l’écran titre du jeu
    public EcranTitre() {
        super("EcranTitre", 0, 0, Etiquette.SOL);
    }

    // Met à jour l’écran à chaque trame
    @Override
    protected void mettreAJour(long deltaTemps) {
        tempsEcouleDepuisCreation += deltaTemps;
        // On attend 2 secondes avant d'accepter un appui sur ESPACE
        if (tempsEcouleDepuisCreation >= 2000) {
            if (EKOTouche.ESPACE.estEnfoncee()) {
                this.detruire();

                ChargeurSalle.chargerSalle("salle1.txt");
                Position depart = ChargeurSalle.trouverSpawn("salle1.txt");

            }
        }
    }

    // Dessine l’écran avec une épée ASCII et le titre du jeu
    @Override
    protected void dessiner() {
        String[] epee = {
                " /\\",
                " /  \\",
                "   |  |  ",
                "   |  |  ",
                "   |  |  ",
                "   |  |  ",
                "   |  |  ",
                "   |  |  ",
                " |  |",
                "  __|__|__ ",
                " |________|",
                "   ||  ",
                "  || ",
                "   "
        };
        // Calcule la position pour afficher l’épée au centre
        int centreY = (EKOConsole.hauteur() / 2) - (epee.length / 2) - 1;

        for (int i = 0; i < epee.length; i++) {
            String ligne = epee[i];
            int centreX = (EKOConsole.largeur() - ligne.length()) / 2;
            EKOConsole.afficher(centreX, centreY + i, ligne);
        }
        // Affiche le titre du jeu sous l’épée
        String titre = " LA LÉGENDE DU GARS (il a une épée)";
        int centreTitreX = (EKOConsole.largeur() - titre.length()) / 2;
        int titreY = centreY + epee.length + 1;

        EKOConsole.afficher(centreTitreX, titreY, new EKOChaine(titre, EKOCouleur.VERT));
    }
}
