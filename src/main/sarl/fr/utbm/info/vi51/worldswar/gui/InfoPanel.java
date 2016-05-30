package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * 
 * This JPanel appear at the bottom of the GUI, and indicates information about
 * the state of the simulation, such as if it is running, the number of steps
 * per second etc. It is meant to be instantiated and updated by Window.
 *
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()//
			.appendDays()//
			.appendSuffix("d, ")//
			.appendHours()//
			.appendSuffix("h ")//
			.appendMinutes()//
			.appendSuffix("m ")//
			.appendSeconds()//
			.appendSuffix("s")//
			.toFormatter();

	private JLabel labelSimulationState;
	private JLabel labelStepPerSecond;
	private JLabel labelSimulationTime;
	private JLabel labelAgentCount;

	/**
	 * Build the InfoPanel (bottom status bar)
	 */
	public InfoPanel() {
		this.labelSimulationState = new JLabel();
		this.labelStepPerSecond = new JLabel();
		this.labelAgentCount = new JLabel();
		this.labelSimulationTime = new JLabel();

		this.add(this.labelSimulationState);
		this.add(this.labelSimulationTime);
		this.add(this.labelAgentCount);
		this.add(this.labelStepPerSecond);

		this.setSimulationStateLabel(SimulationState.STOPPED);
		this.setStepPerSecondLabel(0);
		this.setAgentCountLabel(0);
		this.setSimulationTimeLabel(0);

	}

	/**
	 * @param nbSPS
	 *            the number of steps per second to display
	 */
	public void setStepPerSecondLabel(float nbSPS) {
		this.labelStepPerSecond
				.setText(String.format("%.2f %s", new Float(nbSPS), Messages.getString("InfoPanel.stepsPerSecond"))); //$NON-NLS-1$//$NON-NLS-2$
																														// //$NON-NLS-3$
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
	 *            the current step number
	 */
	public void setSimulationTimeLabel(int stepNumber) {
		Duration duration = new Duration((stepNumber * SimulationSpeed.REAL_TIME.getMilliseconds()));

		// Workaround to a bug that causes seconds to not be displayed when
		// milliseconds are not null but lesser than 1000
		if (duration.getMillis() < 1000) {
			duration = new Duration(0);
		}

		this.labelSimulationTime.setText(String.format(" %s : %s |", //$NON-NLS-1$
				Messages.getString("InfoPanel.simulationTime"), PERIOD_FORMATTER.print(duration.toPeriod())));
	}

	/**
	 * @param agentCount
	 *            the count of agents to display
	 */
	public void setAgentCountLabel(int agentCount) {
		this.labelAgentCount.setText(
				String.format(" %s : %d |", Messages.getString("InfoPanel.agentCount"), new Integer(agentCount))); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
