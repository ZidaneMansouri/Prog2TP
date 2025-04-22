import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOTouche;
import eko.EKOCouleur;

public class  EcranTitre extends ObjetJeu {

    private long tempsEcouleDepuisCreation = 0;

    public EcranTitre() {
        super("EcranTitre", 0, 0, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        tempsEcouleDepuisCreation += deltaTemps;

        if (tempsEcouleDepuisCreation >= 2000) {
            if (EKOTouche.ESPACE.estEnfoncee()) {
                this.detruire();

                ChargeurSalle.chargerSalle("salle1.txt");
                new Personnage(3, 5);
            }
        }
    }

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

        int centreY = (EKOConsole.hauteur() / 2) - (epee.length / 2) - 1;

        for (int i = 0; i < epee.length; i++) {
            String ligne = epee[i];
            int centreX = (EKOConsole.largeur() - ligne.length()) / 2;
            EKOConsole.afficher(centreX, centreY + i, ligne);
        }

        String titre = " LA LÉGENDE DU GARS (il a une épée)";
        int centreTitreX = (EKOConsole.largeur() - titre.length()) / 2;
        int titreY = centreY + epee.length + 1;

        EKOConsole.afficher(centreTitreX, titreY, new EKOChaine(titre, EKOCouleur.VERT));
    }
}
