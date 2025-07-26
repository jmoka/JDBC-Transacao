package db.Exceptions;

public class DbException extends RuntimeException{
	
	// extendes a RuntimeException , exceçã que não precisa tratar 

	private static final long serialVersionUID = 1L;

	// metodo da exceção personalizada
	
	// Construtor da classe
	public DbException (String msg) { // recebe uma string
		super(msg); // passa para a super classe (RunTimeException)
	}
}
