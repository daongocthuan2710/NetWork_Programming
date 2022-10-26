package Week5.Practise1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;   

public class Dictionary {
    private Map<String,String> data = new TreeMap<String,String>();
    private static final String FILE_CONFIG = "dictionary.properties";
    
    public void readFileDictionary()
    {
        Properties prop = new Properties();
        InputStream input = null;
        try {
			input = LoadPropertiesFromClasspath.class.getClassLoader().getResourceAsStream(FILE_CONFIG);
			if (input == null) {
				System.out.println("Sorry, unable to find " + FILE_CONFIG);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
            
			// get the property value and add to Dictionary
            prop.entrySet().forEach(e -> this.putNewVocabulary(e.getKey().toString(),e.getValue().toString()));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }

    public void writeFileDictionary(String vocab, String value)
    {
        Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(FILE_CONFIG);
			// set the properties value
            for (Map.Entry<String, String> entry : data.entrySet()) {
                prop.setProperty(entry.getKey().toString(), entry.getValue().toString());
            }
            // Add new vocabulary
			prop.setProperty(vocab, value);

			// save properties to project root folder
			prop.store(output, null);

            this.readFileDictionary();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }

    public String putNewVocabulary(String vocab, String meaning)
    {
        return this.data.put(vocab,meaning);
    }

    public String removeVocabulary(String vocab)
    {
        return this.data.remove(vocab);
    }

    public String lookupVocabulary(String vocab)
    {
        String meaning = this.data.get(vocab);
        return meaning;
    }

    public String lookupVocabularyFromMeaning(String meaning)
    {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if(entry.getValue().toString().equals(meaning)){
                return entry.getKey().toString();
            }
        }
        return "No vocabulary having meaning like this";
    }

    public void printListVocabulary()
    {
        Set<String> vocabSet = this.data.keySet();
        System.out.println(Arrays.toString(vocabSet.toArray()));
    }

    public int printNumberOfVocab()
    {
        return this.data.size();
    }

    public void deleteAll()
    {
        this.data.clear();
    }

    public void active(){
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("-------------------");
            System.out.println("Look up dictionary ENG - VIE");
            System.out.println("MENU");
            System.out.println("1. Add new vocabulary");
            System.out.println("2. Remove vocabualary");
            System.out.println("3. Look up vocabulary");
            System.out.println("4. Print vocabulary list");
            System.out.println("5. Print the number of vocabularys");
            System.out.println("6. Remove vocabulary list");
            System.out.println("7. Look up vocabulary from meaning");
            
            System.out.print("Enter your option: ");
            option = sc.nextInt();
            sc.nextLine();
            switch(option) {
                case 1:
                {
                    System.out.println("Enter your vocabulary that you want to add");
                    String vocab = sc.nextLine();
                    System.out.println("Enter your meaning of this vocabulary");
                    String meaning = sc.nextLine();
                    this.writeFileDictionary(vocab,meaning);
                    System.out.println("Add vocabulary successful");
                }
                  break;
                case 2:
                {
                    System.out.println("Enter your vocabulary that you want to remove");
                    String vocab = sc.nextLine();
                    this.removeVocabulary(vocab);
                    System.out.println("Remove successful");
                }
                  break;
                case 3:
                {
                    System.out.println("Enter your vocabulary that you want to look up");
                    String vocab = sc.nextLine();
                    System.out.println("The meaning of " + vocab + " is: ");
                    System.out.println(this.lookupVocabulary(vocab));
                }
                  break;
                case 4:
                {
                    System.out.println("Your vocabulary list is: ");
                    this.printListVocabulary();
                }
                  break;
                case 5:
                {
                    System.out.println(" The size of vocabulary list is: ");
                    System.out.println(this.printNumberOfVocab());
                }
                  break;
                case 6:
                {
                    System.out.println("Remove your vocabulary list");
                    this.deleteAll();
                    System.out.println("Remove successful");
                }
                case 7:
                {
                    System.out.print("Enter your meaning that you want to look up: ");
                    String meaning = sc.nextLine();
                    System.out.print("Corresponding vocabulary: ");
                    System.out.println(this.lookupVocabularyFromMeaning(meaning));
                }
                  break;
                default:
                  // code block
              }
        }
        while(option != 0);
        sc.close();
    }     
}


