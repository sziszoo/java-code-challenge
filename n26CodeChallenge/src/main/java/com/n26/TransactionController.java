package com.n26;

import java.util.List;

import com.n26.model.SumAmount;
import com.n26.model.Transaction;
import com.n26.model.TransactionInterface;
import com.n26.model.TransactionList;
import com.n26.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaRepositories
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepository;

	//Save transactions from JSON input to DB 
	@RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.PUT)
	public Status putTransaction(
			@PathVariable("transaction_id") long transactionId,
			@RequestBody Transaction transaction) throws Exception {

		if (transactionRepository != null) {

			Transaction newTransaction = new Transaction(transactionId,
					transaction.getAmount(), transaction.getType(),
					transaction.getParentId());

			transactionRepository.save(newTransaction);
		} else {
			throw new NullPointerException(
					"Could not initialize transactionRepository.");
		}

		return new Status("ok");
	}

	//Get transaction details by id
	@RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.GET)
	public TransactionInterface getTransactionById(
			@PathVariable("transaction_id") long transactionId)
			throws Exception {

		Transaction transaction = null;

		if (transactionRepository != null) {
			transaction = transactionRepository
					.findByTransactionId(transactionId);

			if (transaction == null) {
				throw new Exception(
						"Could not find transaction with the given id.");
			}
		} else {
			throw new NullPointerException(
					"Could not initialize transactionRepository.");
		}
		return new TransactionInterface(transaction.getAmount(),
				transaction.getType(), transaction.getParentId());
	}

	//Retrieves  and lists transaction ids with the requested type
	@RequestMapping(value = "/transactionservice/types/{type}", method = RequestMethod.GET)
	public List<Long> getTransactionsByType(@PathVariable("type") String type)
			throws Exception {

		TransactionList list = null;

		if (transactionRepository != null) {

			list = new TransactionList();

			List<Transaction> transactions = transactionRepository
					.findByType(type);

			if (transactions != null && !transactions.isEmpty()) {
				for (Transaction transaction : transactions)
					list.addTransactionId(transaction.getTransactionId());
			} else {
				throw new Exception(
						"Could not find transactions with the given type.");
			}
		} else {
			throw new NullPointerException(
					"Could not initialize transactionRepository.");
		}
		return list.getTransactionIds();
	}

	//Get sum amount by parent transactionId
	@RequestMapping(value = "/transactionservice/sum/{transaction_id}", method = RequestMethod.GET)
	public SumAmount getLinkedTransactions(
			@PathVariable("transaction_id") long transactionId)
			throws Exception {

		double sum = 0;

		if (transactionRepository != null) {
			List<Transaction> transactions = transactionRepository
					.findByParentId(transactionId);

			if (transactions != null && !transactions.isEmpty()) {
				for (Transaction transaction : transactions) {
					sum += transaction.getAmount();
				}
			} else {
				throw new Exception(
						"Could not find transactions with the given parent id.");
			}
		} else {
			throw new NullPointerException(
					"Could not initialize transactionRepository.");
		}
		return new SumAmount(sum);
	}
}