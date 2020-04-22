import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalculadoraCliente {
	// Calculadora de exemplo RMI
	// Lado do Cliente
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;		
		try {
			//Se conectar nessa porta 1099 para acessar as funções da calculadora.
			reg = LocateRegistry.getRegistry(1099);

			//Funçoes da calculadora, acessar atraves de uma interface
			calc = (ICalculadora) reg.lookup("calculadora");

			//Chamada ao metodo de soma
			System.out.println(calc.soma(3,2));

			//Chamada ao metodo de subtrair
			System.out.println(calc.subtrair(3,2));

			//Chamada ao metodo de dividir
			System.out.println(calc.dividir(3,3));

			//Chamada ao metodo de multiplicar
			System.out.println(calc.multiplicar(3,2));
		} catch (RemoteException | NotBoundException e) {
				//Tratamento de erros
				System.out.println(e);
				System.exit(0);
		}
	}		

}
