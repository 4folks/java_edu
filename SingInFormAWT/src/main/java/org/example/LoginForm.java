package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;

    public LoginForm() {
        // Устанавливаем заголовок окна
        setTitle("Форма авторизации");
        // Устанавливаем менеджер компоновки
        setLayout(new GridBagLayout());
        // Устанавливаем размеры окна
        setSize(600, 400);
        // Устанавливаем позицию окна
        setLocationRelativeTo(null);
        // Устанавливаем действие при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Устанавливаем видимость окна
        setVisible(true);

        // Создаем метки
        lblUsername = new JLabel("Имя пользователя:");
        lblPassword = new JLabel("Пароль:");
        // Создаем текстовые поля
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        // Создаем кнопки
        btnLogin = new JButton("Войти");
        btnCancel = new JButton("Отмена");

        // Добавляем компоненты на панель
        add(lblUsername, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(2, 2, 2, 2), 0, 0));
        add(txtUsername, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(2, 2, 2, 2), 0, 0));
        add(lblPassword, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(2, 2, 2, 2), 0, 0));
        add(txtPassword, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(2, 2, 2, 2), 0, 0));
        add(btnLogin, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(2, 2, 2, 2), 0, 0));
        add(btnCancel, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0,
                new Insets(2, 2, 2, 2), 0, 0));

        // Устанавливаем слушателя для кнопок
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Здесь должна быть проверка правильности введенных данных
                JOptionPane.showMessageDialog(null, "Данные введены верно!");
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}