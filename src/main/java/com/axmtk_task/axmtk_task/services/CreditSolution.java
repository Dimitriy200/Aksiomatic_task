package com.axmtk_task.axmtk_task.services;


import java.math.BigDecimal;

public class CreditSolution {
    private final boolean solution_status;
    private final double probability;
    private final short solution_date;
    private final short max_solution_date;
    private final short credit_amount;

    public CreditSolution(short credit_amount) {
        this.max_solution_date = 12;

        this.probability = Math.random();
        this.solution_status = probability > 0.5;

        double res_date = this.max_solution_date * this.probability;
        this.solution_date = (short) res_date;

        double res_amount = credit_amount * probability;
        this.credit_amount = (short) res_amount;
    }

    public boolean isSolution_status() {
        return solution_status;
    }

    public double getProbability() {
        return probability;
    }

    public short getSolution_date() {
        return solution_date;
    }

    public short getCredit_amount() {
        return credit_amount;
    }
}