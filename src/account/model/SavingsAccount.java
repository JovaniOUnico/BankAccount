package account.model;

//conta poupança
public class SavingsAccount extends Account {

	private String aniversary;

	public SavingsAccount(int numero, int agencia, String titular, String aniversary) {
		super(numero, agencia, titular);
		this.setAniversary(aniversary);
	}

	public String getAniversary() {
		return aniversary;
	}

	public void setAniversary(String aniversary) {
		this.aniversary = aniversary;
	}
	
}
