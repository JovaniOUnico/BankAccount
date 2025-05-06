package account.repository;

import java.util.ArrayList;
import java.util.Optional;

import account.model.Account;

public interface AccountRepository {
	// CRUD
	
	public Optional<Account> searchByNumber(int num);
	public Optional<Account> searchByName(String name);
	public ArrayList<Account> getList();
	public void register(Account  acc);
	public boolean update(Account acc);
	public boolean remove(Account acc);

	// Métodos Bancários
	
	public boolean draw(int numero, float valor);
	public boolean deposit(int numero, float valor);
	public void transfer(int numeroOrigem, int numeroDestino, float valor);

}
