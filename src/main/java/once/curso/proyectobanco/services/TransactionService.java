package once.curso.proyectobanco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

import once.curso.proyectobanco.entities.CurrentAccount;

import once.curso.proyectobanco.dtos.TransactionDto;

import once.curso.proyectobanco.entities.Transaction;
import once.curso.proyectobanco.repositories.CurrentAccountCRUDRepository;
import once.curso.proyectobanco.repositories.DescriptionCRUDRepository;
import once.curso.proyectobanco.repositories.TransactionCRUDRepository;

@Data
@Service

//fecha
public class TransactionService {

	@Autowired
	private CurrentAccountCRUDRepository currentAccountCRUDRepository;
	@Autowired
	private DescriptionCRUDRepository DescriptionCRUDRepository;

	@Autowired
	private TransactionCRUDRepository transactionCRUDRepository;

	public <S extends Transaction> S save(S entity) {
		return getTransactionCRUDRepository().save(entity);
	}

	public <S extends Transaction> Iterable<S> saveAll(Iterable<S> entities) {
		return getTransactionCRUDRepository().saveAll(entities);
	}

	public Optional<Transaction> findById(Integer id) {
		return getTransactionCRUDRepository().findById(id);
	}

	public List<Transaction> getTransactionsByCurrentAccount(CurrentAccount number) {
		return getTransactionCRUDRepository().getTransactionsByCurrentAccount(number);
	}

	@Transactional
	public void realizarTransaccion(TransactionDto transactionDto) {
		getTransactionCRUDRepository()
				.save(new Transaction(0, transactionDto.getDate(), transactionDto.getCurrent() * -1,
						getDescriptionCRUDRepository().findById(transactionDto.getDescription()).get(),
						getCurrentAccountCRUDRepository().findById(transactionDto.getCurrentAccountOrigen()).get()));
		
		
		getTransactionCRUDRepository()
		.save(new Transaction(0, transactionDto.getDate(), transactionDto.getCurrent(),
				getDescriptionCRUDRepository().findById(transactionDto.getDescription()).get(),
				getCurrentAccountCRUDRepository().findById(transactionDto.getCurrentAccountDestino()).get()));
	}

	public boolean existsById(Integer id) {
		return getTransactionCRUDRepository().existsById(id);
	}

	public Iterable<Transaction> findAll() {
		return getTransactionCRUDRepository().findAll();
	}

	public Page<Transaction> findAll(Pageable pageable) {
		return getTransactionCRUDRepository().findAll(pageable);
	}

	public Iterable<Transaction> findAllById(Iterable<Integer> ids) {
		return getTransactionCRUDRepository().findAllById(ids);
	}

	public long count() {
		return getTransactionCRUDRepository().count();
	}

	public void deleteById(Integer id) {
		getTransactionCRUDRepository().deleteById(id);
	}

	public void delete(Transaction entity) {
		getTransactionCRUDRepository().delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		getTransactionCRUDRepository().deleteAllById(ids);
	}

	public void deleteAll(Iterable<? extends Transaction> entities) {
		getTransactionCRUDRepository().deleteAll(entities);
	}

	public void deleteAll() {
		getTransactionCRUDRepository().deleteAll();
	}

	public int getBalance(int accountNumber) {
		return getTransactionCRUDRepository().getBalance(accountNumber);
	}

	

}