package test_dictionnaire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;

public class ListFieldName {


    ListFieldName(String filein, String fileout)
    {
       //fichier dont on récupère les champs
    	String pdfTemplate = filein;

        //nouveau pdfReader afin de lire le pdf
    	PdfReader pdfReader = null;
		try 
		{
			pdfReader = new PdfReader(pdfTemplate);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Erreur lors de l'ouverture du formulaire pdf");
			System.exit(2);
		}
		//creation d'un fichier xml
		File f = new File (fileout);
		//recupération des champs du pdf
        AcroFields fields = pdfReader.getAcroFields();
		Set<String> fldNames = fields.getFields().keySet();
		try
		{
			//on ecrit dans le fichier créer plus haut
		    PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));
		    //en tête xml
		    pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\t<xfdf xmlns=\"http://ns.adobe.com/xfdf/\" xml:space=\"preserve\">\n\t\t<fields>");
			for (String fldName : fldNames) 
	        {
				//nom du champ
				pw.println("\t\t\t<field name=\""+fldName+"\">" );
				switch (fields.getFieldType(fldName)) 
				{
					//récupération du type
			        case AcroFields.FIELD_TYPE_CHECKBOX:
			        	pw.println("\t\t<!--CheckBox-->");
			            break;
			        case AcroFields.FIELD_TYPE_COMBO:
			        	pw.println("\t\t<!--Combo-->");
			            break;
			        case AcroFields.FIELD_TYPE_LIST:
			        	pw.println("\t\t<!--Liste-->");
			            break;
			        case AcroFields.FIELD_TYPE_NONE:
			        	pw.println("\t\t<!--None-->");
			            break;
			        case AcroFields.FIELD_TYPE_PUSHBUTTON:
			        	pw.println("\t\t<!--PushButton-->");
			            break;
			        case AcroFields.FIELD_TYPE_RADIOBUTTON:
			        	pw.println("\t\t<!--RadioButton-->");
			            break;
			        case AcroFields.FIELD_TYPE_SIGNATURE:
			        	pw.println("\t\t<!--Signature-->");
			            break;
			        case AcroFields.FIELD_TYPE_TEXT:
			        	pw.println("\t\t<!--Text-->");
			            break;
		        }
				pw.println("\t\t\t\t<value> </value>");
				pw.println("\t\t\t</field>");
		      }
			pw.println("\t\t</fields>\n\t</xfdf>");
			//fermeture du fichier
			pw.close();
		}
        catch (IOException exception)
        {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
 }
