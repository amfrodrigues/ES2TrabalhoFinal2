package com.es2.trabalhofinal;

public class Editora implements IEditora {
    private final String nome;
    private final String termoResponsabilidade;

    public Editora(String nome, String termoResponsabilidade) throws ExceptionTermoResponsabilidadeEditoraNulo, ExceptionNomeEditoraNulo {
        if (termoResponsabilidade == null) throw new ExceptionTermoResponsabilidadeEditoraNulo();
        if (nome == null) throw new ExceptionNomeEditoraNulo();
        this.nome = nome;
        this.termoResponsabilidade = termoResponsabilidade;
    }


    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getTermoResponsabilidade() {
        return this.termoResponsabilidade;
    }
}
