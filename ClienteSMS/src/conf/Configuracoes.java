package conf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Configuracoes {

	private String filename = "config.properties";
	private InputStream is;
	private OutputStream os;
	private File file;

	public Configuracoes() throws FileNotFoundException {
		file = new File(filename);
		CriarProp();
		is = new FileInputStream(file);
		os = new FileOutputStream(file);

	}

	public Properties GetProp() {
		try {
			CriarProp();
			Properties props = new Properties();
			if (is != null) {
				props.load(is);
				return props;
			} else {
				throw new FileNotFoundException(filename + " não encontrado");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}

	}

	public void SalvarProp(Properties prop) {
		try {
			CriarProp();
			prop.store(os, null);
			os.flush();
			os.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	private void CriarProp() {
		try {

			if (!file.exists()) {
				String properties = "urlrest=\nuser=\npass=";
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
				bw.write(properties);
				bw.flush();
				bw.close();

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		Configuracoes c = new Configuracoes();
		c.CriarProp();
	}

}
