package com.es2.trabalhofinal;

import java.time.LocalDate;

public interface IEmprestimoLivro {
    LocalDate getDataInicialEmprestimo();

    void extenderEmprestimo() throws ExceptionEmprestimoLivroLimiteExtenderExcedido;

    int getIndexLivroEmprestimo();

    int getIndexRepositorioEmprestimo();

    boolean checkEmprestimoValido() throws ExceptionEmprestimoLivroDataEmprestimoExcedida;
}
