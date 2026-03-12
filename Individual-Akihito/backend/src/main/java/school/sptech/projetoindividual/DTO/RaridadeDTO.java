package school.sptech.projetoindividual.DTO;

public class RaridadeDTO {
    private int id;
    private String raridade;

    public RaridadeDTO() {}

    public RaridadeDTO(int id, String raridade) {
        this.id = id;
        this.raridade = raridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }
}
