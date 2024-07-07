package com.axmtk_task.axmtk_task.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditSolutionTest extends TestCase {

    long credit_amount_1 = 1;
    CreditSolution creditSolution_1;

    @Before
    public void setUp(){
        creditSolution_1 = new CreditSolution(credit_amount_1);

    }

    @Test
    public void testIsSolution_status() {
        System.out.println(creditSolution_1.getCredit_amount());
        Assert.assertTrue(creditSolution_1.getCredit_amount() > 0 & creditSolution_1.getCredit_amount() <= credit_amount_1);
    }

    public void testGetProbability() {
        System.out.println(creditSolution_1.getSolution_date());
        Assert.assertTrue(creditSolution_1.getCredit_amount() > 0 & creditSolution_1.getCredit_amount() <= creditSolution_1.getSolution_MaxDate());
    }

    public void testGetSolution_date() {
    }

    public void testGetCredit_amount() {
    }
}