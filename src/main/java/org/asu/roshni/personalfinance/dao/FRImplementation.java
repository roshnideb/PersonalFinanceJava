package org.asu.roshni.personalfinance.dao;

import org.asu.roshni.personalfinance.model.Account;
import org.asu.roshni.personalfinance.model.Category;
import org.asu.roshni.personalfinance.model.Transaction;
import org.asu.roshni.personalfinance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FRImplementation implements FinanceRepository {

    @Autowired
    private JdbcTemplate financeConnection;


    @Override
    public User getUser(String email) {
        return financeConnection.queryForObject("select * from Users where email=?",
                new Object[]{email},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(rs.getInt("userid"), rs.getString("password"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
                    }
                });
    }

    @Override
    public String getUserName(String email) {
        return getUser(email).getFname() + " " + getUser(email).getLname();
    }

    @Override
    public int getUserid(String email) {
        return getUser(email).getUserid();
    }

    @Override
    public List<Account> getAccounts(int userid) {
        return financeConnection.query("select * from Accounts where userid=?",
                new Object[]{userid},
                new RowMapper<Account>() {
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(rs.getInt("accountid"), rs.getInt("userid"), rs.getString("name"), rs.getDouble("balance"), rs.getString("type"));
                    }
                });
    }

    @Override
    public List<Transaction> getTransactions(int userid) {
        return financeConnection.query("select * from Transactions where userid=?",
                new Object[]{userid},
                new RowMapper<Transaction>() {
                    @Override
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Transaction(rs.getInt("transactionid"), rs.getInt("userid"), rs.getInt("accountid"), rs.getInt("categoryid"), rs.getDouble("amount"), rs.getDate("date"), rs.getString("name"), rs.getString("comment"));
                    }
                });
    }

    @Override
    public List<Category> getCategories(int userid) {
        return financeConnection.query("select * from Categories where userid=?",
                new Object[]{userid},
                new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Category(rs.getInt("categoryid"), rs.getInt("userid"), rs.getString("name"), rs.getDouble("budget"));
                    }
                });
    }

    @Override
    public List<Transaction> getAccountTransactions(int accountid) {
        return financeConnection.query("select * from Transactions where accountid=?",
                new Object[]{accountid},
                new RowMapper<Transaction>() {
                    @Override
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Transaction(rs.getInt("transactionid"), rs.getInt("userid"), rs.getInt("accountid"), rs.getInt("categoryid"), rs.getDouble("amount"), rs.getDate("date"), rs.getString("name"), rs.getString("comment"));
                    }
                });
    }

    @Override
    public List<Transaction> getCategoryTransactions(int categoryid) {
        return financeConnection.query("select * from Transactions where categoryid=?",
                new Object[]{categoryid},
                new RowMapper<Transaction>() {
                    @Override
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Transaction(rs.getInt("transactionid"), rs.getInt("userid"), rs.getInt("accountid"), rs.getInt("categoryid"), rs.getDouble("amount"), rs.getDate("date"), rs.getString("name"), rs.getString("comment"));
                    }
                });
    }

    @Override
    public void createAccount(Account account) {
        int accountId = financeConnection.queryForObject("select max(accountid) from Accounts ", Integer.class);
        account.setAccountid(accountId+1);
        financeConnection.update("insert into Accounts values(?,?,?,?,?)",
                account.getAccountid(), account.getUserid(), account.getName(), account.getBalance(), account.getType());
    }

    @Override
    public void createCategory(Category category) {
        int categoryId = financeConnection.queryForObject("select max(categoryid) from Categories ", Integer.class);
        category.setCategoryid(categoryId+1);
        financeConnection.update("insert into Categories values(?,?,?,?)",
                category.getCategoryid(), category.getUserid(), category.getName(), category.getBudget());
    }

    @Override
    public void makeTransaction(Transaction transaction) {
        int transactionId = financeConnection.queryForObject("select max(transactionid) from Transactions ", Integer.class);
        transaction.setTransactionid(transactionId+1);

        double accountBalance = getAccount(transaction.getAccountid()).getBalance();

        double categoryBudget = getCategory(transaction.getCategoryid()).getBudget();

        if (isTransactionPossible(transaction.getAmount(), accountBalance)) {
            financeConnection.update("insert into Transactions values(?,?,?,?,?,?,?,?)",
                    transaction.getTransactionid(), transaction.getUserid(), transaction.getAccountid(), transaction.getCategoryid(), transaction.getAmount(), transaction.getDate(), transaction.getName(), transaction.getComment());
            financeConnection.update("update Accounts set balance=? where accountid=?",
                    (accountBalance-transaction.getAmount()), transaction.getAccountid());
            financeConnection.update("update Categories set budget=? where categoryid=?",
                        enoughCategoryBudget(transaction.getAmount(), categoryBudget), transaction.getCategoryid());
        }

    }

    @Override
    public Account getAccount(int accountid) {
        return financeConnection.queryForObject("select * from Accounts where accountid=?",
                new Object[]{accountid},
                new RowMapper<Account>() {
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(rs.getInt("accountid"), rs.getInt("userid"), rs.getString("name"), rs.getDouble("balance"), rs.getString("type"));
                    }
                });
    }

    @Override
    public Category getCategory(int categoryid) {
        return financeConnection.queryForObject("select * from Categories where categoryid=?",
                new Object[]{categoryid},
                new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Category(rs.getInt("categoryid"), rs.getInt("userid"), rs.getString("name"), rs.getDouble("budget"));
                    }
                });
    }

    @Override
    public Transaction getTransaction(int transactionid) {
        return financeConnection.queryForObject("select * from Transactions where transactionid=?",
                new Object[]{transactionid},
                new RowMapper<Transaction>() {
                    @Override
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Transaction(rs.getInt("transactionid"), rs.getInt("userid"), rs.getInt("accountid"), rs.getInt("categoryid"), rs.getDouble("amount"), rs.getDate("date"), rs.getString("name"), rs.getString("comment"));
                    }
                });
    }

    @Override
    public double getAccountBalance(Account account) {
        return account.getBalance();
    }

    @Override
    public double getCategoryBudget(Category category) {
        return category.getBudget();
    }

    @Override
    public boolean isTransactionPossible(double amount, double balance) {
        return amount <= balance;
    }

    @Override
    public double enoughCategoryBudget(double amount, double budget) {
        if (amount <= budget) {
            return budget-amount;
        }
        else return 0;
    }

    @Override
    public void updateAccountBalance(int accountid, double newBalance) {
        financeConnection.update("update Accounts set balance = ? where accountid = ?", newBalance, accountid);
    }

    @Override
    public void updateCategoryBudget(int categoryid, double newBudget) {
        financeConnection.update("update Categories set budget = ? where categoryid = ?", newBudget, categoryid);
    }

    @Override
    public User login(String email, String password) {

        return financeConnection.queryForObject("select * from Users where email=? and password=?",
                new Object[]{email, password},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(rs.getInt("userid"), rs.getString("password"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
                    }
                });

    }

    @Override
    public User signUp(User user) {
        int userId = financeConnection.queryForObject("select max(userid) from Users ", Integer.class);
        int count = financeConnection.queryForObject("select count(*) from Users where email = ? ",
                new Object[]{user.getEmail()}, Integer.class);
        if(count == 0) {
            user.setUserid(userId + 1);
            financeConnection.update("insert into Users values(?,?,?,?,?)",
                    user.getUserid(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail());
        }
        return user;
    }


}
