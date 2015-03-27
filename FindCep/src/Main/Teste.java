package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import Models.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Teste {

	public static void main(String[] args) throws IOException {
		try {

			URL url = new URL("http://correiosapi.apphb.com/cep/67020590");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				System.out.println(conn.getResponseCode());
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream()), "UTF-8"));

			String output = br.readLine();

			GsonBuilder builder = new GsonBuilder();
			Gson g = builder.create();
			Endereco endereco = g.fromJson(output, Endereco.class);

			System.out.println(endereco.getTipoDeLogradouro() + " "
					+ endereco.getLogradouro() + " " + endereco.getCidade()
					+ "-" + endereco.getEstado());

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
