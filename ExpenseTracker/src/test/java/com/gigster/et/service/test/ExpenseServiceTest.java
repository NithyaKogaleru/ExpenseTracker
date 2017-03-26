package com.gigster.et.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gigster.et.model.Expense;
import com.gigster.et.model.User;
import com.gigster.et.repository.ExpenseRepository;
import com.gigster.et.repository.UserRepository;
import com.gigster.et.service.impl.ExpenseServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExpenseServiceImpl.class)
public class ExpenseServiceTest {

	@Mock
	ExpenseRepository expenseRepository;

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	ExpenseServiceImpl expenseServiceImpl;
	

	@Test
	public void getExpenseTest_Admin() {
		
		User user = new User();
		Expense expense = new Expense();
		
		user.setRole("ADMIN");

		expense.setDescription("test");
		
		List<Expense> expenses = new ArrayList<>();
		expenses.add(expense);
		
		Mockito.when(userRepository.findOne("test")).thenReturn(user);
		Mockito.when(expenseRepository.findAll()).thenReturn(expenses);
		
		List<Expense> result = expenseServiceImpl.getExpenses("test");
		
		Assert.assertEquals("test", result.get(0).getDescription());
	}
	
	@Test
	public void getExpenseTest_User() {		
		User user = new User();
		Expense expense = new Expense();
		user.setRole("USER");
		
		expense.setDescription("test");
		
		List<Expense> expenses = new ArrayList<>();
		expenses.add(expense);
		
		Mockito.when(userRepository.findOne("test")).thenReturn(user);
		Mockito.when(expenseRepository.findByUserUsername("test")).thenReturn(expenses);
		
		List<Expense> result = expenseServiceImpl.getExpenses("test");
		
		Assert.assertEquals("test", result.get(0).getDescription());
	}
	
	@Test
	public void addExpenseTest(){
		Expense expense = new Expense();
		
		Mockito.when(expenseRepository.save(expense)).thenReturn(expense);		
		expenseServiceImpl.addExpense(expense, "test");
		Mockito.verify(expenseRepository).save(expense);
	}
	
	@Test
	public void deleteExpenseTest(){
		
		Mockito.doNothing().when(expenseRepository).delete(1L);		
		expenseServiceImpl.deleteExpense(1L);
		Mockito.verify(expenseRepository).delete(1L);
	}
	
	@Test
	public void getExpenseBetweenDatesTest(){
		Expense expense = new Expense();
		List<Expense> expenses = new ArrayList<>();
		
		expenses.add(expense);
		
		Mockito.when(expenseRepository.findByUserUsernameAndDateBetween("test", new Date(), new Date())).thenReturn(expenses);
		List<?> result = expenseServiceImpl.getExpenseBetweenDates("test", new Date(), new Date());
		
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void getWeeklyValuesTest(){
		List<Object[]> list = new ArrayList<>();
		
		Mockito.when(expenseRepository.getWeeklyValues("test")).thenReturn(list);
		List<?> result = expenseServiceImpl.getWeeklyExpense("test");
		
		Assert.assertNotNull(result);
		
	}
}
