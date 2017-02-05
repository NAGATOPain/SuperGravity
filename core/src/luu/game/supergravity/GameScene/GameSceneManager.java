package luu.game.supergravity.GameScene;

import java.util.Stack;

/**
 * Created by luu on 9/26/16.
 */

public class GameSceneManager {

    private Stack<Scene> gsm;

    public GameSceneManager(){
        gsm = new Stack<Scene>();
    }

    public void push(Scene scene){
        if (gsm.empty()){
            gsm.push(scene);
        }
        else {
            gsm.pop().dispose();
            gsm.push(scene);
        }
    }

    public Scene peek(){
        return gsm.peek();
    }

    public void pop(){
        gsm.pop().dispose();
    }
}
