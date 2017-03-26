package com.gigster.et.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gigster.et.model.Expense;
import com.gigster.et.model.Report;
import com.gigster.et.service.ExpenseService;

@RestController
public class ExpenseRESTController {

	@Autowired
	private ExpenseService expenseService;

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/users/{username}/expenses/")
	public List<Expense> getExpenses(@PathVariable String username) {
		return expenseService.getExpenses(username);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping("/users/{username}/expenses/")
	public void addExpense(Expense expense, @PathVariable String username) {
		expenseService.addExpense(expense, username);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping("/users/{username}/expenses/{expenseId}")
	public void updateExpense(Expense expense, @PathVariable String username) {
		expenseService.updateExpense(expense, username);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@DeleteMapping("/users/{username}/expenses/{expenseId}")
	public void deleteExpense(@PathVariable long expenseId) {
		expenseService.deleteExpense(expenseId);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/users/{username}/report/weekly")
	public List<Report> getWeeklyExepenses(@PathVariable String username) {
		return expenseService.getWeeklyExpense(username);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/users/{username}/report/")
	public List<Expense> getExepensesBetweenRange(@PathVariable String username,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
		return expenseService.getExpenseBetweenDates(username, from, to);
	}

}
