package view.screen_components;

import javafx.scene.layout.BorderPane;

/**
 * Abstract class which structures how screen components are created
 */
public abstract class ScreenComponent {
	private BorderPane borderPane;

	/**
	 * Constructor
	 * Generates GUI components (unique to each screen component subclass)
	 */
	public ScreenComponent(){
		borderPane = new BorderPane();
		this.generateGUIComponent();
	}

	protected abstract void mapUserActions();

	protected abstract void generateGUIComponent();

	/**
	 *
	 * @return borderPane
	 */
	public BorderPane getGUIComponent(){
		mapUserActions();
		return borderPane;
	}

	/**
	 *
	 * @return borderPane
	 */
	protected BorderPane getBorderPane(){
		return borderPane;
	}
}
