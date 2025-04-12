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

        if (tempsEcouleDepuisCreation >= 2000) { // attendre 2 secondes
            if (EKOTouche.ESPACE.estEnfoncee()) {
                this.detruire();
                // 🔥 Charger la salle ici
                ChargeurSalle.chargerSalle("salle1.txt");
                new Personnage(2, 2); // (ex: personnage position x=2, y=2)
            }
        }
    }

    @Override
    protected void dessiner() {
        String[] grenouille = {
                "   @..@",
                "  (----)",
                "  ( >__< )",
                "  ^^~~  ~~^^",
        };

        int centreY = (EKOConsole.hauteur() / 2) - (grenouille.length / 2) - 1;

        for (int i = 0; i < grenouille.length; i++) {
            String ligne = grenouille[i];
            int centreX = (EKOConsole.largeur() - ligne.length()) / 2;
            EKOConsole.afficher(centreX, centreY + i, ligne);
        }

        String titre = " FROGGYRINTHE";
        int centreTitreX = (EKOConsole.largeur() - titre.length()) / 2;
        int titreY = centreY + grenouille.length + 1;

        EKOConsole.afficher(centreTitreX, titreY, new EKOChaine(titre, EKOCouleur.VERT));
    }
}
