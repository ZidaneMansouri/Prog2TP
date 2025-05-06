import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;

public class EcranDefaite extends ObjetJeu {

    public EcranDefaite() {
        super("EcranDefaite", 0, 0, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.ESPACE.estEnfoncee() || EKOTouche.ECHAPPEMENT.estEnfoncee()) {
            System.exit(0); // Quitter le jeu
        }
    }

    @Override
    protected void dessiner() {
        String message1 = "VOUS AVEZ ÉTÉ VAINCU";
        String message2 = "Appuyez sur ESPACE ou ÉCHAPPE pour quitter.";

        int centreX1 = (EKOConsole.largeur() - message1.length()) / 2;
        int centreX2 = (EKOConsole.largeur() - message2.length()) / 2;
        int centreY = EKOConsole.hauteur() / 2;

        EKOConsole.afficher(centreX1, centreY - 1, message1, EKOCouleur.ROUGE);
        EKOConsole.afficher(centreX2, centreY + 1, message2, EKOCouleur.GRIS_PALE);
    }
}
