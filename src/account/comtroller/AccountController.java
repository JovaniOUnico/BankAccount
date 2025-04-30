package account.comtroller;

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

		if (acc.isPresent())
			return Optional.ofNullable(acc.get());
		else
			return null;
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

	@Override
	public void draw(int numero, float valor) {
	    Optional<Account> acc = this.searchByNumber(numero);

	    if (acc.isPresent()) {
	        try {
	            acc.get().draw(valor);
	        } catch (IllegalArgumentException ex) {
	            // Captura exceções específicas do método deposit, se houver
	            System.err.println("Erro ao depositar na conta " + numero + ": " + ex.getMessage());
	            throw ex; // Relança a exceção para o chamador saber do problema
	        }
	    }

	}

	@Override
	public void deposit(int numero, float valor) {
	    Optional<Account> acc = this.searchByNumber(numero);

	    if (acc.isPresent()) {
	        try {
	            acc.get().deposit(valor);
	        } catch (IllegalArgumentException ex) {
	            // Captura exceções específicas do método deposit, se houver
	            System.err.println("Erro ao depositar na conta " + numero + ": " + ex.getMessage());
	            throw ex; // Relança a exceção para o chamador saber do problema
	        }
	    }

	}

	@Override
	public void transfer(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub

	}

	/* Métodos Auxiliares */

	public int getNumId() {
		return ++num;
	}

	public Optional<Account> searchOnCollection(int numero) {

		for (var aux : listaContas) {
			if (aux.getNumero() == numero)
				return Optional.of(aux);
		}

		return Optional.empty();
	}
}
