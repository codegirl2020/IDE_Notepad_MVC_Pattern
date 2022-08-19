import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Viewer {

    private JTextArea textArea;
	private JFrame frame;
    private Controller controller;
    private JDialog dialogFont;
    private JTextField textFieldFontName;
    private JTextField textFieldStyleName;
    private JTextField textFieldSizeName;
    private JDialog dialogFind;
    private JTextField textFieldFindText;

    public Viewer() {
        controller = new Controller(this);

        Font fontForTextArea = new Font("Arial", Font.BOLD, 25);
        textArea = new JTextArea();
        textArea.setFont(fontForTextArea);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JMenuItem newMenuItem = new JMenuItem("New", new ImageIcon("images/new.gif"));
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem openMenuItem = new JMenuItem("Open ...", new ImageIcon("images/open.gif"));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openMenuItem.addActionListener(controller);
        openMenuItem.setActionCommand("Open_File");


        JMenuItem saveMenuItem = new JMenuItem("Save",
                new ImageIcon("images/save.gif"));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveMenuItem.addActionListener(controller);
        saveMenuItem.setActionCommand("Save_File");


        JMenuItem saveAsMenuItem = new JMenuItem("Save As ...",
                new ImageIcon("images/save_as.gif"));

        JMenuItem printDocumentMenuItem = new JMenuItem("Print Document ...",
                new ImageIcon("images/print.gif"));
        printDocumentMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        printDocumentMenuItem.addActionListener(controller);
        printDocumentMenuItem.setActionCommand("Print_Document");


        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(controller);
        exitMenuItem.setActionCommand("Close_Program");


        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        menuFile.add(newMenuItem);
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);
        menuFile.add(new JSeparator());
        menuFile.add(printDocumentMenuItem);
        menuFile.add(new JSeparator());
        menuFile.add(exitMenuItem);


        JMenuItem cutMenuItem = new JMenuItem("Cut",
                new ImageIcon("images/cut.gif"));
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutMenuItem.addActionListener(controller);
        cutMenuItem.setActionCommand("Cut_Text");

        JMenuItem copyMenuItem = new JMenuItem("Copy",
                new ImageIcon("images/copy.gif"));
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyMenuItem.addActionListener(controller);
        copyMenuItem.setActionCommand("Copy_Text");

        JMenuItem pasteMenuItem = new JMenuItem("Paste",
                new ImageIcon("images/past.gif"));
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteMenuItem.addActionListener(controller);
        pasteMenuItem.setActionCommand("Paste_Text");

        JMenuItem clearMenuItem = new JMenuItem("Clear",
                new ImageIcon("images/delit.gif"));
        clearMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, 0));
        clearMenuItem.addActionListener(controller);
        clearMenuItem.setActionCommand("Delete_Text");

        JMenuItem findMenuItem = new JMenuItem("Find",new ImageIcon("images/find.gif"));
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        findMenuItem.addActionListener(controller);
        findMenuItem.setActionCommand("Find_Text");
        JMenuItem find_moreMenuItem = new JMenuItem("Find more...");
        find_moreMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F3, 0));
        JMenuItem goMenuItem = new JMenuItem("Go",new ImageIcon("images/go.gif"));
        goMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        JMenuItem marker_allMenuItem = new JMenuItem("MarkerAll",new ImageIcon("images/marker.gif"));
        marker_allMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        JMenuItem time_and_date_MenuItem = new JMenuItem("Time and date",new ImageIcon("images/time.gif"));
        time_and_date_MenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, 0));

        JMenu menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic('E');
        menuEdit.add(cutMenuItem);
        menuEdit.add(copyMenuItem);
        menuEdit.add(pasteMenuItem);
        menuEdit.add(clearMenuItem);
        menuEdit.add(new JSeparator());
        menuEdit.add(findMenuItem);
        menuEdit.add(find_moreMenuItem);
        menuEdit.add(goMenuItem);
        menuFile.add(new JSeparator());
        menuEdit.add(marker_allMenuItem);
        menuEdit.add( time_and_date_MenuItem);

        JMenuItem wordSpaceMenuItem = new JMenuItem("Word Space",
				new ImageIcon("images/wordSpace.gif"));
		JMenuItem fontMenuItem = new JMenuItem("Font",
				new ImageIcon("images/font.gif"));
		fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		fontMenuItem.addActionListener(controller);
		fontMenuItem.setActionCommand("Font_Select");

		JMenu menuFormat = new JMenu("Format");
		menuFormat.add(wordSpaceMenuItem);
		menuFormat.add(fontMenuItem);


        JMenuItem statusSpaceMenuItem = new JMenuItem("Status space", new ImageIcon("images/marker.gif"));
        JMenu menuView = new JMenu("View");
        menuView.add(statusSpaceMenuItem);


        JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
        viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(viewHelpMenuItem);
        menuHelp.add(aboutMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuView);
        menuBar.add(menuHelp);


        frame = new JFrame("Notepad MVC Pattern");
        frame.setSize(500, 500);
        frame.setLocation(300, 50);
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void update(String text) {
        textArea.setText(text);
    }

    public String getTextFromTextArea() {
        return textArea.getText();
    }

    public void doAction(String command) {
        switch (command) {
            case "Cut_Text":
                textArea.cut();
                break;
            case "Copy_Text":
                textArea.copy();
                break;
            case "Paste_Text":
                textArea.paste();
                break;
            case "Delete_Text":
                textArea.replaceSelection("");
                break;
            default:
        }
    }

    public void fontSelectedText(String fontName) {
        textFieldFontName.setText(fontName);
    }
    public void styleSelectedText(String styleName) {
        textFieldStyleName.setText(styleName);
    }
    public void sizeSelectedText(String sizeName){ textFieldSizeName.setText(sizeName);

    }

	public void fontSelect() {

        if(dialogFont == null){

            //labelSizeName
            JLabel labelSizeName = new JLabel("Size:");
            labelSizeName.setBounds(460, 50, 70, 50);
            textFieldSizeName = new JTextField();
            textFieldSizeName.setBounds(460, 110, 70, 50);
            String[] nameSizeName = {"10", "12", "14", "16", "18",
                    "1","2","3","4","5","6","7"};
            JList listSizeName = new JList(nameSizeName);
            listSizeName.setName("SizeChangeName");
            listSizeName.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listSizeName.setLayoutOrientation(JList.VERTICAL);
            listSizeName.setVisibleRowCount(-1);
            listSizeName.addListSelectionListener(controller);

            JScrollPane listSizeScroller = new JScrollPane(listSizeName);
            listSizeScroller.setBounds(460, 170, 70, 150);


            // labelStyleName
            JLabel labelStyleName = new JLabel("Style");
            labelStyleName.setBounds(230, 50, 200, 50);
            textFieldStyleName = new JTextField();
            textFieldStyleName.setBounds(230, 110, 200, 50);
            String[] nameStyleName = {"Italic", "Bold", "Curve", "Underline", "SemiBold",
                    "1","2","3","4","5","6","7"};
            JList listStyleName = new JList(nameStyleName);

            listStyleName.setName("StyleNameChange");
            listStyleName.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listStyleName.setLayoutOrientation(JList.VERTICAL);
            listStyleName.setVisibleRowCount(-1);
            listStyleName.addListSelectionListener(controller);
//            listStyleName.setSelectedValue("Erkin", false);
            JScrollPane listStyleScroller = new JScrollPane(listStyleName);
            listStyleScroller.setBounds(230, 170, 200, 150);


            //labelFontName
            JLabel labelFontName = new JLabel("Font");
            labelFontName.setBounds(50, 50, 150, 50);
            labelFontName.setOpaque(true);
            textFieldFontName = new JTextField();
            textFieldFontName.setBounds(50, 110, 150, 50);
//            String[] nameFontName = {"Arial",
//                    "Montserrat", "Calibre",
//                    "PT Sans", "Avenger",
//                    "1","2","3","4","5","6","7"};
            GraphicsEnvironment ob =  GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] arrayFonts = ob.getAvailableFontFamilyNames();
            JList listFontName = new JList(arrayFonts);

            listFontName.setName("FontNameChange");
            listFontName.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listFontName.setLayoutOrientation(JList.VERTICAL);
            listFontName.setVisibleRowCount(-1);
            listFontName.addListSelectionListener(controller);
//            listFontName.setSelectedValue("Erkin", true);
            JScrollPane listFontScroller = new JScrollPane(listFontName);
            listFontScroller.setBounds(50, 170, 150, 150);
//


            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(410, 450, 150, 50);
            cancelButton.addActionListener(controller);
            cancelButton.setActionCommand("Close_Window");

            JButton okButton = new JButton("OK");
            okButton.setBounds(250, 450, 150, 50);
            okButton.addActionListener(controller);
            okButton.setActionCommand("Change_Font");



            int x = frame.getX();
            int y = frame.getY();
            dialogFont = new JDialog(frame, "Font", false);
            dialogFont.setSize(600, 600);
            dialogFont.setLocation(x + 100, y + 100);
            dialogFont.setLayout(null);
            dialogFont.add(okButton);
            dialogFont.add(cancelButton);
            dialogFont.add(labelFontName);
            dialogFont.add(textFieldFontName);
            dialogFont.add( listFontScroller);
            dialogFont.add(labelStyleName);
            dialogFont.add(textFieldStyleName);
            dialogFont.add(listStyleScroller);
            dialogFont.add(labelSizeName);
            dialogFont.add(textFieldSizeName);
            dialogFont.add( listSizeScroller);
            dialogFont.setVisible(true);
        } else{
            int x = frame.getX();
            int y = frame.getY();
            dialogFont.setLocation(x + 100, y + 100);
            dialogFont.setVisible(true);
        }





	}

    public void closeDialog() {
        dialogFont.setVisible(false);
    }
    public void changeFont(Font font){
        textArea.setFont(font);
        dialogFont.setVisible(false);

    }

    public void openFindDialog() {

        int x = frame.getX();
        int y = frame.getY();

        JLabel labelWhat = new JLabel("What:");
        labelWhat.setBounds(10, 30, 50, 50);

        textFieldFindText = new JTextField();
        textFieldFindText.setBounds(70, 30, 150, 50);

        JButton buttonFindNext = new JButton("Find Next...");
        buttonFindNext.setBounds(230, 30, 100, 50);
        buttonFindNext.addActionListener(controller);
        buttonFindNext.setActionCommand("Find_Text_From_Textarea");

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(230, 90, 100, 50);
        buttonCancel.addActionListener(controller);
        buttonCancel.setActionCommand("Close_Find_Dialog");

        dialogFind  = new JDialog(frame, "Find", false);
        dialogFind.setSize(400, 250);
        dialogFind.setLocation(x + 150, y + 150);
        dialogFind.setLayout(null);
        dialogFind.add(labelWhat);
        dialogFind.add(textFieldFindText);
        dialogFind.add(buttonFindNext);
        dialogFind.add(buttonCancel);
        dialogFind.setVisible(true);
    }
    public void closeFindDialog(){
        dialogFind.setVisible(false);
    }

    public String getFindText(){
        return textFieldFindText.getText();
    }

    public void findtextInTextArea(String text) {
        String searchTerm = textArea.getText();
        if(searchTerm.contains(text)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
            JOptionPane.showMessageDialog(frame,
                    "There is no such word.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
