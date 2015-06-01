package test_dictionnaire;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		ListFieldName list = null;
		if (args.length >= 2)
			list = new ListFieldName(args[0], args[1]);
		else
		{
			System.out.println("Erreur, Veuillez saisir le nom du formulaire pdf et le nom du fichier de sorti");
			System.exit(1);
		}	
	}

}
