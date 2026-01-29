import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame {
    private JTextField nameField, deptField, dateField;
    private JTextArea resultArea;
    private EmployeeDAO dao = new EmployeeDAO();

    public Main() {
        setTitle("従業員管理アプリ");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("氏名"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("部署"));
        deptField = new JTextField();
        panel.add(deptField);

        panel.add(new JLabel("入社日 (yyyy-mm-dd)"));
        dateField = new JTextField();
        panel.add(dateField);

        JButton addButton = new JButton("登録");
        JButton showButton = new JButton("一覧表示");
        panel.add(addButton);
        panel.add(showButton);

        add(panel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        addButton.addActionListener(e -> {

            String name = nameField.getText();
            String dept = deptField.getText();

            // 氏名チェック
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "従業員名を入力してください。",
                    "入力エラー",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // 部署チェック
            if (dept == null || dept.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "部署名を入力してください。",
                    "入力エラー",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            // 入社日チェック＆変換
            LocalDate joinDate;
            try {
                joinDate = LocalDate.parse(dateField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "入社日は yyyy-mm-dd 形式で入力してください。",
                    "入力エラー",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // 登録
            dao.insert(new Employee(
                name,
                deptField.getText(),
                joinDate
            ));

            JOptionPane.showMessageDialog(this, "登録しました");
            
            //登録後入力欄を空欄に
            nameField.setText("");
            deptField.setText("");
            dateField.setText("");

        });

        
        
        showButton.addActionListener(e -> {
            resultArea.setText("");
            for (Employee emp : dao.findAll()) {
                resultArea.append(emp + "\n");
            }
        });
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}


