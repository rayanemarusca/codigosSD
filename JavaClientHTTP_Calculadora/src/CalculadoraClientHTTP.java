import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

    public static void servidorPHP(String parametros) {
        String result="";
        try {

            //Instancia da classe url com a url do servidor PHP
            URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");

            //Parametros para realizar a conexão com o servidor, tempo de timeout da leitura e da conexão, utiliza o metodo post para fazer a requisição e permite enviar e receber dados
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true) ;

            //ENVIO DOS PARAMETROS

            //Feita essa primeira conexão é recebido um stream
            OutputStream os = conn.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            //Aqui ele escreve os parametros na chamada ao servidor, esse metodos vão ser referente a calculadora
            writer.write(parametros); //1-somar 2-subtrair 3-dividir 4-multiplicar
            //Enviar os parametros
            writer.flush();
            writer.close();
            // o stream é fechado
            os.close();

            //Aqui é tratado a resposta do servidor PHP
            int responseCode=conn.getResponseCode();
            //Primeiro ele verifica se a resposta é 200 e está ok
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                //RECBIMENTO DOS PARAMETROS
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                //Transforma a resposta o resultado em string
                result = response.toString();

                //E imprimi no console
                System.out.println("Resposta do Servidor PHP="+result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //4 chamadas a função servidorPHP que tem a intenção em cada chamada acessar ao servidor.
        servidorPHP("oper1=3&oper2=2&operacao=1");
        servidorPHP("oper1=3&oper2=2&operacao=2");
        servidorPHP("oper1=3&oper2=3&operacao=3");
        servidorPHP("oper1=3&oper2=2&operacao=4");
    }

}