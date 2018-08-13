package org.tutsplus.example3;

import javafx.scene.image.Image;

class AnimatedImage
{
    // assumes animation loops,
    //  each image displays for equal time
    public Image[] frames;
    public double duration;
    
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}