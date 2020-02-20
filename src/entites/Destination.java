package entites;


public class Destination {

    private int _Ref;
    private String _Nom;
    private String _Adresse;
    private String _Type;
    private float _Rating;
    private Voyage v;
    public Destination(String _Nom, String _Adresse, String _Type, float _Rating , Voyage v) {
        this._Nom = _Nom;
        this._Adresse = _Adresse;
        this._Type = _Type;
        this._Rating = _Rating;
        this.v = v;
    }

    public Destination(int _Ref, String _Nom, String _Adresse, String _Type, float _Rating, Voyage v) {
        this._Ref = _Ref;
        this._Nom = _Nom;
        this._Adresse = _Adresse;
        this._Type = _Type;
        this._Rating = _Rating;
        this.v = v;
    }
    
    public Destination(){
        
    }

    public Voyage getV() {
        return v;
    }

    public void setV(Voyage v) {
        this.v = v;
    }
    public int getRef() {
        return _Ref;
    }

    public void setRef(int value) {
        _Ref = value;
    }

    public String getNom() {
        return _Nom;
    }

    public void setNom(String value) {
        _Nom = value;
    }

    public String getAdresse() {
        return _Adresse;
    }

    public void setAdresse(String value) {
        _Adresse = value;
    }

    public String getType() {
        return _Type;
    }

    public void setType(String value) {
        _Type = value;
    }

    public float getRating() {
        return _Rating;
    }

    public void setRating(float value) {
        _Rating = value;
    }
}
