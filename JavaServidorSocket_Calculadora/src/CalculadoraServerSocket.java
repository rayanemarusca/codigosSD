import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {
    public CalculadoraServerSocket() {
    }

    public static void main(String[] args) {
        //Intancia a classe calculadora para utilizar os metodos
        Calculadora calc = new Calculadora();
        int i = 0;
        try {
            //Definir a porta do socket do servidor e o endereço IP é local
            ServerSocket welcomeSocket = new ServerSocket(9090);

            //Imprimi uma mensagem no console dizendo que o servidor está esperando qualquer requisição
            System.out.println("Servidor no ar");

            //Loop para aguardar as requisições dos clientes
            while(true) {

                //Veirifica se existe mensagem na porta do socket e caso tenha ela é recebida
                Socket connectionSocket = welcomeSocket.accept();
                ++i;
                //Sinaliza que houve uma conexão
                System.out.println("Nova conexão");

                //É instanciado um buffer de leitura da mensagem do cliente socket
                BufferedReader socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                //Mensam recebida por linha
                String operacao = socketEntrada.readLine();
                String oper1 = socketEntrada.readLine();
                String oper2 = socketEntrada.readLine();
                String result = "";

                //Laço para verificar a operação que o cliente deseja
                switch (Integer.parseInt(operacao)) {
                    case 1:
                        // Chamada ao metodo de soma da calculadora
                        result = "" + calc.soma(Double.parseDouble(oper1), Double.parseDouble(oper2));
                        break;
                    case 2:
                        // Chamada ao metodo de subtrair da calculadora
                        result = "" + calc.subtrair(Double.parseDouble(oper1), Double.parseDouble(oper2));
                        break;
                    case 3:
                        // Chamada ao metodo de dividir da calculadora
                        result = "" + calc.dividir(Double.parseDouble(oper1), Double.parseDouble(oper2));
                        break;
                    case 4:
                        // Chamada ao metodo de multiplicar da calculadora
                        result = "" + calc.multiplicar(Double.parseDouble(oper1), Double.parseDouble(oper2));
                        break;
                    default:
                        result = "";
                }

                //Intancia um metodo stream para enviar uma resposta ao servidor
                DataOutputStream socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
                //Escreve a resposta
                socketOutput.writeBytes(result + '\n');
                //Imprimir no console a resposta enviada
                System.out.println(result);
                //Enviar a mensagem
                socketOutput.flush();
                //E finaliza a conexñao com o cliente
                socketOutput.close();
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }
    }
}
