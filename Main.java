
/**
 *
 * @author Haroon
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AnimationPlayer player = new AnimationPlayer();
        player.loadAnimationFromFile("animation1.txt"); // no longer need!!!!! Just run this in your application's start
                                                        // function (executed after launch)
        player.run(args);

    }

}
