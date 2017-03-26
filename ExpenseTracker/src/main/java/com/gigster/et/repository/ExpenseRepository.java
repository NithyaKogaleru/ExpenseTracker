package com.gigster.et.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gigster.et.model.Expense;

@Repository("expenseRepository")
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	public List<Expense> findByUserUsername(String username);

	@Query(value = "SELECT CONCAT(YEAR(date), '/', WEEK(date)) AS week_name, YEAR(date), WEEK(date), SUM(amount) FROM expense where username = ?1 GROUP BY week_name ORDER BY YEAR(DATE) ASC, WEEK(date) ASC", nativeQuery = true)
	public List<Object[]> getWeeklyValues(String username);
	
	public List<Expense> findByUserUsernameAndDateBetween(String username, Date from, Date to);

}
