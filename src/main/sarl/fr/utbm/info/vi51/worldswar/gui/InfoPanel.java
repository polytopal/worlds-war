package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * This JPanel appear at the bottom of the GUI, and indicates 
 * information about the state of the simulation, such as if
 * it is running, the number of steps per second etc.
 * It is meant to be instantiated and updated by Window.
 *
 */
public class InfoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel labelSimulationState;
	private JLabel labelStepPerSecond;
	private JLabel labelStepNumber;
	
	public InfoPanel() {
		this.labelSimulationState = new JLabel(Messages.getString("InfoPanel.simulationStopped")+" |"); //$NON-NLS-1$ //$NON-NLS-2$
		this.add(this.labelSimulationState);
		
		this.labelStepPerSecond = new JLabel("N/A |"); //$NON-NLS-1$
		this.add(this.labelStepPerSecond);
		
		this.labelStepNumber = new JLabel(String.format(" %s : %d", Messages.getString("InfoPanel.stepNumber"), new Integer(0)));  //$NON-NLS-1$//$NON-NLS-2$
		this.add(this.labelStepNumber);
	}
	
	public void setStepPerSecondLabel(float nbSPS) {
		this.labelStepPerSecond.setText(String.format("%.2f %s", new Float(nbSPS), Messages.getString("InfoPanel.stepsPerSecond"))+" |");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
	}
	
	public void setSimulationStateLabel(boolean simulationRunning) {
		if (simulationRunning) {
			this.labelSimulationState.setText(Messages.getString("InfoPanel.simulationRunning")+" |"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			this.labelSimulationState.setText(Messages.getString("InfoPanel.simulationStopped")+" |"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	public void setStepNumberLabel(int stepNumber) {
		this.labelStepNumber.setText(String.format(" %s : %d", Messages.getString("InfoPanel.stepNumber"), new Integer(stepNumber))); //$NON-NLS-1$ //$NON-NLS-2$
	}
}