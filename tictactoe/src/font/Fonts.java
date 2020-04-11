package font;

import javafx.scene.text.Font;

public class Fonts {
	public Font getFont(FONT_TYPE type,int size) {
		switch (type) {
		case THIN:
			return Font.loadFont("file:resources/fonts/thin.ttf", size);
		case LIGHT:
			return Font.loadFont("file:resources/fonts/light.ttf", size);
		case EXTRA_LIGHT:
			return Font.loadFont("file:resources/fonts/extra_light.ttf", size);
		case REGULAR:
			return Font.loadFont("file:resources/fonts/regular.ttf", size);
		case MEDIUM:
			return Font.loadFont("file:resources/fonts/medium.ttf", size);
		case BOLD:
			return Font.loadFont("file:resources/fonts/bold.ttf", size);
		case SEMI_BOLD:
			return Font.loadFont("file:resources/fonts/semi_bold.ttf", size);
		case EXTRA_BOLD:
			return Font.loadFont("file:resources/fonts/extra_bold.ttf", size);
		case BLACK:
			return Font.loadFont("file:resources/fonts/black.ttf", size);
		}
		return getFont(FONT_TYPE.REGULAR, 16);
		
	}
}
