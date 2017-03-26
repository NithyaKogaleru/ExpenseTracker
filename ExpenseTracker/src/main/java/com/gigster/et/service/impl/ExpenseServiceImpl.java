package com.gigster.et.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigster.et.model.Expense;
import com.gigster.et.model.Report;
import com.gigster.et.model.User;
import com.gigster.et.repository.ExpenseRepository;
import com.gigster.et.repository.UserRepository;
import com.gigster.et.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Expense> getExpenses(String username) {
		User user = userRepo.findOne(username);
		
		if ("ADMIN".equals(user.getRole())) {
			return expenseRepo.findAll();
		}
		return expenseRepo.findByUserUsername(username);
	}

	@Override
	public void addExpense(Expense expense, String username) {
		User user = userRepo.findOne(username);
		expense.setUser(user);
		expenseRepo.save(expense);
	}

	@Override
	public void updateExpense(Expense expense, String username) {
		User user = userRepo.findOne(username);
		expense.setUser(user);
		expenseRepo.save(expense);
	}

	@Override
	public void deleteExpense(long expenseId) {
		expenseRepo.delete(expenseId);
	}

	@Override
	public List<Report> getWeeklyExpense(String username) {
		List<Object[]> weeklyInfo = expenseRepo.getWeeklyValues(username);
		return weeklyInfo.stream().map(entry -> {
			Report report = new Report();
			report.setUsername(username);
			report.setYear((Integer) entry[1]);
			report.setWeek((Integer) entry[2]);
			report.setSum((Double) entry[3]);
			return report;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Expense> getExpenseBetweenDates(String username, Date from, Date to) {
		return expenseRepo.findByUserUsernameAndDateBetween(username, from, to);
	}
}
