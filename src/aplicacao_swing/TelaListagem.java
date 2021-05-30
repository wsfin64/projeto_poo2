package aplicacao_swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class TelaListagem extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    //	public static void main(String[] args) {
    //		EventQueue.invokeLater(new Runnable() {
    //			public void run() {
    //				try {
    //					TelaListagem frame = new TelaListagem();
    //					frame.setVisible(true);
    //				} catch (Exception e) {
    //					e.printStackTrace();
    //				}
    //			}
    //		});
    //	}

    /**
     * Create the frame.
     */
    public TelaListagem(String texto) {

        setTitle("Listagem");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 511, 226);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(24, 29, 458, 140);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        contentPane.add(scroll);

        textArea.setText(texto);




    }
}