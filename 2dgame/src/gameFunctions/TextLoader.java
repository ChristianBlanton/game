package gameFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class TextLoader {

	public static String load(String path) {

	    String stringao = "";
        String read = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(TextLoader.class.getResourceAsStream(path)))) {

            while (((read = br.readLine()) != null)) {
                stringao += read;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("reading text error");
            System.exit(0);
        }

        return stringao;
    }

    public static String loadFromDisk(File file) {

        String stringao = "";
        String read = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while (((read = br.readLine()) != null)) {
                stringao += read;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("reading text error");
            System.exit(0);
        }

        return stringao;
    }
}