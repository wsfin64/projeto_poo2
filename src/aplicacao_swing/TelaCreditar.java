package aplicacao_swing;

import fachada.Fachada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCreditar extends JFrame {
    private  JPanel contentPane;
    private JLabel labelCPF;
    private JTextField entradataCPF;
    private JLabel labelValor;
    private JTextField entradaValor;
    private JButton btnDepositar;
    private JButton btnLimpar;
    private JLabel mensagem;

    public TelaCreditar(){
        initialize();
    }

    private void initialize() {
        setTitle("Tela de Depósito");
        setBounds(100, 100, 345, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        labelCPF = new JLabel("CPF:");
        labelCPF.setHorizontalAlignment(SwingConstants.RIGHT);
        labelCPF.setBounds(20, 30, 105, 15);
        contentPane.add(this.labelCPF);

        entradataCPF = new JTextField();
        entradataCPF.setBounds(130, 30, 120, 20);
        contentPane.add(this.entradataCPF);
        entradataCPF.setColumns(10);

        labelValor = new JLabel("Valor R$:");
        labelValor.setHorizontalAlignment(SwingConstants.RIGHT);
        labelValor.setBounds(20, 60, 105, 15);
        contentPane.add(this.labelValor);

        entradaValor = new JTextField();
        entradaValor.setBounds(130, 60, 100, 20);
        contentPane.add(this.entradaValor);
        entradaValor.setColumns(10);

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf = entradataCPF.getText();
                    String valor = entradaValor.getText();
                    double quantia = Double.parseDouble(valor);
                    Fachada.creditar(cpf, quantia);

                    mensagem.setText("Deposito realizado com sucesso");
                }catch (Exception err){
                    mensagem.setText(err.getMessage());
                }
            }
        });

        btnDepositar.setBounds(43, 91, 112, 23);
        contentPane.add(this.btnDepositar);

        mensagem = new JLabel("Mensagem do usuário");
        mensagem.setBounds(19, 141, 294, 14);
        contentPane.add(this.mensagem);

        btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entradataCPF.setText("");
                entradaValor.setText("");
                entradataCPF.requestFocus();
            }
        });

        btnLimpar.setBounds(172, 91, 117, 23);
        contentPane.add(this.btnLimpar);

    }


}
