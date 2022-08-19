import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
public class Controller implements ActionListener, ListSelectionListener {
	private Viewer viewer;

	public Controller(Viewer viewer) {
		this.viewer = viewer;
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Open_File")) {
			openFile();

		}else if (command.equals("Print_Document")) {
			printMyDocument();
		}
		else if (command.equals("Save_File")) {
			saveFile();
		} else if (command.equals("Close_Program")) {
			closeProgram();

		} else if (command.equals("Cut_Text") || command.equals("Copy_Text")
				|| command.equals("Paste_Text") || command.equals("Delete_Text")) {
			viewer.doAction(command);
		} else if (command.equals("Font_Select")) {
			viewer.fontSelect();

		} else if (command.equals("Change_Font")) {
			Font font = new Font("Impact", Font.PLAIN, 72);
			viewer.changeFont(font);

		} else if (command.equals("Close_Window")) {
			viewer.closeDialog();
		} else if (command.equals("Find_Text")) {
			viewer.openFindDialog();
		}
		else if (command.equals("Close_Find_Dialog")) {
			viewer.closeFindDialog();
		}else if (command.equals("Find_Text_From_Textarea")) {
			String text = viewer.getFindText();
			viewer.findtextInTextArea(text);
		}
	}

	private void printMyDocument() {
		String text = viewer.getTextFromTextArea();
		PrinterJob job = PrinterJob.getPrinterJob();
		DocumentPrintable printdocument = new DocumentPrintable(text);
		job.setPrintable(printdocument );
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
				javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),
						"The document has been printed!");
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}
	private void closeProgram() {
		System.exit(0);
	}

	private void saveFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				FileOutputStream out = new FileOutputStream(file);
				String textFoSaving = viewer.getTextFromTextArea();
				for (int i = 0; i < textFoSaving.length(); i++) {
					int unicode = textFoSaving.charAt(i);
					out.write(unicode);
				}
				out.flush();
				out.close();
			} catch (IOException ioe) {
				System.out.print(ioe);
			}
		}
	}
	private void openFile() {
		String textFromFile = "";
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			try {
				FileInputStream in = new FileInputStream(file);

				int unicode;
				while ((unicode = in.read()) != -1) {
					char symbol = (char) unicode;
					textFromFile = textFromFile + symbol;
				}
				in.close();
			} catch (IOException ioe) {
				System.out.print(ioe);
			}
		}
		viewer.update(textFromFile);
	}
	@Override
	public void valueChanged(ListSelectionEvent event) {
		javax.swing.JList list = (javax.swing.JList) event.getSource();
		String nameFont = (String) list.getSelectedValue();
		String styleFont = (String) list.getSelectedValue();
		System.out.println(styleFont);
		String sizeFont = (String) list.getSelectedValue();

		String nameList = list.getName();
		if (nameList.equals("StyleNameChange")) {
			viewer.styleSelectedText(styleFont);
		} else if (nameList.equals("FontNameChange")) {
			viewer.fontSelectedText(nameFont);
		} else if (nameList.equals("SizeChangeName")) {
			viewer.sizeSelectedText(sizeFont);
		}
	}
}
