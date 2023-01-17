package com.SKP2.ClientApplication.controller;

import com.SKP2.ClientApplication.MainFrame;
import com.SKP2.ClientApplication.dto.CarDto;
import com.SKP2.ClientApplication.dto.CarFilterDto;
import com.SKP2.ClientApplication.dto.CarListDto;
import com.SKP2.ClientApplication.util.JTableImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FilterController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(1024, 768);

        JLabel lblFilter = new JLabel("Filter Parameters");
        lblFilter.setBounds(40, 40, 250, 30);
        jDialog.add(lblFilter);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(40, 70, 250, 30);
        jDialog.add(lblCity);

        JTextField tfCity = new JTextField();
        tfCity.setBounds(40, 100, 250, 30);
        jDialog.add(tfCity);

        JLabel lblCompanyID = new JLabel("Company ID");
        lblCompanyID.setBounds(40, 130, 250, 30);
        jDialog.add(lblCompanyID);

        JTextField tfCompanyID = new JTextField();
        tfCompanyID.setBounds(40, 160, 250, 30);
        jDialog.add(tfCompanyID);

        JLabel lblStartDate = new JLabel("Start Date");
        lblStartDate.setBounds(40, 190, 250, 30);
        jDialog.add(lblStartDate);

        JTextField tfStartDate = new JTextField();
        tfStartDate.setBounds(40, 220, 250, 30);
        jDialog.add(tfStartDate);

        JLabel lblEndDate = new JLabel("End Date");
        lblEndDate.setBounds(40, 250, 250, 30);
        jDialog.add(lblEndDate);

        JTextField tfEndDate = new JTextField();
        tfEndDate.setBounds(40, 280, 250, 30);
        jDialog.add(tfEndDate);

        JButton btnFilter = new JButton("Filter");
        btnFilter.setBounds(40, 310, 250, 30);
        jDialog.add(btnFilter);

        btnFilter.addActionListener(e1 -> {
            CarListDto list;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(tfStartDate.getText());
                java.sql.Date startDateToSqlDate = new java.sql.Date(startDate.getTime());
                Date endDate = sdf.parse(tfEndDate.getText());
                java.sql.Date endDateToSqlDate = new java.sql.Date(endDate.getTime());
                CarFilterDto carFilterDto = new CarFilterDto(tfCity.getText(), Long.parseLong(tfCompanyID.getText()), startDateToSqlDate, endDateToSqlDate);
                list = MainFrame.getInstance().getRentalService().filter(carFilterDto);
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
            List<CarDto> content = list.getContent();
            Object[][] data = new Object[50][50];
            int k = 0;
            for (CarDto dto : content)
                data[k++] = new Object[]{dto.getId(), dto.getModelName(), dto.getTypeName(), dto.getCompanyName(),
                        dto.getRentalDayPrice(), dto.isReserved(), dto.getStartDate(), dto.getEndDate()};
            String[] header = {"ID", "Model Name", "Type Name", "Company Name", "Rental Day Price", "Reserved Status",
                    "Start Date", "End Date"};

            JTableImpl table = new JTableImpl(header, data);
            table.setBounds(0, 340, 1024, 768);

            jDialog.add(new JScrollPane(table));
        });

        jDialog.setVisible(true);
    }
}
