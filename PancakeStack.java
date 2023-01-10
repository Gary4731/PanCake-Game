

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class PancakeStack {

    private List<Pancake> pancakeAry;
    private int numPancake;
    private PancakeGUI MouseListener;

    public PancakeStack()
    {
        this.numPancake = 0;
        this.pancakeAry = new ArrayList<>();
    }

    public void push(Pancake p){
        pancakeAry.add(p);
        numPancake++;
    }

    public Pancake pop()
    {
        numPancake--;
        return pancakeAry.remove(numPancake);
    }

    public Pancake peek(){
        if(numPancake == 0)
            throw new NoSuchElementException("No pancake left");
        return pancakeAry.get(numPancake-1);
    }

    public int findMax(int offset)
    {
        int arr[] =  new int[offset];
        int max = 0, i = 0,x = 0;
        for(;i<offset;i++)
        {
            arr[i] = pancakeAry.get(i).getSize();
        }
        for(;x< offset; x++)
        {
            if(arr[x] > arr[max])
                max = x;
        }
        return max;
    }

    public void flip(int offset)
    {
        int arr[] =  new int[offset];
        int left = 0, right = offset;

        while(left < right){
            Collections.swap(pancakeAry,left,right);
            left ++;
            right --;
        }
    }

    public List<Pancake> mouseClick(int offset)
    {
        Collections.swap(pancakeAry,0, offset-1);
        return pancakeAry;
    }

    public List<Pancake> sortMethod(int offset)
    {
        if(offset == 1)
        {
            return pancakeAry;
        }else {
            for (; offset > 1; offset--) {
                int max = findMax(offset);
                if (max != offset - 1) {
                    flip(max);
                    flip(offset - 1);
                }
            }
        }
        return pancakeAry;
    }

    public void drawPancakes(Graphics g){
        for(Pancake pancake: pancakeAry){
            pancake.draw(g);
        }
    }
}
