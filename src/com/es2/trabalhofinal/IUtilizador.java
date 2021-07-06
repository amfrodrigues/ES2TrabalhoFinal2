package com.es2.trabalhofinal;

import java.util.List;

public interface IUtilizador {
    List<IEmprestimoLivro> getAllEmprestimosUtilizador() throws ExceptionUtilizadorListaEmprestimosVazia;

    void adicionarEmprestimoLivro(IEmprestimoLivro emprestimoLivro) throws ExceptionUtilizadorLivroJaEmprestado, ExceptionEmprestimoLivroNulo;

    IEmprestimoLivro getLivroEmprestimo(int indexEmprestimoLivro) throws ExceptionListaLivrosEmprestadosVazia, ExceptionUtilizadorIndexEmprestimoLivroInvalido;

    void desativarUtilizador();

    boolean checkLogin(String nome, String password);
}
