package com.axmtk_task.axmtk_task.services;


import java.math.BigDecimal;

public class CreditSolution implements  Solution{
    private final boolean solution_status;
    private final double probability;
    private final long solution_date;
    private final long max_solution_date;
    private final long credit_amount;

    public CreditSolution(long credit_amount) {
        this.max_solution_date = 12;

        this.probability = Math.random();
        this.solution_status = probability > 0.5;

        double res_date = this.max_solution_date * this.probability;
        this.solution_date = (long) res_date;

        this.credit_amount = credit_amount;
//        double res_amount = credit_amount * probability;
//        this.credit_amount = (long) res_amount;
    }

    public boolean getSolution_status() {
        return this.solution_status;
    }

    public double getProbability() {
        return this.probability;
    }

    public long getSolution_date() {
        return this.solution_date;
    }

    public long getSolution_MaxDate() {
        return this.max_solution_date;
    }

    public long getCredit_amount() {
        return this.credit_amount;
    }
}