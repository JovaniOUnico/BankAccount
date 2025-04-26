package account.model;

public class Account {

	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;
	
	public Account(int numero, int agencia, int tipo, String titular) {
		this.setNumero(numero);
		this.setAgencia(agencia);
		this.setTipo(tipo);
		this.setTitular(titular);
		this.setSaldo(0);
	}

	public float getSaldo() {
		return saldo;
	}

	private void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public boolean draw(float valor) {
		if (this.saldo < valor) {
			return false;
		}

		this.setSaldo(this.saldo - valor);

		return true;
	}

	public boolean deposit(float valor) {
		if (valor > 0) {
			this.setSaldo(this.saldo + valor);

			return true;
		}

		return false;
	}
}
