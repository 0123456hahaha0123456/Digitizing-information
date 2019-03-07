import control.UTF8Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class Menu extends JMenu {
    Menu (frameClient gui){
        Locale locale = Locale.getDefault();
        //Locale locale = new Locale("ru","RU");
        ResourceBundle rb = ResourceBundle.getBundle("resources.Resources", locale, new UTF8Control());
        this.setText(rb.getString("language"));

        JMenuItem ru_Ru = new JMenuItem("Русский");
        ru_Ru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.changeLanguage(new Locale("ru", "RU"));
            }
        });
        this.add(ru_Ru);

        JMenuItem es_ES = new JMenuItem("Español");
        es_ES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.changeLanguage(new Locale("es", "ES"));
            }
        });
        this.add(es_ES);

        JMenuItem pl_PL = new JMenuItem("Polski");
        pl_PL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.changeLanguage(new Locale("pl", "PL"));
            }
        });
        this.add(pl_PL);

        JMenuItem en_US = new JMenuItem("English");
        en_US.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.changeLanguage(new Locale("en", "US"));
            }
        });
        this.add(en_US);
    }

    public void changeLanguage(Locale locale){
        ResourceBundle rb = ResourceBundle.getBundle("resources.Resources", locale, new UTF8Control());
        this.setText(rb.getString("language"));
    }
}