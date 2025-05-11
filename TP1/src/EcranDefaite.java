import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;

public class EcranDefaite extends ObjetJeu {
    // Constructeur : crée l'objet "écran de défaite"
    public EcranDefaite() {
        super("EcranDefaite", 0, 0, Etiquette.SOL);
    }

    // Cette méthode est appelée à chaque trame pour vérifier si une touche est appuyée
    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.ESPACE.estEnfoncee() || EKOTouche.ECHAPPEMENT.estEnfoncee()) {
            System.exit(0); // Quitter le jeu
        }
    }

    // Cette méthode affiche le texte à l'écran
    @Override
    protected void dessiner() {
        String message1 = "VOUS AVEZ ÉTÉ VAINCU";
        String message2 = "Appuyez sur ESPACE ou ÉCHAPPE pour quitter.";

        // Calcul de la position horizontale pour centrer les textes
        int centreX1 = (EKOConsole.largeur() - message1.length()) / 2;
        int centreX2 = (EKOConsole.largeur() - message2.length()) / 2;
        int centreY = EKOConsole.hauteur() / 2;
        // Affichage des messages dans la console
        EKOConsole.afficher(centreX1, centreY - 1, message1, EKOCouleur.ROUGE);
        EKOConsole.afficher(centreX2, centreY + 1, message2, EKOCouleur.GRIS_PALE);
    }
}
