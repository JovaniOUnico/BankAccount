package account.model;

public class Account {

	private int numero;
	private int agencia;
	private String titular;
	private float saldo;
	
	public Account(int num, int agencia, String titular) {
		this.setNumero(num);
		this.setAgencia(agencia);
		this.setTitular(titular);
		this.setSaldo(0);
	}

	public float getSaldo() {
		return saldo;
	}

	protected void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	// Métodos Bancários
	// saque
	public boolean draw(float valor) {
		if (this.saldo < valor) {
			return false;
		}

		this.setSaldo(this.saldo - valor);

		return true;
	}

	//deposito
	public boolean deposit(float valor) {
		if (valor > 0) {
			this.setSaldo(this.saldo + valor);

			return true;
		}

		return false;
	}
}
