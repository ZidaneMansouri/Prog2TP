import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChargeurSalle {

    public static void chargerSalle(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int y = 0;

            while ((ligne = lecteur.readLine()) != null) {
                for (int x = 0; x < ligne.length(); x++) {
                    char symbole = ligne.charAt(x);

                    switch (symbole) {
                        case 'A':
                            new Personnage(x, y);
                            break;
                        case '#':
                            new Mur(x, y);
                            break;
                        case '+':
                            new PorteCondamnee(x, y);
                            break;
                        case 'C':
                            new Clef(x, y);
                            break;
                        case '-':
                            new PorteVerrouillee(x, y);
                            break;
                        case 'F':
                            new Feu(x, y);
                            break;
                        case 'G':
                            new Fantome(x, y, true); // Fantôme horizontal
                            break;
                        case 'V':
                            new Fantome(x, y, false); // Fantôme vertical
                            break;
                        case 'S':
                            new Squelette(x, y);
                            break;
                        case 'I':
                            new Insecte(x, y);
                            break;
                        case 'R':
                            new Grenouille(x, y, true); // langue à droite
                            break;
                        case 'L':
                            new Grenouille(x, y, false); // langue à gauche
                            break;
                        case 'P':
                            new Potion(x, y);
                            break;
                        default:
                            // sol vide ou caractère ignoré
                            break;
                    }
                }
                y++;
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + cheminFichier);
            e.printStackTrace();
        }
    }

    public static Position trouverSpawn(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int y = 0;
            while ((ligne = lecteur.readLine()) != null) {
                for (int x = 0; x < ligne.length(); x++) {
                    if (ligne.charAt(x) == 'A') {
                        return new Position(x, y, 0);
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Position(3, 5, 0); // position par défaut
    }
}
