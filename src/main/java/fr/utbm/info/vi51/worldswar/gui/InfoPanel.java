package fr.utbm.info.vi51.worldswar.gui;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel labelSimulationState;
	private JLabel labelSPS;
	private JLabel labelStepNumber;

	DecimalFormat df = new DecimalFormat("#,###,##0.00");
	
	public InfoPanel() {
		this.labelSimulationState = new JLabel(" simulation stopped |"); //$NON-NLS-1$
		this.add(this.labelSimulationState);
		
		this.labelSPS = new JLabel("N/A |"); //$NON-NLS-1$
		this.add(this.labelSPS);
		
		this.labelStepNumber = new JLabel(" step number : 0"); //$NON-NLS-1$
		this.add(this.labelStepNumber);
	}
	
	public void setSPSLabel(float nbSPS) {
		this.labelSPS.setText(df.format(nbSPS)+" steps per second |"); //$NON-NLS-1$
	}
	
	public void setLabelSimulationState(boolean simulationRunning) {
		if (simulationRunning) {
			this.labelSimulationState.setText(" simulation running |"); //$NON-NLS-1$
		} else {
			this.labelSimulationState.setText(" simulation stopped |"); //$NON-NLS-1$
		}
	}
	
	public void setLabelStepNumber(int stepNumber) {
		this.labelStepNumber.setText("step number : "+stepNumber); //$NON-NLS-1$
	}
}
