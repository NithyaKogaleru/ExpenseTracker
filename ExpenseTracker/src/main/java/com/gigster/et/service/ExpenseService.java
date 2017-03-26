package com.gigster.et.service;

import java.util.Date;
import java.util.List;

import com.gigster.et.model.Expense;
import com.gigster.et.model.Report;

public interface ExpenseService {

	List<Expense> getExpenses(String username);

	void addExpense(Expense expense, String username);

	void updateExpense(Expense expense, String username);

	void deleteExpense(long expenseId);

	List<Report> getWeeklyExpense(String username);

	List<Expense> getExpenseBetweenDates(String username, Date from, Date to);

}