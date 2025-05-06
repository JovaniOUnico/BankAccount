package account.controller;

import java.util.ArrayList;
import java.util.Optional;

import account.model.Account;
import account.repository.AccountRepository;

public class AccountController implements AccountRepository {

	// Criar a Collection
	private ArrayList<Account> listaContas = new ArrayList<Account>();

	// Variável para receber o numero da Conta
	int num = 0;

	@Override
	public Optional<Account> searchByNumber(int numero) {

		Optional<Account> acc = searchOnCollection(numero);

		return acc;
	}
	
	@Override
	public Optional<Account> searchByName(String name) {

		Optional<Account> acc = searchOnCollection(name);

		return acc;
	}

	@Override
	public ArrayList<Account> getList() {
		return listaContas;
	}

	@Override
	public void register(Account conta) {
		listaContas.add(conta);
	}

	@Override
	public boolean update(Account conta) {
		
		Optional<Account> buscaConta = searchOnCollection(conta.getNumero());

		if (buscaConta.isPresent()) {
			listaContas.set(listaContas.indexOf(buscaConta.get()), conta);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean remove(Account acc) {

		Optional<Account> conta = searchOnCollection(acc.getNumero());

		if (conta.isPresent()) {
			if (listaContas.remove(conta.get()) == true)
				return true;
		}
		
		return false;

	}

	/* Saque*/
	@Override
	public boolean draw(int numero, float valor) {
	    Optional<Account> acc = this.searchByNumber(numero);

	    if (acc.isPresent()) {
	        try {
	            return acc.get().draw(valor);
	        } catch (IllegalArgumentException ex) {
	            // Captura exceções específicas do método deposit, se houver
	            System.err.println("Erro ao depositar na conta " + numero + ": " + ex.getMessage());
	            throw ex; // Relança a exceção para o chamador saber do problema
	        }
	    }
	    
	    return false;

	}

	/* Deposito*/
	@Override
	public boolean deposit(int numero, float valor) {
	    Optional<Account> acc = this.searchByNumber(numero);

	    if (acc.isPresent()) {
	        try {
	            return acc.get().deposit(valor);
	        } catch (IllegalArgumentException ex) {
	            // Captura exceções específicas do método deposit, se houver
	            System.err.println("Erro ao depositar na conta " + numero + ": " + ex.getMessage());
	            throw ex; // Relança a exceção para o chamador saber do problema
	        }
	    }
	    
	    return false;

	}

	@Override
	public void transfer(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub

	}

	/* Métodos Auxiliares */

	public int getNumId() {
		return ++num;
	}

	/* Buscas por numero e nome*/
	private Optional<Account> searchOnCollection(int numero) {

		for (var aux : listaContas) {
			if (aux.getNumero() == numero)
				return Optional.of(aux);
		}

		return Optional.empty();
	}
	
	private Optional<Account> searchOnCollection(String name) {

		for (var aux : listaContas) {
			if (aux.getTitular().equalsIgnoreCase(name))
				return Optional.of(aux);
		}

		return Optional.empty();
	}
}
