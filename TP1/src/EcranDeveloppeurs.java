import eko.EKOConsole;
import eko.EKOTouche;

public class EcranDeveloppeurs extends ObjetJeu {
    // Constructeur : crée l'écran qui montre les noms des développeurs
    public EcranDeveloppeurs() {
        super("EcranDeveloppeurs", 0, 0, Etiquette.SOL);
    }

    // Cette méthode vérifie si le joueur appuie sur ESPACE
    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.ESPACE.estEnfoncee()) {
            this.detruire();
            new EcranTitre();
        }
    }
    // Cette méthode affiche le texte de présentation à l'écran
    @Override
    protected void dessiner() {
        String texte1 = "Développé par : Antranik Bakkalian et Zidane Rakane Mansouri";
        String texte2 = "Appuyez sur ESPACE pour continuer...";

        // Calcul de la position pour centrer les textes dans la console
        int centreX1 = (EKOConsole.largeur() - texte1.length()) / 2;
        int centreX2 = (EKOConsole.largeur() - texte2.length()) / 2;
        int centreY = EKOConsole.hauteur() / 2;
        // Affiche les deux lignes à l'écran
        EKOConsole.afficher(centreX1, centreY - 1, texte1);
        EKOConsole.afficher(centreX2, centreY + 1, texte2);
    }
}
