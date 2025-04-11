import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOTouche;
import eko.EKOCouleur;

public class EcranTitre extends ObjetJeu {

    private long tempsEcouleDepuisCreation = 0;

    public EcranTitre() {
        super("EcranTitre", 0, 0, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        tempsEcouleDepuisCreation += deltaTemps;

        if (tempsEcouleDepuisCreation >= 2000) { // attendre 2 secondes
            if (EKOTouche.ESPACE.estEnfoncee()) {
                this.detruire();
                // ici tu commenceras ton jeu
            }
        }
    }

    @Override
    protected void dessiner() {
        String[] grenouille = {
                "  @..@",
                "  (----)",
                " ( >__< )",
                " ^^ ~~ ^^",
        };

        int hauteurGrenouille = grenouille.length;
        int centreY = (EKOConsole.hauteur() / 2) - (hauteurGrenouille / 2) - 1;
        // "-1" pour que FROGGYRINTHE soit juste bien en dessous

        // Afficher chaque ligne de la grenouille
        for (int i = 0; i < grenouille.length; i++) {
            String ligne = grenouille[i];
            int centreX = (EKOConsole.largeur() - ligne.length()) / 2;
            EKOConsole.afficher(centreX, centreY + i, ligne);
        }

        // Afficher "FROGGYRINTHE" en dessous
        String titre = "FROGGYRINTHE";
        int centreTitreX = (EKOConsole.largeur() - titre.length()) / 2;
        int titreY = centreY + grenouille.length + 1; // juste après la grenouille

        EKOConsole.afficher(centreTitreX, titreY, new EKOChaine(titre, EKOCouleur.VERT));
    }
}
