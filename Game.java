/*
<applet code = "Game.class"	    width = "640"	height = "480">
</applet>
*/

import java.applet.* ;
import java.awt.* ;
import java.awt.event.* ;


public class Game extends Applet implements MouseMotionListener, ActionListener
{
int x = 26,	 y1 = 370 ;
int y = 26, x1 = 530 ;
int color_ticks, score = 0, frame_rate = 30 ;
boolean C=true, B, A ;
AudioClip c1, c2, c3 ;
Font f = new Font("Arial",Font.PLAIN,20) ;
Button b1 = new Button("Retry...?") ;

public void init()
{
setBackground(Color.black) ;  
setFont(f) ;
setLayout(null) ;
c1 = getAudioClip(getCodeBase(),"drop.au") ;
c2 = getAudioClip(getCodeBase(),"beep.au") ;
c3 = getAudioClip(getCodeBase(),"gong.au") ;
b1.addActionListener(this); b1.setBounds(240,200,180,80); add(b1); b1.setVisible(false);
addMouseMotionListener(this) ;
}

public void mouseDragged(MouseEvent e){}

public void mouseMoved(MouseEvent e)
{
y1 = e.getY() ;
if(y1<20)y1=20 ;
else if(y1>360)y1=360 ;

x1 = e.getX() ;
if(x1<20)x1=20 ;
else if(x1>520)x1=520 ;
}

public void actionPerformed(ActionEvent e)
{
b1.setVisible(false); 
C=true; 
frame_rate=35; 
score=0; 
x=26; 
y1=370; 
y=26; 
x1=530; 
repaint(); 
}

public void paint(Graphics g) 
{
if(!B)x-=10 ;
else x+=11 ;
if(x<20){B=true;}
else if(x>570){c2.play(); B=false; color_ticks = 10; score--;}

if(!A)y-=10 ;
else y+=9 ;
if(y<20){A=true;}
else if(y>410){c2.play(); A=false; color_ticks = 10; score--;}

if(x>560 && (y>y1-20 && y<y1+80))
{ c1.play(); score++; B=false; g.drawString(String.valueOf(score),44,60); }
if(y>400 && (x>x1-20 && x<x1+80))
{ c1.play(); score++; A=false; g.drawString(String.valueOf(score),44,60); }

g.setColor(Color.orange) ;
if(score>20){g.drawOval(34,32,40,40); frame_rate=35;}
if(score>40){g.drawOval(33,31,42,42) ; frame_rate=40;}
if(score>60){g.drawOval(32,30,44,44) ; frame_rate=45;}
if(score>80){g.drawOval(31,29,46,46) ; frame_rate=50;}
if(score>100){g.drawOval(30,28,48,48) ; frame_rate=55;}

if(score<-10)C=false ;
else if(score<0)showStatus("You are about to loos this Game...!");
else showStatus("Designed  &  Encoded  by  Akshay Nile...");

color_ticks-- ;
if(color_ticks>0)g.setColor(Color.red) ;
else g.setColor(Color.yellow) ;

g.drawRect(9,9,622,462) ;
g.drawRect(10,10,620,460) ;
g.setColor(Color.yellow) ;
g.fillOval(x,y,50,50) ;
g.fillRect(610,y1,16,100) ;
g.fillRect(x1,450,100,16) ; 
g.drawString(String.valueOf(score),44,60);

try {Thread.sleep(1000/frame_rate); } catch(Exception e){}
if(C)repaint() ;
else{ c3.play(); b1.setVisible(true); showStatus("Sorry... You Lost...!"); }
}

}