package com.ui.component;

import com.ui.component.dialog.GameRecordDialog;
import com.ui.scheme.*;
import com.ui.component.button.MatchingPanelButton;
import com.ui.component.label.MatchingPanelLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MatchingPanel extends JPanel {
    MainFrame parentFrame;
    JTextArea textArea;
    JScrollPane scrollPane;
    JButton randomMatchButton;
    JButton privateMatchButton;
    JButton checkRecordButton;
    JLabel randomMatchLabel;
    JLabel privateMatchLabel;
    JLabel checkRecordLabel;
    Container randomField;
    Container privateField;
    Container checkRecordField;
    Container buttonArea;

    MatchingPanel(MainFrame parentFrame) {
        setBackground(ColorScheme.LIGHT_ORANGE.getColor());
        setLayout(new GridBagLayout());

        this.parentFrame = parentFrame;
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        randomMatchButton = MatchingPanelButton.getButton("ランダムマッチ");
        privateMatchButton = MatchingPanelButton.getButton("プライベートマッチ");
        checkRecordButton = MatchingPanelButton.getButton("対戦成績確認");
        randomMatchLabel = MatchingPanelLabel.getLabel("※世界中のプレイヤーと対戦しよう");
        privateMatchLabel = MatchingPanelLabel.getLabel("※友だちとプライベートで対戦しよう");
        checkRecordLabel = MatchingPanelLabel.getLabel("※自分の対戦成績を確認しよう");
        randomField = new Container();
        privateField = new Container();
        checkRecordField = new Container();
        buttonArea = new Container();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("game_rule.txt");
            String str = new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
            textArea.setText(str);
        } catch (Exception e) {
            System.out.println("Failed to load game rule: " + e);
        }
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(FontScheme.MATCHING_LABEL.getFont());
        textArea.setBackground(ColorScheme.LIGHT_GOLD.getColor());
        scrollPane.setPreferredSize(new Dimension(400, 600));
        scrollPane.setBorder(new LineBorder(Color.BLACK, 1, false));
        randomMatchButton.addActionListener(new RandomMatchingAction());
        privateMatchButton.addActionListener(new PrivateMatchingAction());
        checkRecordButton.addActionListener(new CheckRecordAction());
        randomField.setLayout(new GridBagLayout());
        privateField.setLayout(new GridBagLayout());
        checkRecordField.setLayout(new GridBagLayout());
        buttonArea.setLayout(new GridLayout(3, 1));

        add(scrollPane, LayoutScheme.MATCHING_SCROLLPANEL.getLayout());
        randomField.add(randomMatchButton, LayoutScheme.MATCHING_BUTTON.getLayout());
        randomField.add(randomMatchLabel, LayoutScheme.MATCHING_LABEL.getLayout());
        privateField.add(privateMatchButton, LayoutScheme.MATCHING_BUTTON.getLayout());
        privateField.add(privateMatchLabel, LayoutScheme.MATCHING_LABEL.getLayout());
        checkRecordField.add(checkRecordButton, LayoutScheme.MATCHING_BUTTON.getLayout());
        checkRecordField.add(checkRecordLabel, LayoutScheme.MATCHING_LABEL.getLayout());
        buttonArea.add(randomField);
        buttonArea.add(privateField);
        buttonArea.add(checkRecordField);
        add(buttonArea, LayoutScheme.MATCHING_BUTTONAREA.getLayout());
    }

    class RandomMatchingAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //TODO: add random matching action

            parentFrame.changePanel(new LobbyPanel(parentFrame, "random", "0023"));
        }
    }

    class PrivateMatchingAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //TODO: add private matching action

            parentFrame.changePanel(new LobbyPanel(parentFrame, "private", "0024"));
        }
    }

    class CheckRecordAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //TODO: add check record action

            GameRecordDialog.getDialog(parentFrame, "ここで戦績を確認できます").setVisible(true);
        }
    }
}
