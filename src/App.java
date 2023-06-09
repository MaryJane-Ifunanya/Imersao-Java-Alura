import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Key;
import java.util.List;
import java.util.Map;

import javax.swing.text.AttributeSet.ColorAttribute;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var reqruest = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(reqruest,BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
         List<Map<String, String>> listaDeFilmes = parser.parse(body);


         // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mTítulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mURL da imagem: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[1mClassificaçao: \u001b[m" + filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numEstrelas = (int) classificacao;
            for(int n =1; n <= numEstrelas; n++ ){
                System.out.print("⭐");
            }
            System.out.println();
        
             

       

            
        }
    }
}
