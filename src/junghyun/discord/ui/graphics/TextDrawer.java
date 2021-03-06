package junghyun.discord.ui.graphics;

import junghyun.ai.Game;
import junghyun.ai.Stone;
import junghyun.ai.Pos;

public class TextDrawer {

    private final static String WHITE = "●"; //TODO: 보편적인 문자열로 교체
    private final static String BLACK = "○"; //TODO: 보편적인 문자열로 교체

    private final static String LAST_WHITE = "■"; //TODO: 보편적인 문자열로 교체
    private final static String LAST_BLACK = "□"; //TODO: 보편적인 문자열로 교체

    private final static String CORNER_T_L = "┏";
    private final static String CORNER_T_R = "┓";
    private final static String CORNER_B_L = "┗";
    private final static String CORNER_B_R = "┛";

    private final static String CORNER_T_U = "┳";
    private final static String CORNER_T_D = "┻";

    private final static String CORNER_L = "┣";
    private final static String CORNER_R = "┫";

    private final static String CROSS = "╋";

    private final static String BLANK = "　";

    private final static String[] FIXED_NUM = {"０", "　１", "　２", "　３", "　４", "　５", "　６", "　７", "　８", "　９", "１０", "１１", "１２", "１３", "１４", "１５"};
    private final static String[] FIXED_ENG = {"Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ"};

    public static String getGraphics(Game game) {
        return getGraphics(game, new Pos(-1, -1), true);
    }

    public static String getGraphics(Game game, boolean useGrid) {
        return getGraphics(game, new Pos(-1, -1), useGrid);
    }

    public static String getGraphics(Game game, Pos aiPos) {
        return getGraphics(game, aiPos, true);
    }

    public static String getGraphics(Game game, Pos aiPos, boolean useGrid) {
        Stone[][] plate = game.getPlate();
        StringBuilder result = new StringBuilder("┏　");

        for (int x = 0; x < 15; x++) result.append(FIXED_ENG[x]);

        result.append("\n");

        for (int y = 0; y < 15; y++) {
            result.append(FIXED_NUM[y + 1]);
            for (int x = 0; x < 15; x++) {
                Stone pro_stone = plate[x][y];
                if (pro_stone.isStoneAdded()) {
                    if (pro_stone.getColor()) result.append((pro_stone.getX() == aiPos.getX()) && (pro_stone.getY() == aiPos.getY()) ? LAST_BLACK : BLACK);
                    else result.append((pro_stone.getX() == aiPos.getX()) && (pro_stone.getY() == aiPos.getY()) ? LAST_WHITE : WHITE);
                } else result.append(useGrid ? getEmptyCode(x, y) : BLANK);
            }
            result.append("\n");
        }

        return result.toString();
    }

    public static String getConsoleGraphics(Game game, boolean useGrid) {
        return getConsoleGraphics(game, new Pos(-1, -1), useGrid);
    }

    public static String getConsoleGraphics(Game game, Pos aiPos, boolean useGrid) {
        Stone[][] plate = game.getPlate();
        StringBuilder result = new StringBuilder("   ");

        for (int x = 0; x < 15; x++) result.append((char) (65 + x)).append(" ");

        result.append("\n");

        for (int y = 0; y < 15; y++) {
            if (y < 9) result.append(" ").append(y + 1).append(" ");
            else result.append(y + 1).append(" ");
            for (int x = 0; x < 15; x++) {
                Stone pro_stone = plate[x][y];
                if (pro_stone.isStoneAdded()) {
                    if (pro_stone.getColor()) result.append((pro_stone.getX() == aiPos.getX()) && (pro_stone.getY() == aiPos.getY()) ? "Q ": "O ");
                    else result.append((pro_stone.getX() == aiPos.getX()) && (pro_stone.getY() == aiPos.getY()) ? "% " : "# ");
                } else result.append(useGrid ? "+ " : "  ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    private static String getEmptyCode(int x, int y) {
        if (y == 0) {
            switch (x) {
                case 0:
                    return CORNER_T_L;
                case 14:
                    return CORNER_T_R;
                default:
                    return CORNER_T_U;
            }
        } else if (y == 14) {
            switch (x) {
                case 0:
                    return CORNER_B_L;
                case 14:
                    return CORNER_B_R;
                default:
                    return CORNER_T_D;
            }
        } else if (x == 0) return CORNER_L;
        else if (x == 14) return CORNER_R;
        else return CROSS;
    }
}
