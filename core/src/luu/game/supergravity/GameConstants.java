package luu.game.supergravity;

/**
 * Created by luu on 9/26/16.
 */

public final class GameConstants {

    //Desktop :
    public static final String DESKTOP_TITLE = "Super Gravity V1";
    public static final int DESKTOP_HEIGHT = 800;
    public static final int DESKTOP_WIDTH = 480;

    public static final String TEXTURE_PATH = "resource/texture";
    public static final String FONT_PATH = "resource/font";
    public static final String SOUND_PATH = "resource/audio";

    //Main Character :
    public static final String JUMP_TEXTURE_PATH = TEXTURE_PATH + "/MainCharacter/Jumping/charJumping.atlas";
    public static final String RUN_TEXTURE_PATH = TEXTURE_PATH + "/MainCharacter/Running/charRunning.atlas";
    public static final String STATIC_TEXTURE_PATH = TEXTURE_PATH + "/MainCharacter/Static/charStatic.atlas";
    public static final float CHARACTER_SIZE_WIDTH = 1/12f;
    public static final float CHARACTER_SIZE_HEIGHT = 1/10f;

    //Character status :
    public static final int IS_RUNNING = 1;
    public static final int IS_JUMPING = 2;
    public static final int IS_STATIC = 3;

    //Button:
    public static final String BUTTON_PLAY_PATH = TEXTURE_PATH + "/Button/PlayButton";
    public static final String BUTTON_RESTART_PATH = TEXTURE_PATH + "/Button/RestartButton";
    public static final float BUTTON_PLAY_SIZE = 1/3f;
    public static final float BUTTON_RESTART_SIZE = 0.2f;

    //Music :
    public static final String BACKGROUND_MUSIC = SOUND_PATH + "/bg.mp3";
    public static final String CLICKING_SOUND = SOUND_PATH + "/click.wav";
    public static final String JUMP_SOUND = SOUND_PATH + "/jump.wav";
    public static final String DEATH_SOUND = SOUND_PATH + "/death.wav";

    //Background :
    public static final String BACKGROUND_PATH = TEXTURE_PATH + "/background.jpg";

    //Pipe :
    public static final String PIPE_PATH = TEXTURE_PATH + "/Pipe.png";
    public static final float DELTA_Y_OF_PIPES = 0.075f;
    public static final float DELTA_X_OF_PIPES = 0.75f;
    public static final float MAX_HEIGHT_OF_PIPES = 0.75f;

    //Ground:
    public static final String GROUND_PATH = TEXTURE_PATH + "/ground.png";
    public static final float GROUND_DT_HEIGHT = 1/7.5f;

    //Time Score (x,y):
    public static final float TIME_X = 17/20f;
    public static final float TIME_Y = 19/20f;

    //Gravity (x,y):
    public static final float GRAVITY_X = 1/20f;
    public static final float GRAVITY_Y = 19/20f;

    //White Panel:
    public static final String WHITE_PANEL = TEXTURE_PATH + "/score_panel.png";
    public static final float OVER_PANEL_SIZE = 0.65f;

    //Font path :
    public static final String OPEN_SANS_PATH = FONT_PATH + "/OpenSans-Regular.ttf";

    //Other :
    public static final float TIME_PER_BACKGROUND = 2f;
    public static final float TRADING_TIME = 0.1f; //0.1 seconds to trading to other scene

}
