package Modelo;

public class Dog {
    
    private Integer id;
    private String nome;
    private String raca;
    private String dtnasc;
    private String cor;
    
    public Dog(){}
    
    public Dog (String nome, String raca, String dtnasc, String cor){
        this.nome = nome;
        this.raca = raca;
        this.dtnasc = dtnasc;
        this.cor = cor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(String dtnasc) {
        this.dtnasc = dtnasc;
    }
    
     public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
