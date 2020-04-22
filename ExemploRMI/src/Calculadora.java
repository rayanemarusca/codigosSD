import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora {

	//Lado do servidor onde foi implementado os metodos da ICalculadora
	private static final long serialVersionUID = 1L;

	//variavel responvel por contabilizar chamadas ao servidor
	private static int chamadas = 0;

	//Metodo soma da calculadora, perceba que sempre que o cliente chamar esse metodo ele vai aparecer uma mensagem do console dizendo a quatidades de chamadas feitas ao servidor.
	public int soma(int a, int b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a + b;
	}

	//Metodo subtrair da calculadora, perceba que sempre que o cliente chamar esse metodo ele vai aparecer uma mensagem do console dizendo a quatidades de chamadas feitas ao servidor.
	public int subtrair(int a, int b) throws RemoteException {
		System.out.println("Método subtrair chamado " + chamadas++);
		return a - b;
	}

	//Metodo dividir da calculadora, perceba que sempre que o cliente chamar esse metodo ele vai aparecer uma mensagem do console dizendo a quatidades de chamadas feitas ao servidor.
	public int dividir(int a, int b) throws RemoteException {
		System.out.println("Método dividir chamado " + chamadas++);
		return a / b;
	}

	//Metodo multiplicar da calculadora, perceba que sempre que o cliente chamar esse metodo ele vai aparecer uma mensagem do console dizendo a quatidades de chamadas feitas ao servidor.
	public int multiplicar(int a, int b) throws RemoteException {
		System.out.println("Método multiplicar chamado " + chamadas++);
		return a * b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		// Instancia classe calculadora
		Calculadora calculadora = new Calculadora();
		//Cria a variavel Reg para guardar as respostas aos metodos
		Registry reg = null;

		//nessa linha é feito o export do objeto calculadora
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			//Guarda o registro nessa porta que vai ser acessada no lado do cliente.
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				//nesse tratamento de erro ele tenta novamente guardar o registro nessa porta.
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				//Caso der erro novamente, ele para a chamada sem inserir o registro.
				System.exit(0);
			}
		}


		reg.rebind("calculadora", stub);
	}
}
