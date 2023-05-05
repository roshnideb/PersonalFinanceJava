package org.asu.roshni.personalfinance.dao;

import org.asu.roshni.personalfinance.model.Account;
import org.asu.roshni.personalfinance.model.Category;
import org.asu.roshni.personalfinance.model.Transaction;
import org.asu.roshni.personalfinance.model.User;

import java.util.List;

public interface FinanceRepository {
    //get a user
    User getUser(String email);

    //get user's name
    String getUserName(String email);

    //get userid
    int getUserid(String email);

    //get user's accounts
    List<Account> getAccounts(int userid);

    //get user's transactions
    List<Transaction> getTransactions(int userid);

    //get user's categories
    List<Category> getCategories(int userid);

    //get user's transactions in a specific account
    List<Transaction> getAccountTransactions(int accountid);

    //get user's transactions in a specific category
    List<Transaction> getCategoryTransactions(int categoryid);

    //create an account
    void createAccount(Account account);

    //create a category
    void createCategory(Category category);

    //make a transaction
    void makeTransaction(Transaction transaction);

    //get details of one account
    Account getAccount(int accountid);

    //get details of one category
    Category getCategory(int categoryid);

    //get details of one transaction
    Transaction getTransaction(int transactionid);

    //get an account's balance
    double getAccountBalance(Account account);

    //get a category's budget
    double getCategoryBudget(Category category);

    //can the transaction be made
    boolean isTransactionPossible(double amount, double balance);

    //enough money in category budget
    double enoughCategoryBudget(double amount, double budget);


    //edit account
    void updateAccountBalance(int accountid, double newBalance);

    //edit category
    void updateCategoryBudget(int categoryid, double newBudget);

    //create account
    User login(String email, String password);

    //log in
    User signUp(User user);
}
