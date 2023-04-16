package org.asu.roshni.personalfinance.controller;

import org.asu.roshni.personalfinance.dao.FinanceRepository;
import org.asu.roshni.personalfinance.model.Account;
import org.asu.roshni.personalfinance.model.Category;
import org.asu.roshni.personalfinance.model.Transaction;
import org.asu.roshni.personalfinance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    FinanceRepository financeRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    //get user's name
    @GetMapping("/user/name/{email}")
    public ResponseEntity<String> getUserName(@PathVariable String email) {
        String name = financeRepository.getUserName(email);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    //get userid
    @GetMapping("/user/id/{email}")
    public ResponseEntity<Integer> getUserid(@PathVariable String email) {
        int id = financeRepository.getUserid(email);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //get user's accounts
    @GetMapping("/{userid}/accounts")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable int userid) {
        List<Account> accounts = financeRepository.getAccounts(userid);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    //get user's transactions
    @GetMapping("/users/{userid}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int userid) {
        List<Transaction> transactions = financeRepository.getTransactions(userid);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    //get user's categories
    @GetMapping("/{userid}/categories")
    public  ResponseEntity<List<Category>> getCategories(@PathVariable int userid) {
        List<Category> categories = financeRepository.getCategories(userid);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //get user's transactions in a specific account
    @GetMapping("/accounts/{accountid}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable int accountid) {
        List<Transaction> transactions = financeRepository.getAccountTransactions(accountid);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    //get user's transactions in a specific category
    @GetMapping("/categories/{categoryid}/transactions")
    public ResponseEntity<List<Transaction>> getCategoryTransactions(@PathVariable int categoryid) {
        List<Transaction> transactions = financeRepository.getCategoryTransactions(categoryid);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    //create an account
    @PostMapping("/newaccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        financeRepository.createAccount(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    //create a category
    @PostMapping("/newcategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        financeRepository.createCategory(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //make a transaction
    @PostMapping("/newtransaction")
    public ResponseEntity<Transaction> makeTransaction(@RequestBody Transaction transaction) {
        financeRepository.makeTransaction(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    //get details of one account
    @GetMapping("/accounts/{accountid}")
    public ResponseEntity<Account> getOneAccount(@PathVariable int accountid) {
        Account account = financeRepository.getAccount(accountid);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    //get details of one transaction
    @GetMapping("/transactions/{transactionid}")
    public ResponseEntity<Transaction> getOneTransaction(@PathVariable int transactionid) {
        Transaction transaction = financeRepository.getTransaction(transactionid);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    //get details of one category
    @GetMapping("/categories/{categoryid}")
    public ResponseEntity<Category> getOneCategory(@PathVariable int categoryid) {
        Category category = financeRepository.getCategory(categoryid);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //edit account

    //edit category

    //create account

    //log in

}
