import eko.EKO;


public class FroggyrintheMain {

    public static void main(String[] args) {
        EKO.initialiser("Froggyrinthe", 75, 28); // Ouvre la fenêtre EKO
        new EcranDeveloppeurs(); // Crée l'écran de développeurs
        boucleDeJeu(); // Lance la boucle de jeu
    }

    private static void boucleDeJeu() {
        final int TPS = 30;
        final long MS_PAR_TRAME = 1000 / TPS;

        long tempsAttente;
        long maintenant;
        long deltaTemps;
        long dernierTemps = System.nanoTime();

        while (true) {
            maintenant = System.nanoTime();
            deltaTemps = maintenant - dernierTemps;
            dernierTemps = maintenant;

            GestionnaireObjetsJeu.obtenir().mettreAJour(deltaTemps / 1_000_000);
            GestionnaireObjetsJeu.obtenir().dessiner();

            tempsAttente = MS_PAR_TRAME - (System.nanoTime() - dernierTemps) / 1_000_000;
            if (tempsAttente > 0) {
                EKO.attendre(tempsAttente);
            }
        }
    }
}
