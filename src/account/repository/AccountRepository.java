package account.repository;

import java.util.ArrayList;
import java.util.Optional;

import account.model.Account;

public interface AccountRepository {
	// CRUD
	
	public Optional<Account> searchByNumber(int num);
	public ArrayList<Account> getList();
	public void register(Account  acc);
	public boolean update(Account acc);
	public boolean remove(Account acc);

	// Métodos Bancários
	
	public void draw(int numero, float valor);
	public void deposit(int numero, float valor);
	public void transfer(int numeroOrigem, int numeroDestino, float valor);
}
