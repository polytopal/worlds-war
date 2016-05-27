package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * This JPanel appear at the bottom of the GUI, and indicates information about
 * the state of the simulation, such as if it is running, the number of steps
 * per second etc. It is meant to be instantiated and updated by Window.
 *
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelSimulationState;
	private JLabel labelStepPerSecond;
	private JLabel labelStepNumber;
	private JLabel labelAgentCount;

	/**
	 * Build the InfoPanel (bottom status bar)
	 */
	public InfoPanel() {
		this.labelSimulationState = new JLabel(String.format("%s |", Messages.getString("SimulationState.stopped"))); //$NON-NLS-1$ //$NON-NLS-2$
		this.add(this.labelSimulationState);

		this.labelStepPerSecond = new JLabel(String.format("%s |", Messages.getString("na"))); //$NON-NLS-1$//$NON-NLS-2$
		this.add(this.labelStepPerSecond);

		this.labelStepNumber = new JLabel(
				String.format(" %s : %d |", Messages.getString("InfoPanel.stepNumber"), new Integer(0))); //$NON-NLS-1$//$NON-NLS-2$
		this.add(this.labelStepNumber);

		this.labelAgentCount = new JLabel(
				String.format(" %s : %d", Messages.getString("InfoPanel.agentCount"), new Integer(0))); //$NON-NLS-1$//$NON-NLS-2$
		this.add(this.labelAgentCount);
	}

	/**
	 * @param nbSPS
	 *            the number of steps per second to display
	 */
	public void setStepPerSecondLabel(float nbSPS) {
		this.labelStepPerSecond.setText(
				String.format("%.2f %s", new Float(nbSPS), Messages.getString("InfoPanel.stepsPerSecond")) + " |"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * @param simState
	 *            the {@link SimulationState to display}
	 */
	public void setSimulationStateLabel(SimulationState simState) {
		this.labelSimulationState.setText(Messages.getString(simState.getPropertyKey()) + " |"); //$NON-NLS-1$
	}

	/**
	 * @param stepNumber
	 *            the step number to display
	 */
	public void setStepNumberLabel(int stepNumber) {
		this.labelStepNumber.setText(
				String.format(" %s : %d |", Messages.getString("InfoPanel.stepNumber"), new Integer(stepNumber))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @param agentCount
	 *            the count of agents to display
	 */
	public void setAgentCountLabel(int agentCount) {
		this.labelAgentCount.setText(
				String.format(" %s : %d", Messages.getString("InfoPanel.agentCount"), new Integer(agentCount))); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
