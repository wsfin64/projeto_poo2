package aplicacao_swing;

import fachada.Fachada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTransferir extends JFrame {
    private JPanel contentPane;
    private JLabel labelCPF;
    private JTextField entradaCPF;
    private JLabel labelChavePIKS;
    private JTextField entradaChavePIKS;
    private JLabel labelValor;
    private JTextField entradaValor;
    private JButton btnTransferir;
    private JButton btnLimpar;
    private JLabel mensagem;

    public TelaTransferir(){
        initialize();
    }

    private void initialize(){
        setTitle("Tranferência com PIKS");
        setBounds(100, 100, 345, 230);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        labelCPF = new JLabel("CPF:");
        labelCPF.setHorizontalAlignment(SwingConstants.RIGHT);
        labelCPF.setBounds(20, 30, 105, 15);
        contentPane.add(this.labelCPF);

        entradaCPF = new JTextField();
        entradaCPF.setBounds(130, 30, 120, 20);
        contentPane.add(this.entradaCPF);
        entradaCPF.setColumns(10);

        labelValor = new JLabel("Valor R$:");
        labelValor.setHorizontalAlignment(SwingConstants.RIGHT);
        labelValor.setBounds(20, 60, 105, 15);
        contentPane.add(this.labelValor);

        entradaValor = new JTextField();
        entradaValor.setBounds(130, 60, 100, 20);
        contentPane.add(this.entradaValor);
        entradaValor.setColumns(10);

        labelChavePIKS = new JLabel("Chave Destino:");
        labelChavePIKS.setHorizontalAlignment(SwingConstants.RIGHT);
        labelChavePIKS.setBounds(20, 90, 100, 20);
        contentPane.add(this.labelChavePIKS);

        entradaChavePIKS = new JTextField();
        entradaChavePIKS.setBounds(130, 90, 120, 20);
        contentPane.add(this.entradaChavePIKS);
        entradaChavePIKS.setColumns(10);

        btnTransferir = new JButton("Transferir");
        btnTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf = entradaCPF.getText();
                    String chave = entradaChavePIKS.getText();
                    String valor = entradaValor.getText();
                    double quantia = Double.parseDouble(valor);

                    Fachada.transferir(cpf, chave, quantia);

                    mensagem.setText("Transferência realizada com sucesso");
                }catch (Exception errTrasferencia){
                    mensagem.setText(errTrasferencia.getMessage());
                }
            }
        });

        btnTransferir.setBounds(43, 120, 112, 23);
        contentPane.add(this.btnTransferir);

        btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entradaCPF.setText("");
                entradaValor.setText("");
                entradaChavePIKS.setText("");
                entradaCPF.requestFocus();
            }
        });

        btnLimpar.setBounds(172, 120, 112, 23);
        contentPane.add(this.btnLimpar);

        mensagem = new JLabel("Mensagem do usuário");
        mensagem.setBounds(19, 161, 294, 14);
        contentPane.add(this.mensagem);

    }

}
