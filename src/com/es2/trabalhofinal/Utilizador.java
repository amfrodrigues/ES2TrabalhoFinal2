package com.es2.trabalhofinal;

import java.util.ArrayList;
import java.util.List;

public class Utilizador implements IUtilizador {
    private final String nome;
    private final String password;
    List<IEmprestimoLivro> listaLivrosEmprestados;
    private boolean isAtivo;

    public Utilizador(String nome, String password) throws ExceptionUtilizadorNomeNulo, ExceptionUtilizadorPasswordNula {
        if (nome == null) throw new ExceptionUtilizadorNomeNulo();
        if (password == null) throw new ExceptionUtilizadorPasswordNula();
        this.nome = nome;
        this.password = password;
        this.isAtivo = true;
        this.listaLivrosEmprestados = new ArrayList<>();
    }

    @Override
    public List<IEmprestimoLivro> getAllEmprestimosUtilizador() throws ExceptionUtilizadorListaEmprestimosVazia {
        if (this.listaLivrosEmprestados.isEmpty()) throw new ExceptionUtilizadorListaEmprestimosVazia();
        return this.listaLivrosEmprestados;
    }

    @Override
    public void adicionarEmprestimoLivro(IEmprestimoLivro emprestimoLivro) throws ExceptionUtilizadorLivroJaEmprestado, ExceptionEmprestimoLivroNulo {
        if (emprestimoLivro == null) throw new ExceptionEmprestimoLivroNulo();
        if (this.listaLivrosEmprestados.contains(emprestimoLivro)) throw new ExceptionUtilizadorLivroJaEmprestado();
        listaLivrosEmprestados.add(emprestimoLivro);
    }

    @Override
    public IEmprestimoLivro getLivroEmprestimo(int indexEmprestimoLivro) throws ExceptionListaLivrosEmprestadosVazia, ExceptionUtilizadorIndexEmprestimoLivroInvalido {
        if (this.listaLivrosEmprestados.isEmpty()) throw new ExceptionListaLivrosEmprestadosVazia();
        if (indexEmprestimoLivro > this.listaLivrosEmprestados.size() - 1 || indexEmprestimoLivro < 0)
            throw new ExceptionUtilizadorIndexEmprestimoLivroInvalido();
        return this.listaLivrosEmprestados.get(indexEmprestimoLivro);
    }

    @Override
    public void desativarUtilizador() {
        this.isAtivo = false;
    }

    @Override
    public boolean checkLogin(String nome, String password) {
        return this.nome.equals(nome) && this.password.equals(password) && isAtivo;
    }
}
