package model;

public class Tarefa {
    
    private Integer idTarefa;
    private String descricao;
    private boolean finalizada;
    private Integer idUsuario;
    private String nomeImagem;

    public Tarefa() {
        
    }

    public Tarefa(Integer idTarefa, String descricao, boolean finalizada, Integer idUsuario) {
        this.idTarefa = idTarefa;
        this.descricao = descricao;
        this.finalizada = finalizada;
        this.idUsuario = idUsuario;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
    
}
