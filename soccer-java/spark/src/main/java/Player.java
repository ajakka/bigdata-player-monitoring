public class Player {
    private Integer id;
    private String nationality;
    private String player_url;
    private String short_name;
    private String long_name;
    private Integer age;
    private String dob;
    private String height_cm;
    private String weight_kg;
    private String value_eur;

    public Player(Integer id, String nationality, String player_url, String short_name, String long_name, Integer age, String dob, String height_cm, String weight_kg, String value_eur) {
        this.id = id;
        this.nationality = nationality;
        this.player_url = player_url;
        this.short_name = short_name;
        this.long_name = long_name;
        this.age = age;
        this.dob = dob;
        this.height_cm = height_cm;
        this.weight_kg = weight_kg;
        this.value_eur = value_eur;
    }

    public Player() {
        this.id=0;
        this.age= Integer.valueOf(0);
        this.dob="";
        this.height_cm="";
        this.player_url="";
        this.long_name="";
        this.nationality="";
        this.short_name="";
        this.value_eur="";
        this.weight_kg="";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlayer_url() {
        return player_url;
    }

    public void setPlayer_url(String player_url) {
        this.player_url = player_url;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHeight_cm() {
        return height_cm;
    }

    public void setHeight_cm(String height_cm) {
        this.height_cm = height_cm;
    }

    public String getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(String weight_kg) {
        this.weight_kg = weight_kg;
    }

    public String getValue_eur() {
        return value_eur;
    }

    public void setValue_eur(String value_eur) {
        this.value_eur = value_eur;
    }
}
