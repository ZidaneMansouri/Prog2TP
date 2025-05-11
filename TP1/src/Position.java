public class Position {
    public int x;    // Coordonnée horizontale (colonne)
    public int y;    // Coordonnée verticale (ligne)
    public int z;    // Profondeur (couche de dessin, plus grand = au-dessus)

    // Constructeur : crée une position avec x, y et z
    public Position(int x, int y, int z) {
        this.x = x; // position horizontale
        this.y = y; // position verticale
        this.z = z; // ordre d'affichage (profondeur)
    }
}
