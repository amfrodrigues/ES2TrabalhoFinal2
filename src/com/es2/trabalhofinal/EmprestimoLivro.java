package com.es2.trabalhofinal;

import java.time.LocalDate;

public class EmprestimoLivro implements IEmprestimoLivro {
    private final int indexlivro;
    private final int indexRepositorio;
    private final LocalDate dataEmprestimoInicio;
    private final int diasDuracaoEmprestimo;
    private int numeroVezesExtencaoEmprestimo;

    public EmprestimoLivro(int indexlivro, int indexRepositorio, int diasDuracaoEmprestimo) throws ExceptionIndiceLivroEmprestadoInvalido, ExceptionIndiceRepositorioLivroInvalido, ExceptionEmprestimoLivroDuraçaoInvalida {
        if (indexlivro < 0) throw new ExceptionIndiceLivroEmprestadoInvalido();
        if (indexRepositorio < 0) throw new ExceptionIndiceRepositorioLivroInvalido();
        if (diasDuracaoEmprestimo < 1) throw new ExceptionEmprestimoLivroDuraçaoInvalida();
        this.indexlivro = indexlivro;
        this.indexRepositorio = indexRepositorio;
        this.diasDuracaoEmprestimo = diasDuracaoEmprestimo;
        this.dataEmprestimoInicio = LocalDate.now().minusDays(4); // Data de hoje menos 4 dias *para testes* || normalmente seria so LocalDate.now()
        this.numeroVezesExtencaoEmprestimo = 0;
    }


    @Override
    public LocalDate getDataInicialEmprestimo() {
        return this.dataEmprestimoInicio;
    }

    @Override
    public void extenderEmprestimo() throws ExceptionEmprestimoLivroLimiteExtenderExcedido {
        if (this.numeroVezesExtencaoEmprestimo > 1) throw new ExceptionEmprestimoLivroLimiteExtenderExcedido();
        this.numeroVezesExtencaoEmprestimo++;
    }

    @Override
    public int getIndexLivroEmprestimo() {
        return this.indexlivro;
    }

    @Override
    public int getIndexRepositorioEmprestimo() {
        return this.indexRepositorio;
    }

    @Override
    public boolean checkEmprestimoValido() throws ExceptionEmprestimoLivroDataEmprestimoExcedida {
        int diasEmprestimoExtra = diasDuracaoEmprestimo * numeroVezesExtencaoEmprestimo;
        int diasEmprestimoTotal = diasEmprestimoExtra + diasDuracaoEmprestimo;
        LocalDate dataFinalEmprestimo = dataEmprestimoInicio.plusDays(diasEmprestimoTotal);

        if (LocalDate.now().isAfter(dataFinalEmprestimo)) {
            throw new ExceptionEmprestimoLivroDataEmprestimoExcedida();
        }
        return true;
    }
}
