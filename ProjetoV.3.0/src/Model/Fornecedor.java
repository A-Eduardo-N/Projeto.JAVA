package Model;

public class Fornecedor {
    private int idFornecedor;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    public Fornecedor(int idFornecedor, String nome, String telefone, String endereco, String email) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Fornecedor{idFornecedor=" + idFornecedor + ", nome='" + nome + "', telefone='" + telefone + "', endereco='" + endereco + "', email='" + email + "'}";
    }
}
