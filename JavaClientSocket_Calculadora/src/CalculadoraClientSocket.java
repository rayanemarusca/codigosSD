import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {

    public static void chamadaServidorSocket(int operador, double oper1, double oper2){
        String result="";
        try {

            //Conex�o com o Servidor
            //É feito uma conexão no servidor, o servidor está local por isso esse IP e está aguardando nessa porta 9090
            Socket clientSocket = new Socket("127.0.0.1", 9090);
            //Intancia um objeto do tipo stream para escrever no socket
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());

            //Enviando os dados
            //através dos parametros da função é possivel escrever no socket
            socketSaidaServer.writeBytes(operador+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            //Aqui ele envia a mensagem q foi escrita
            socketSaidaServer.flush();

            //Recebendo a resposta
            // É feito o instânciamento de um buffer para ler a respota do socket
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            //Essa é a mensagem de retorno do servidor
            result=messageFromServer.readLine();

            //Imprimir no log o resultado
            System.out.println("resultado="+result);
            //finaliza a conexão com o servidor socket
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
        //Chamadas a função que abrir uma conexão via socket com o servidor

        //Somar
        chamadaServidorSocket(1, 10, 20);
        //Subtração
        chamadaServidorSocket(2, 10, 20);
        //Divisão
        chamadaServidorSocket(3, 10, 20);
        //Multiplicação
        chamadaServidorSocket(4, 10, 20);
	}

}
