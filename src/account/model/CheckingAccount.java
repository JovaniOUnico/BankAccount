package account.model;

//Conta corrente
public class CheckingAccount extends Account {

	private float limit;

	public CheckingAccount(int numero, int agencia, String titular, float limit) {
		super(numero, agencia, titular);
		this.setLimite(limit);
	}

	public float getLimit() {		
		// Atualiza o limite caso o saldo seja negativo

		if(this.getSaldo() < 0)
			return this.limit - Math.abs(this.getSaldo());

		return limit;
	}

	public void setLimite(float limit) {
		this.limit = limit;
	}

	@Override
	public boolean draw(float value) {

		if (this.getSaldo() + this.getLimit() < value) {
			return false;
		}

		this.setSaldo(this.getSaldo() - value);
		
		return true;
	}

}
