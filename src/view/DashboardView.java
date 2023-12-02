package view;

import entity.AccountInfo;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "display dash";
    private final DashboardController dashboardController;
    private final DashboardViewModel dashboardViewModel;
    private JButton mainView;
    private DefaultTableModel acccountsTableModel;
    private JTable accounts;

    public DashboardView(DashboardViewModel dashboardViewModel, DashboardController dashboardController) {
        this.dashboardViewModel = dashboardViewModel;
        this.dashboardController = dashboardController;
        this.dashboardViewModel.addPropertyChangeListener(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object state = evt.getNewValue();
        if (state instanceof DashboardState) {
            this.dashboardController.execute();
            DashboardState dashboardState = (DashboardState) evt.getNewValue();
            for (AccountInfo account: dashboardState.getAccounts() ){
                acccountsTableModel.addRow(new Object[]{account.getIconURL(), account.getTitle(), account.getURL(), account.getDate()});
            }
        }

    }


}
